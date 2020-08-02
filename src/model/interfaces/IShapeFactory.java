package model.interfaces;

public interface IShapeFactory {
    IShape createRectangle();

    IShape createTriangle();

    IShape createEllipse();
}
