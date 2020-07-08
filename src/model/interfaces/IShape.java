package model.interfaces;

import model.Point;

public interface IShape {
    void setStartPoint(Point startPoint);
    void setEndPoint(Point endPoint);
    void setWidth(int width);
    void setHeight(int height);
    int getWidth();
    int getHeight();
    Point getStartPoint();
    Point getEndPoint();


}
