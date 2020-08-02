package model;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class NullShape implements IShape {
    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;
    private boolean select;
    private boolean reverse;
    private boolean specialDirection;
    private int copyCount;
    private int shapeType = 0;

    private ShapeColor shapeColorPrimary;
    private ShapeColor shapeColorSecond;
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    private IStrategy iStrategy;
    private ShapeShadingType shapeShadingType;

    @Override
    public void setShapeType(int i) {
        this.shapeType = i;
    }

    @Override
    public void setReverse(Boolean b) {
        this.reverse = b;
    }

    @Override
    public void setSelect(Boolean b) {
        this.select = b;
    }

    @Override
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public void setShapeColorSecond(ShapeColor shapeColor) {
        this.shapeColorSecond = shapeColor;
    }

    @Override
    public void setShapeColorPrimary(ShapeColor shapeColor) {
        this.shapeColorPrimary = shapeColor;
    }

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void setWidth(int width) {
        this.width = 0;
    }

    @Override
    public void setHeight(int height) {
        this.width = 0;
    }

    @Override
    public void setCopyCount() {
        this.copyCount = 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public ShapeColor getShapeColorPrimary() {
        return null;
    }

    @Override
    public ShapeColor getShapeColorSecond() {
        return null;
    }

    @Override
    public ShapeShadingType getShapeShadingType() {
        return null;
    }

    @Override
    public Point getStartPoint() {
        return null;
    }

    @Override
    public Point getEndPoint() {
        return null;
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
    public void update(IObserver observer, Graphics2D g) {

    }

    public int getShapeType() {
        return this.shapeType;
    }
}
