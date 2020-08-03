package model;


import model.Point;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

import static model.ShapeShadingType.*;

public class RectangleStrategy implements IStrategy {

    private Point endPoint;
    private Point startPoint;
    private IShape shape;


    public RectangleStrategy(IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        startPoint=shape.getStartPoint();
        endPoint = shape.getEndPoint();

        if(shape.getShapeShadingType().compareTo(FILLED_IN)==0){
            // System.out.println("FILLED_IN");
            g.fillRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

        }else if(shape.getShapeShadingType().compareTo(OUTLINE)==0){
            //System.out.println("OUTLINE");
            g.setStroke(new BasicStroke(5));
            g.drawRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

        }else if(shape.getShapeShadingType().compareTo(OUTLINE_AND_FILLED_IN)==0){
            //System.out.println("OUTLINE_AND_FILLED_IN");
            g.setStroke(new BasicStroke(5));
            g.fillRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

            g.setColor(shape.getShapeColorSecond().getAwtColor());
            g.drawRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

        }
        //drawSelect(g);
    }

//    @Override
//    public void drawSelect(Graphics2D g) {
//        if (shape.getSelect()) {
//            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
//            g.setColor(Color.MAGENTA);
//            g.drawRect(startPoint.getX() - 5, startPoint.getY() - 5, shape.getWidth() + 10, shape.getHeight() + 10);
//        }
//
//    }

}
