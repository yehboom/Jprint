package model;

import model.factory.ShapeFactory;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;
import model.interfaces.Ithing;

import java.awt.*;

public class Ellipse implements IShape, IComponent, Ithing {

    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;
    private ShapeColor shapeColorPrimary;
    private ShapeColor shapeColorSecond;
    private ShapeShadingType shapeShadingType;
    private boolean select;
    private boolean reverse;
    private int copyCount = 0;
    private int shapeType;
    private int moveX;
    private int moveY;



    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;

    //set default shape
    public Ellipse(){
        this.select = false;
        this.shapeType = 0;

    }

    public void setReverse(Boolean b) {
        this.reverse = b;
    }
    public void setSelect(Boolean b) {
        this.select = b;
    }
    public void setShapeShadingType(ShapeShadingType shapeShadingType){
        this.shapeShadingType=shapeShadingType;
    }

    public void setShapeType(int i) {
        this.shapeType = i;
    }


    public void setShapeColorSecond(ShapeColor shapeColorSecond){
        this.shapeColorSecond=shapeColorSecond;
    }

    public void setShapeColorPrimary(ShapeColor shapeColorPrimary){
        this.shapeColorPrimary=shapeColorPrimary;
    }

    public void setStartPoint(Point startPoint){
        this.startPoint=startPoint;
    }

    public void setEndPoint(Point endPoint){
        this.endPoint=endPoint;
    }

    public void setWidth(int width){
        this.width=width;
    }

    public void setHeight(int height){
        this.height=height;
    }

    public void setCopyCount() {
        if (copyCount != 0) {
            this.copyCount += 50;
        } else {
            copyCount++;
        }
    }

    public void setMovexMovey(int moveX, int moveY) {
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public void deductCopyCount() {
        if (copyCount != 0) {
            this.copyCount -= 50;
        } else {
            copyCount--;
        }

    }

    public int getCopyCount() {
        return this.copyCount + 50;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Point getStartPoint(){
        return  startPoint;
    }

    public Point getEndPoint(){
        return endPoint;
    }

    public String toString(){
        return "Ellipse";
    }

    public ShapeColor getShapeColorPrimary(){
        return shapeColorPrimary;
    }

    public ShapeColor getShapeColorSecond(){
        return shapeColorSecond;
    }

    public ShapeShadingType getShapeShadingType(){
        return this.shapeShadingType;
    }

    public boolean getSelect() {
        return this.select;
    }

    public boolean getReverse() {
        return this.reverse;
    }

    public int getShapeType() {
        return this.shapeType;
    }

    public IShape getClone() {
        ShapeFactory shapeFactory = new ShapeFactory();
        IShape newShape = shapeFactory.createEllipse();
        newShape.setShapeType(shapeType);
        newShape.setHeight(height);
        newShape.setWidth(width);
        newShape.setStartPoint(startPoint);
        newShape.setEndPoint(endPoint);
        newShape.setShapeShadingType(shapeShadingType);
        newShape.setShapeColorSecond(shapeColorSecond);
        newShape.setShapeColorPrimary(shapeColorPrimary);
        return newShape;

    }


    @Override
    public void update(IObserver observer, Graphics2D g) {
//        System.out.println("update");
        IStrategy strategy = new EllipseStrategy((IShape) observer);
        strategy.draw(g);
    }
}
