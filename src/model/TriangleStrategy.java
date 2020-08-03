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
    }
}
