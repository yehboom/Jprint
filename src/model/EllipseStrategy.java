package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class EllipseStrategy implements IStrategy {
    private Point startPoint;
    private IShape shape;

    public EllipseStrategy( IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        startPoint=shape.getStartPoint();
        g.fillOval(startPoint.getX(),startPoint.getY(),shape.getWidth(),shape.getWidth());
    }
}
