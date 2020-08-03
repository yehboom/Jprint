package model;

import model.interfaces.IShape;

import java.awt.*;

public class TriangleOutlineDecorator implements TriangleDrawDecorator {

    private Point startPoint;
    private IShape shape;
    private Point endPoint;
    private Graphics2D g;

    TriangleOutlineDecorator(IShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {
        startPoint = shape.getStartPoint();
        endPoint = shape.getEndPoint();

        g.setStroke(new BasicStroke(5));
        g.drawPolygon(new int[]{startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);
    }
}
