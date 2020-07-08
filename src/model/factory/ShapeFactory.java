package model.factory;

import model.Rectangle;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;

public class ShapeFactory implements IShapeFactory {
    public IShape createRectangle(){
        return new Rectangle();
    }
}
