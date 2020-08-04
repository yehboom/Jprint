package model;

import model.interfaces.IStrategy;

import java.awt.*;

public class NullDrawDecorator implements IStrategy {
    private static NullDrawDecorator instance = new NullDrawDecorator();

    private NullDrawDecorator() {
    }

    public static NullDrawDecorator getInstance() {
        return instance;
    }

    @Override
    public void draw(Graphics2D g) {
        //DoNothing
    }
}
