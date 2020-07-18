package model.interfaces;

import model.Point;
import model.ShapeColor;

public interface IShape {
    void setShapeColorPrimary(ShapeColor shapeColor);
    void setStartPoint(Point startPoint);
    void setEndPoint(Point endPoint);
    void setWidth(int width);
    void setHeight(int height);
    int getWidth();
    int getHeight();
    ShapeColor getShapeColorPrimary();
    Point getStartPoint();
    Point getEndPoint();


}
