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
        if(shape.getShapeShadingType().compareTo(FILLED_IN)==0){
            System.out.println("FILLED_IN");

        }else if(shape.getShapeShadingType().compareTo(OUTLINE)==0){

            System.out.println("OUTLINE");
        }else if(shape.getShapeShadingType().compareTo(OUTLINE_AND_FILLED_IN)==0){
            System.out.println("OUTLINE_AND_FILLED_IN");

        }
        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        startPoint=shape.getStartPoint();
        endPoint=shape.getEndPoint();
        g.fillPolygon(new int[] {startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[] {startPoint.getY(), endPoint.getY(),  endPoint.getY()}, 3);
    }
}
