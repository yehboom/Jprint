package model;

import model.factory.ShapeFactory;
import model.interfaces.IShape;
import model.interfaces.IStrategy;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import static model.ShapeType.*;

public class CreateShapeCommand implements ICommand, IUndoable {
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
            //get shape from shapeFactory
            newShape=shapeFactory.createTriangle();

        }else if(appState.getActiveShapeType().compareTo(RECTANGLE)==0){
            //get shape from shapeFactory
            newShape=shapeFactory.createRectangle();

        }else if(appState.getActiveShapeType().compareTo(ELLIPSE)==0){
            //get shape from shapeFactory
            newShape=shapeFactory.createEllipse();

        } else {
            newShape = shapeFactory.createNullShape();
        }


        //create a shape
        newShape.setStartPoint(startPoint);
        newShape.setEndPoint(endPoint);
        newShape.setShapeColorPrimary(appState.getActivePrimaryColor());
        newShape.setShapeColorSecond(appState.getActiveSecondaryColor());
        newShape.setShapeShadingType(appState.getActiveShapeShadingType());



        int width;
        int height;
        width = Math.abs(endPoint.getX() - startPoint.getX());
        height = Math.abs(endPoint.getY() - startPoint.getY());

        if(endPoint.getX()<startPoint.getX() && endPoint.getY()<startPoint.getY()){
            //reverse direction
            width = Math.abs(startPoint.getX() - endPoint.getX());
            height = Math.abs(startPoint.getY() - endPoint.getY());
            newShape.setStartPoint(endPoint);
            newShape.setEndPoint(startPoint);
            newShape.setReverse(true);

            //deal with different direction troangle
        } else if (endPoint.getX() > startPoint.getX() && endPoint.getY() < startPoint.getY()) {
            newShape.setShapeType(1);
        } else if (endPoint.getX() < startPoint.getX() && endPoint.getY() > startPoint.getY()) {
            newShape.setShapeType(2);
        } else {
        }


        //(Ellipse and Rectangle) left to right and right to left direction
        if (appState.getActiveShapeType().compareTo(TRIANGLE) != 0) {
            if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) {
                startPoint = new Point(endPoint.getX(), startPoint.getY());
                newShape.setStartPoint(startPoint);
            } else if (startPoint.getX() < endPoint.getX() && startPoint.getY() > endPoint.getY()) {
                startPoint = new Point(startPoint.getX(), endPoint.getY());
                newShape.setStartPoint(startPoint);
            }
        }

        newShape.setWidth(width);
        newShape.setHeight(height);


        store.cleanSelectShapeList();
        //add the new shape to the list
        store.addShape(newShape);
        store.addSelectShape(newShape);
        appState.setSelectList(store.getSelectShapeList());


        // setting strategy
        switch (appState.getActiveShapeType()){
            case RECTANGLE:
                this.strategy=new RectangleStrategy(newShape);
                break;
            case ELLIPSE:
                this.strategy=new EllipseStrategy(newShape);
                break;
            case TRIANGLE:
                this.strategy=new TriangleStrategy(newShape);
                break;
            default:
                System.out.println("Invalid shape selected :" + appState.getActiveShapeType());
                this.strategy = NullShapeStrategy.getInstance();
                break;
        }




        //draw a shape
        ICommand d = new DrawCommand(strategy, paintCanvas, appState);

        d.run();
        CommandHistory.add(this);

    }

    @Override
    public void undo() {
        System.out.println("create undo");
        store.cleanSelectShapeList();
        store.addSelectShape(newShape);
        appState.setSelectList(store.getSelectShapeList());
        appState.deleteCreate();
    }

    @Override
    public void redo() {
        store.cleanSelectShapeList();
        store.addShape(newShape);
        store.addSelectShape(newShape);
        appState.setSelectList(store.getSelectShapeList());

        //draw a shape
        ICommand d = new DrawCommand(strategy, paintCanvas, appState);
        d.run();
    }
}
