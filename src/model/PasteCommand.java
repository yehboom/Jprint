package model;

import model.factory.ShapeFactory;
import model.interfaces.IShape;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PasteCommand implements ICommand, IUndoable {
    private List<IShape> copyList;
    private ShapeStore store;
    private IShape newShape;
    private List<IShape> pasteShapeList;

    private PaintCanvasBase paintCanvas;
    private Graphics2D g;


    public PasteCommand(List<IShape> copyList, ShapeStore store, PaintCanvasBase paintCanvas, Graphics2D g) {
        this.copyList = copyList;
        this.store = store;
        pasteShapeList = new ArrayList<>();

        this.paintCanvas = paintCanvas;
        this.g = g;
    }


    @Override
    public void run() {
        paste();

        CommandHistory.add(this);




    }

    public void paste() {
        System.out.println("Paste");

        if (copyList == null) {
            return;
        }

        ShapeFactory shapeFactory = new ShapeFactory();

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
            pasteShapeList.add(newShape);
        }

    }

    @Override
    public void undo() {
        System.out.println("Paste undo");
        List<IShape> allList = store.getShapeList();

        System.out.println("paste allList size" + allList.size());
        if (allList.size() == 0) {
            return;
        }
        System.out.println("Appstate select size" + pasteShapeList.size());
        for (IShape s : pasteShapeList) {
            allList.remove(s);
        }

        Printer.print(allList, g, paintCanvas);


    }


    @Override
    public void redo() {
        paste();
        List<IShape> allList = store.getShapeList();

        Printer.print(allList, g, paintCanvas);

    }
}
