package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

import static model.ShapeShadingType.*;

public class TriangleStrategy implements IStrategy {

    private Point startPoint;
    private IShape shape;
    private Point endPoint;


    public TriangleStrategy( IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {

        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        startPoint=shape.getStartPoint();
        endPoint=shape.getEndPoint();


        if(shape.getShapeShadingType().compareTo(FILLED_IN)==0){
            // System.out.println("FILLED_IN");
            g.fillPolygon(new int[] {startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[] {startPoint.getY(), endPoint.getY(),  endPoint.getY()}, 3);

        }else if(shape.getShapeShadingType().compareTo(OUTLINE)==0){
            //System.out.println("OUTLINE");
            g.setStroke(new BasicStroke(5));
            g.drawPolygon(new int[] {startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[] {startPoint.getY(), endPoint.getY(),  endPoint.getY()}, 3);


        }else if(shape.getShapeShadingType().compareTo(OUTLINE_AND_FILLED_IN)==0){
            //System.out.println("OUTLINE_AND_FILLED_IN");
            g.setStroke(new BasicStroke(5));
            g.fillPolygon(new int[] {startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[] {startPoint.getY(), endPoint.getY(),  endPoint.getY()}, 3);

            g.setColor(shape.getShapeColorSecond().getAwtColor());
            g.drawPolygon(new int[] {startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[] {startPoint.getY(), endPoint.getY(),  endPoint.getY()}, 3);

        }
        drawSelect(g);

    }

    @Override
    public void drawSelect(Graphics2D g) {

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
                g.drawPolygon(new int[]{shape.getStartPoint().getX() + 5, shape.getEndPoint().getX() - 15, shape.getStartPoint().getX() + 5},
                        new int[]{shape.getStartPoint().getY() - 15, shape.getEndPoint().getY() + 10, shape.getEndPoint().getY() + 10}, 3);
            }

        }

    }
}
