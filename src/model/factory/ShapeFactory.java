package model.factory;

import model.Ellipse;
import model.NullShape;
import model.Rectangle;
import model.Triangle;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;

public class ShapeFactory implements IShapeFactory {
    public IShape createRectangle(){
        return new Rectangle();
    }
    public IShape createTriangle(){
        return new Triangle();
    }

    public IShape createEllipse(){
        return new Ellipse();
    }

    public IShape createNullShape() {
        return new NullShape();
    }

}
