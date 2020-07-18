package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

public class Rectangle implements IShape {
    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;
    private ShapeColor shapeColorPrimary;
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private IStrategy iStrategy;

    //set default shape
    public Rectangle(){

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
        return "Rectangle";
    }

    public ShapeColor getShapeColorPrimary(){
        return shapeColorPrimary;
    }

}
