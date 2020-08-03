package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public abstract class EllipseDrawDecorator implements IStrategy {
    private IShape shape;

    public EllipseDrawDecorator(IShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {

        if (shape.getSelect()) {
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
            g.setColor(Color.MAGENTA);
            g.drawOval(shape.getStartPoint().getX() - 5, shape.getStartPoint().getY() - 5, shape.getWidth() + 10, shape.getHeight() + 10);

        }

    }

    public abstract void specialDraw();


}
