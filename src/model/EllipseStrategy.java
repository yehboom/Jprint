package model;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

import static model.ShapeShadingType.*;

public class EllipseStrategy implements IStrategy, IObserver {
    private Point startPoint;
    private IShape shape;
    private Point endPoint;

    public EllipseStrategy( IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {

        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        startPoint=shape.getStartPoint();
        endPoint = shape.getEndPoint();



        if(shape.getShapeShadingType().compareTo(FILLED_IN)==0){
            System.out.println("FILLED_IN");
            g.fillOval(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

        }else if(shape.getShapeShadingType().compareTo(OUTLINE)==0){
            System.out.println("OUTLINE");
            g.setStroke(new BasicStroke(5));
            g.drawOval(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

        }else if(shape.getShapeShadingType().compareTo(OUTLINE_AND_FILLED_IN)==0){
            System.out.println("OUTLINE_AND_FILLED_IN");
            g.setStroke(new BasicStroke(5));
            g.fillOval(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());
            g.setColor(shape.getShapeColorSecond().getAwtColor());
            g.drawOval(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());


        }




    }

    @Override
    public void update() {
        shape.setSelect(true);
    }
}
