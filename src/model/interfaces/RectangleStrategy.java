package model.interfaces;


import model.Point;

import java.awt.*;

public class RectangleStrategy implements IStrategy {


    private Point startPoint;
    private IShape shape;

    public RectangleStrategy( IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {

        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        startPoint=shape.getStartPoint();
        g.fillRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());
    }
}
