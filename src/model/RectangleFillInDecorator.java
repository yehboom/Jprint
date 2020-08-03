package model;

import model.interfaces.IShape;

import java.awt.*;

public class RectangleFillInDecorator extends RectanleDrawDecorator {
    private Point startPoint;
    private IShape shape;
    private Point endPoint;
    private Graphics2D g;


    public RectangleFillInDecorator(IShape shape) {
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
        endPoint = shape.getEndPoint();

        g.fillRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

    }
}
