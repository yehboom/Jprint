package model;

import model.factory.ShapeFactory;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;
import model.interfaces.Ithing;
import view.interfaces.ICommand;

import java.awt.*;
import java.util.List;

import static model.ShapeShadingType.*;

public class Group implements Ithing, ICommand, IShape {
    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;
    private boolean select;
    private boolean reverse;
    private boolean specialDirection;
    private int copyCount;

    private int shapeType;


    private ShapeColor shapeColorPrimary;
    private ShapeColor shapeColorSecond;
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private IStrategy iStrategy;
    private ShapeShadingType shapeShadingType;


    private List<Ithing> children;
    private int GroupWidth;
    private int GroupHeight;

    private Point GroupStartPoint;
    private Point GroupEndPoint;

    private Graphics2D g;

    public Group(List<Ithing> children, Graphics2D g) {
        this.children = children;
        this.g = g;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Ithing s : children) {
            //start point
            int newMinXValue = ((IShape) s).getStartPoint().getX();
            int newMinYValue = ((IShape) s).getStartPoint().getY();
            if (minX > newMinXValue) {
                minX = newMinXValue;
            }

            if (minY > newMinYValue) {
                minY = newMinYValue;
            }


            //end point
            int newMaxXValue = ((IShape) s).getEndPoint().getX();
            int newMaxYValue = ((IShape) s).getEndPoint().getY();
            if (maxX < newMaxXValue) {
                maxX = newMaxXValue;
            }
            if (maxY < newMaxYValue) {
                maxY = newMaxYValue;
            }


        }
        GroupStartPoint = new Point(minX - 10, minY - 10);
        GroupEndPoint = new Point(maxX + 10, maxY + 10);

        this.startPoint = GroupStartPoint;
        this.endPoint = GroupEndPoint;

        draw(g);

    }

    @Override
    public void setReverse(Boolean b) {

    }

    @Override
    public void setSelect(Boolean b) {

    }

    @Override
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {


    }

    @Override
    public void setShapeColorSecond(ShapeColor shapeColor) {

    }

    @Override
    public void setShapeColorPrimary(ShapeColor shapeColor) {

    }

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = GroupStartPoint;

        for (Ithing s : children) {
            ((IShape) s).setStartPoint(startPoint);
        }
    }

    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = GroupEndPoint;

        for (Ithing s : children) {
            ((IShape) s).setEndPoint(endPoint);
        }

    }

    @Override
    public void setWidth(int width) {
        for (Ithing s : children) {
            ((IShape) s).setWidth(width);
        }
    }

    @Override
    public void setHeight(int height) {
        for (Ithing s : children) {
            ((IShape) s).setHeight(height);
        }
    }

    @Override
    public void setShapeType(int i) {
        for (Ithing s : children) {
            ((IShape) s).setShapeType(i);
        }
    }

    @Override
    public void setCopyCount() {

    }

    @Override
    public void deductCopyCount() {

    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public ShapeColor getShapeColorPrimary() {
        return ShapeColor.YELLOW;
    }

    @Override
    public ShapeColor getShapeColorSecond() {
        return ShapeColor.YELLOW;
    }

    @Override
    public ShapeShadingType getShapeShadingType() {
        return OUTLINE;
    }

    @Override
    public Point getStartPoint() {
        return this.startPoint;
    }

    @Override
    public Point getEndPoint() {
        return this.endPoint;
    }

    @Override
    public boolean getSelect() {
        return false;
    }

    @Override
    public int getCopyCount() {
        return 0;
    }

    @Override
    public boolean getReverse() {
        return false;
    }

    @Override
    public int getShapeType() {
        return 0;
    }

    @Override
    public void update(IObserver observer, Graphics2D g) {


//        draw(g);
        for (Ithing s : children) {
            System.out.println("update!!!!!!!!!!!!!!!!!");
            ((IShape) s).update((IShape) s, g);
        }

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.MAGENTA);

        ShapeFactory shapeFactory = new ShapeFactory();
        IShape newShape = shapeFactory.createRectangle();
        //create a shape
        newShape.setStartPoint(startPoint);
        newShape.setEndPoint(endPoint);
        newShape.setShapeColorPrimary(ShapeColor.YELLOW);


        int width;
        int height;
        width = Math.abs(endPoint.getX() - startPoint.getX());
        height = Math.abs(endPoint.getY() - startPoint.getY());

        newShape.setWidth(width);
        newShape.setHeight(height);
        this.width = width;
        this.height = height;


        IStrategy rectangleDraw;
        rectangleDraw = new RectangleOutlineDecorator(newShape);
        rectangleDraw.draw(g);

    }

    @Override
    public void run() {

    }
}
