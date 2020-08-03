package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class TriangleOutlineAndFilledInDecorator implements IStrategy {
    private Point startPoint;
    private IShape shape;
    private Point endPoint;
    private Graphics2D g;

    TriangleOutlineAndFilledInDecorator(IShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {
        startPoint = shape.getStartPoint();
        endPoint = shape.getEndPoint();

        g.setStroke(new BasicStroke(5));
        g.fillPolygon(new int[]{startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);

        g.setColor(shape.getShapeColorSecond().getAwtColor());
        g.drawPolygon(new int[]{startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);

    }
}
