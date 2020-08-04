package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;

import java.awt.*;

public class NullShapeStrategy implements IStrategy {
    private static NullShapeStrategy instance = new NullShapeStrategy();

    public NullShapeStrategy() {
    }

    public static NullShapeStrategy getInstance() {
        return instance;
    }

    @Override
    public void draw(Graphics2D g) {
        //it it null, do nothing
    }


}
