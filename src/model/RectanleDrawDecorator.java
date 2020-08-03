package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public abstract class RectanleDrawDecorator implements IStrategy {

    private IShape shape;
    private Point endPoint;
    private Point startPoint;

    private Graphics2D g;


    public RectanleDrawDecorator(IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {
        startPoint = shape.getStartPoint();
        endPoint = shape.getEndPoint();

        if (shape.getSelect()) {
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
            g.setColor(Color.MAGENTA);
            g.drawRect(startPoint.getX() - 5, startPoint.getY() - 5, shape.getWidth() + 10, shape.getHeight() + 10);
        }

    }

    public abstract void specialDraw();
}
