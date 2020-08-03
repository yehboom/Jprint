package model;


import model.Point;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

import static model.ShapeShadingType.*;

public class RectangleStrategy implements IStrategy {

    private IShape shape;


    public RectangleStrategy(IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        IStrategy rectangleDraw;

        if(shape.getShapeShadingType().compareTo(FILLED_IN)==0){
            // System.out.println("FILLED_IN");
            rectangleDraw = new RectangleFillInDecorator(shape);
        }else if(shape.getShapeShadingType().compareTo(OUTLINE)==0){
            //System.out.println("OUTLINE");
            rectangleDraw = new RectangleOutlineDecorator(shape);
        }else if(shape.getShapeShadingType().compareTo(OUTLINE_AND_FILLED_IN)==0){
            //System.out.println("OUTLINE_AND_FILLED_IN");
            rectangleDraw = new RectangleOutlineAndFilledInDecorator(shape);
        } else {
            rectangleDraw = new NullDrawDecorator();
        }
        rectangleDraw.draw(g);
    }


}
