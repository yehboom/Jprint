package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class TriangleStrategy implements IStrategy {

    private Point startPoint;
    private IShape shape;
    private Point endPoint;

    public TriangleStrategy( IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        startPoint=shape.getStartPoint();
        endPoint=shape.getEndPoint();
        g.fillPolygon(new int[] {startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[] {startPoint.getY(), endPoint.getY(),  endPoint.getY()}, 3);
    }
}
