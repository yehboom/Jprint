package model;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class Ellipse implements IShape {

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



    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;

    //set default shape
    public Ellipse(){
        select = false;
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


    @Override
    public void update(IObserver observer, Graphics2D g) {
        System.out.println("update");
        IStrategy strategy = new EllipseStrategy((IShape) observer);
        strategy.draw(g);
    }
}
