package model;

import model.factory.ShapeFactory;
import model.interfaces.IShape;
import model.interfaces.Ithing;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

public class PasteCommand implements ICommand, IUndoable {
    private List<Ithing> copyList;
    private ShapeStore store;
    private IShape newShape;
    private List<Ithing> pasteShapeList;

    private PaintCanvasBase paintCanvas;
    private Graphics2D g;


    public PasteCommand(List<Ithing> copyList, ShapeStore store, PaintCanvasBase paintCanvas, Graphics2D g) {
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
        List<Ithing> allList = store.getShapeList();
        Printer.print(allList, g, paintCanvas);




    }

    public void paste() {


        if (copyList == null) {

            return;
        }

        System.out.println("Copy List size" + copyList.size());
        ShapeFactory shapeFactory = new ShapeFactory();

        for (Ithing i : copyList) {
            Point pointStart = ((IShape) i).getStartPoint();
            Point pointEnd = ((IShape) i).getEndPoint();
            String type = i.toString();
            ((IShape) i).setCopyCount();

            int offsetValue = ((IShape) i).getCopyCount() + 50;
            System.out.println("offsetValue" + offsetValue);
            Point newStart;
            Point newEnd;
            int newHeight = ((IShape) i).getHeight();
            int newWidth = ((IShape) i).getWidth();


            if (i instanceof Group) {
                List<Ithing> tempChildren = ((Group) i).getChildren();
                List<Ithing> cloneChildren = new ArrayList<>();
                for (Ithing temp : tempChildren) {
                    cloneChildren.add(((IShape) temp).getClone());
                }


                Ithing newGroup = GroupFactory.createShapeGroup(cloneChildren, g);
                newShape = (IShape) newGroup;
                ((Group) i).setSelect(false);
                newShape.setSelect(true);

                for (Ithing s1 : cloneChildren) {
                    Point tempStartPoint = ((IShape) s1).getStartPoint();
                    Point tempEndPoint = ((IShape) s1).getEndPoint();

                    newStart = new Point(tempStartPoint.getX() + offsetValue, tempStartPoint.getY() + offsetValue);
                    newEnd = new Point(tempEndPoint.getX() + offsetValue, tempEndPoint.getY() + offsetValue);

                    ((IShape) s1).setStartPoint(newStart);
                    ((IShape) s1).setEndPoint(newEnd);
                }


            } else {
                if (type.equals("Triangle")) {
                    newShape = shapeFactory.createTriangle();
                } else if (type.equals("Ellipse")) {
                    newShape = shapeFactory.createEllipse();
                } else if (type.equals("Rectangle")) {
                    newShape = shapeFactory.createRectangle();
                } else {
                    newShape = shapeFactory.createNullShape();
                }

            }


            newStart = new Point(pointStart.getX() + offsetValue, pointStart.getY() + offsetValue);
            newEnd = new Point(pointEnd.getX() + offsetValue, pointEnd.getY() + offsetValue);
            newShape.setStartPoint(newStart);
            newShape.setEndPoint(newEnd);


            newShape.setHeight(newHeight);
            newShape.setWidth(newWidth);

            newShape.setShapeShadingType(((IShape) i).getShapeShadingType());
            newShape.setShapeColorPrimary(((IShape) i).getShapeColorPrimary());
            newShape.setShapeColorSecond(((IShape) i).getShapeColorSecond());



            store.addShape(newShape);
            pasteShapeList.add(newShape);
            store.cleanSelectShapeList();
            store.addSelectShape(newShape);


        }

    }

    @Override
    public void undo() {

        List<Ithing> allList = store.getShapeList();


        if (allList.size() == 0) {
            return;
        }

        for (Ithing s : allList) {
            ((IShape) s).deductCopyCount();
        }

        for (Ithing s : pasteShapeList) {
            allList.remove(s);
        }

        Printer.print(allList, g, paintCanvas);


    }


    @Override
    public void redo() {

        paste();
        List<Ithing> allList = store.getShapeList();

        Printer.print(allList, g, paintCanvas);

    }
}
