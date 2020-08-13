package model;

import model.factory.ShapeFactory;
import model.interfaces.IShape;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;

import java.util.List;

public class PasteCommand implements ICommand {
    private List<IShape> copyList;
    private ShapeStore store;

    public PasteCommand(List<IShape> copyList, ShapeStore store) {
        this.copyList = copyList;
        this.store = store;
    }


    @Override
    public void run() {
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


    }
}
