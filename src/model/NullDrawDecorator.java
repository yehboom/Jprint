package model;

import model.interfaces.IStrategy;

import java.awt.*;

public class NullDrawDecorator implements IStrategy {
    @Override
    public void draw(Graphics2D g) {
        //DoNothing
    }
}
