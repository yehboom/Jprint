package model;


import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public abstract class TriangleDrawDecorator implements IStrategy {
    private IShape shape;

    private Graphics2D g;

    public TriangleDrawDecorator(IShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {
        //select
        if (shape.getSelect()) {
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
            g.setColor(Color.MAGENTA);

            if (shape.getShapeType() == 0) {
                g.drawPolygon(new int[]{shape.getStartPoint().getX() - 5, shape.getEndPoint().getX() + 20, shape.getStartPoint().getX() - 5},
                        new int[]{shape.getStartPoint().getY() - 15, shape.getEndPoint().getY() + 5, shape.getEndPoint().getY() + 5}, 3);
            } else if (shape.getShapeType() == 1) {
                g.drawPolygon(new int[]{shape.getStartPoint().getX() - 5, shape.getEndPoint().getX() + 15, shape.getStartPoint().getX() - 5},
                        new int[]{shape.getStartPoint().getY() + 15, shape.getEndPoint().getY() - 10, shape.getEndPoint().getY() - 10}, 3);
            } else {
                g.drawPolygon(new int[]{shape.getStartPoint().getX() + 5, shape.getEndPoint().getX() - 30, shape.getStartPoint().getX() + 5},
                        new int[]{shape.getStartPoint().getY() - 15, shape.getEndPoint().getY() + 5, shape.getEndPoint().getY() + 5}, 3);
            }

        }
    }

    public abstract void specialDraw();

}
