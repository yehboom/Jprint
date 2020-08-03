package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

import static model.ShapeShadingType.*;

public class TriangleStrategy implements IStrategy {

    private IShape shape;


    public TriangleStrategy( IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {

        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        IStrategy triangleDraw;

        if(shape.getShapeShadingType().compareTo(FILLED_IN)==0){
            // System.out.println("FILLED_IN");
            triangleDraw = new TriangleFillInDecorator(shape);
        }else if(shape.getShapeShadingType().compareTo(OUTLINE)==0){
            //System.out.println("OUTLINE");
            triangleDraw = new TriangleOutlineDecorator(shape);
        }else if(shape.getShapeShadingType().compareTo(OUTLINE_AND_FILLED_IN)==0){
            //System.out.println("OUTLINE_AND_FILLED_IN");
            triangleDraw = new TriangleOutlineAndFilledInDecorator(shape);
        } else {
            triangleDraw = new NullDrawDecorator();
        }


        triangleDraw.draw(g);
        drawSelect(g);
        //if select so add drawselectStrategy

    }


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
