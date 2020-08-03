package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class TriangleOutlineAndFilledInDecorator extends TriangleDrawDecorator {
    private Point startPoint;
    private IShape shape;
    private Point endPoint;
    private Graphics2D g;

    public TriangleOutlineAndFilledInDecorator(IShape shape) {
        super(shape);
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        this.g = g;
        specialDraw();
    }

    @Override
    public void specialDraw() {
        startPoint = shape.getStartPoint();
        endPoint = shape.getEndPoint();
        g.setColor(shape.getShapeColorPrimary().getAwtColor());

        g.setStroke(new BasicStroke(5));
        g.fillPolygon(new int[]{startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);

        g.setColor(shape.getShapeColorSecond().getAwtColor());
        g.drawPolygon(new int[]{startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);

    }
}
