package model;

import model.interfaces.IShape;

import java.awt.*;

public class EllipseOutlineAndFilledInDecorator extends EllipseDrawDecorator {
    private Point startPoint;
    private IShape shape;

    private Graphics2D g;

    public EllipseOutlineAndFilledInDecorator(IShape shape) {
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
        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        startPoint = shape.getStartPoint();

        g.setStroke(new BasicStroke(5));
        g.fillOval(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());
        g.setColor(shape.getShapeColorSecond().getAwtColor());
        g.drawOval(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());


    }
}
