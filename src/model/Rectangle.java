package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

public class Rectangle implements IShape {
    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;
    private boolean select;
    private boolean reverse;
    private boolean specialDirection;
    private boolean directionLeftToRight;
    private boolean directionRightToLeft;


    private ShapeColor shapeColorPrimary;
    private ShapeColor shapeColorSecond;
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private IStrategy iStrategy;
    private ShapeShadingType shapeShadingType;

    //set default shape
    public Rectangle(){
        this.select = false;
    }

    @Override
    public void setDirectionLeftToRight(Boolean b) {
        this.directionLeftToRight = b;
    }

    @Override
    public void setDirectionRightToLeft(Boolean b) {
        this.directionRightToLeft = b;
    }

    public void setReverse(Boolean b) {
        this.reverse = b;
    }

    public void setSpecialDirection(Boolean b) {
        this.specialDirection = b;

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
    public boolean getDirectionLeftToRight() {
        return this.directionLeftToRight;
    }

    @Override
    public boolean getDirectionRightToLeft() {
        return this.directionRightToLeft;
    }

    public boolean getSpecialDirection() {
        return this.specialDirection;

    }

    @Override
    public void update() {
        System.out.println("update");
    }
}
