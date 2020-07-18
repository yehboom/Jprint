package model.interfaces;

import model.Point;
import model.ShapeColor;
import model.ShapeShadingType;

public interface IShape {
    void setShapeShadingType(ShapeShadingType shapeShadingType);
    void setShapeColorSecond(ShapeColor shapeColor);
    void setShapeColorPrimary(ShapeColor shapeColor);
    void setStartPoint(Point startPoint);
    void setEndPoint(Point endPoint);
    void setWidth(int width);
    void setHeight(int height);
    int getWidth();
    int getHeight();
    ShapeColor getShapeColorPrimary();
    ShapeColor getShapeColorSecond();
    ShapeShadingType getShapeShadingType();
    Point getStartPoint();
    Point getEndPoint();



}
