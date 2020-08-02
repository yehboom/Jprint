package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class NullShapeStrategy implements IStrategy {
    private IShape shape;

    public NullShapeStrategy(IShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D g) {
        //it it null, do nothing
    }

    @Override
    public void drawSelect(Graphics2D g) {
        //it it null, do nothing
    }

}
