package model.persistence;

import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.*;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class ApplicationState implements IApplicationState, Serializable {
    private static final long serialVersionUID = -5545483996576839009L;
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private List<Ithing> selectList;
    private List<Ithing> copyList;
    private PaintCanvasBase paintCanvas;
    private ShapeStore store;
    private Graphics2D g;


    public ApplicationState(IUiModule uiModule, PaintCanvasBase paintCanvas, ShapeStore store) {
        this.paintCanvas = paintCanvas;
        this.store = store;
        this.uiModule = uiModule;

        this.dialogProvider = new DialogProvider(this);
        setDefaults();
        g = paintCanvas.getGraphics2D();
    }

    public void setSelectList(List<Ithing> selectList) {
        this.selectList = selectList;
    }

    @Override
    public void setDelete() {
        new DeleteCommand(store, paintCanvas, g, selectList).run();
    }

    public void deleteCreate() {
        List<Ithing> allList = store.getShapeList();
        List<Ithing> selectList = store.getSelectShapeList();
        if (allList.size() == 0) {
            return;
        }
        for (Ithing s : selectList) {
            allList.remove(s);
        }

        Printer.print(allList, g, paintCanvas);
//        print(allList);

    }

    @Override
    public void setGroup() {


        GroupCommand newGroupCommand = new GroupCommand(selectList, store, g);
        newGroupCommand.run();


        System.out.println("GroupCommand size" + newGroupCommand.getSize());
    }

    @Override
    public void setUndo() {
        System.out.println("setUndo");
        new UndoCommand().run();
    }

    @Override
    public void setRedo() {
        System.out.println("setRedo");
        new RedoCommand().run();
    }

    @Override
    public void setCopy() {
        System.out.println("copy");
        copyList = this.selectList;

    }

    @Override
    public void setPaste() {
        new PasteCommand(copyList, store, paintCanvas, g).run();
//        List<IShape> allList = store.getShapeList();
//        print(allList);
    }


    public void print(List<Ithing> allList) {

        g.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
        g.setColor(Color.white);
        g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        for (Ithing s : allList) {
            ((IShape) s).update(((IObserver) s), g);
        }
    }


    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }
}
