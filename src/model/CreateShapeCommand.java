package model;

import model.factory.ShapeFactory;
import model.interfaces.IShape;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

public class CreateShapeCommand implements ICommand {
    private Point startPoint;
    private Point endPoint;
    private IShape newShape;
    private ShapeStore store;
    private PaintCanvasBase paintCanvas;

    public CreateShapeCommand(Point startPoint, Point endPoint, ShapeStore store, PaintCanvasBase paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.store = store;
        this.paintCanvas = paintCanvas;
    }


    @Override
    public void run(){
        ShapeFactory shapeFactory=new ShapeFactory();
        //get shape from shapeFactory
        newShape=shapeFactory.createRectangle();

        //create a shape
        newShape.setStartPoint(startPoint);
        newShape.setEndPoint(endPoint);


        int width;
        int height;

        if(endPoint.getX()<startPoint.getX() && endPoint.getY()<startPoint.getY()){
            //reverse direction
            width =startPoint.getX()- endPoint.getX();
            height = startPoint.getY()-endPoint.getY();
            newShape.setStartPoint(endPoint);
            newShape.setEndPoint(startPoint);
        }else {
            width = endPoint.getX()-startPoint.getX();
            height = endPoint.getY()-startPoint.getY();
        }

        newShape.setWidth(width);
        newShape.setHeight(height);


        //add the new shape to the list
        store.addShape(newShape);

        //draw a shape
        ICommand d= new DrawCommand(newShape,paintCanvas);
        d.run();


    }

}
