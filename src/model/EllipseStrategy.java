package model;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

import static model.ShapeShadingType.*;

public class EllipseStrategy implements IStrategy {
    private IShape shape;
    public EllipseStrategy( IShape shape) {
        this.shape = shape;
    }


    @Override
    public void draw(Graphics2D g) {

        g.setColor(shape.getShapeColorPrimary().getAwtColor());
        IStrategy ellipseDraw;

        if(shape.getShapeShadingType().compareTo(FILLED_IN)==0){
            //System.out.println("FILLED_IN");
            ellipseDraw = new EllipseFillInDecorator(shape);

        }else if(shape.getShapeShadingType().compareTo(OUTLINE)==0){
            // System.out.println("OUTLINE");
            ellipseDraw = new EllipseOutlineDecorator(shape);

        }else if(shape.getShapeShadingType().compareTo(OUTLINE_AND_FILLED_IN)==0){
            // System.out.println("OUTLINE_AND_FILLED_IN");
            ellipseDraw = new EllipseOutlineAndFilledInDecorator(shape);
        } else {
            ellipseDraw = NullDrawDecorator.getInstance();
        }
        ellipseDraw.draw(g);
    }


}
