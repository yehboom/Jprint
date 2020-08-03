package model.persistence;

import model.*;
import model.Point;
import model.dialogs.DialogProvider;
import model.factory.ShapeFactory;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import model.interfaces.IShape;
import view.gui.PaintCanvas;
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
    private List<IShape> selectList;
    private List<IShape> copyList;
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

    public void setSelectList(List<IShape> selectList) {
        this.selectList = selectList;
    }

    @Override
    public void setDelete() {
        List<IShape> allList = store.getShapeList();
        if (allList.size() == 0) {
            return;
        }
        for (IShape s : selectList) {
            allList.remove(s);
        }
        print(allList);
    }

    @Override
    public void setCopy() {
        System.out.println("copy");
        copyList = this.selectList;

    }

    @Override
    public void setPaste() {
        System.out.println("Paste");

        if (copyList == null) {
            return;
        }

        ShapeFactory shapeFactory = new ShapeFactory();
        IShape newShape;
        for (IShape i : copyList) {

            Point pointStart = i.getStartPoint();
            Point pointEnd = i.getEndPoint();
            String type = i.toString();
            i.setCopyCount();


            if (type.equals("Triangle")) {
                newShape = shapeFactory.createTriangle();
            } else if (type.equals("Ellipse")) {
                newShape = shapeFactory.createEllipse();
            } else if (type.equals("Rectangle")) {
                newShape = shapeFactory.createRectangle();
            } else {
                newShape = shapeFactory.createNullShape();
            }


            int offsetValue = i.getCopyCount();

            Point newStart = new Point(pointStart.getX() + offsetValue, pointStart.getY() + offsetValue);
            Point newEnd = new Point(pointEnd.getX() + offsetValue, pointEnd.getY() + offsetValue);
            int newHeight = i.getHeight();
            int newWidth = i.getWidth();

            newShape.setStartPoint(newStart);
            newShape.setEndPoint(newEnd);
            newShape.setHeight(newHeight);
            newShape.setWidth(newWidth);

            newShape.setShapeShadingType(i.getShapeShadingType());
            newShape.setShapeColorPrimary(i.getShapeColorPrimary());
            newShape.setShapeColorSecond(i.getShapeColorSecond());


            store.addShape(newShape);
        }

        List<IShape> allList = store.getShapeList();
        print(allList);

    }


    public void print(List<IShape> allList) {

        g.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
        g.setColor(Color.white);
        g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        for (IShape s : allList) {
            s.update(s, g);
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
