package model;

import model.factory.ShapeFactory;
import model.interfaces.IShape;
import model.interfaces.IStrategy;
import model.interfaces.RectangleStrategy;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import static model.ShapeType.*;

public class CreateShapeCommand implements ICommand {
    private Point startPoint;
    private Point endPoint;
    private IShape newShape;
    private ShapeStore store;
    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private IStrategy strategy;



    public CreateShapeCommand(Point startPoint, Point endPoint, ShapeStore store, PaintCanvasBase paintCanvas,ApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.store = store;
        this.paintCanvas = paintCanvas;
        this.appState=appState;
    }



    @Override
    public void run(){
        System.out.println(appState.getActiveShapeType().compareTo(RECTANGLE));
        ShapeFactory shapeFactory=new ShapeFactory();

        if(appState.getActiveShapeType().compareTo(TRIANGLE)==0){
            System.out.println("Triangle  draw");
            //get shape from shapeFactory
            newShape=shapeFactory.createTriangle();

        }else if(appState.getActiveShapeType().compareTo(RECTANGLE)==0){
            //get shape from shapeFactory
            newShape=shapeFactory.createRectangle();

        }else if(appState.getActiveShapeType().compareTo(ELLIPSE)==0){
            //get shape from shapeFactory
            newShape=shapeFactory.createEllipse();

        }

        //create a shape
        newShape.setStartPoint(startPoint);
        newShape.setEndPoint(endPoint);

        newShape.setShapeColorPrimary(appState.getActivePrimaryColor());


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


        //factory method setting strategy
        switch (appState.getActiveShapeType()){
            case RECTANGLE:
                this.strategy=new RectangleStrategy(newShape);
                System.out.println("In Rectangle"+newShape.getHeight());
                break;
            case ELLIPSE:
                this.strategy=new EllipseStrategy(newShape);
                break;
            case TRIANGLE:
                this.strategy=new TriangleStrategy(newShape);
                break;
        }




        //draw a shape
        ICommand d= new DrawCommand(strategy,paintCanvas);
        d.run();




    }

}
