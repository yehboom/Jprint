package model;

import model.interfaces.IShape;

import java.awt.*;

public class TriangleOutlineDecorator extends TriangleDrawDecorator {

    private Point startPoint;
    private IShape shape;
    private Point endPoint;
    private Graphics2D g;

    public TriangleOutlineDecorator(IShape shape) {
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
        //draw out line fill in etc
        startPoint = shape.getStartPoint();
        endPoint = shape.getEndPoint();
        g.setColor(shape.getShapeColorPrimary().getAwtColor());

        g.setStroke(new BasicStroke(5));
        g.drawPolygon(new int[]{startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);
    }



}
