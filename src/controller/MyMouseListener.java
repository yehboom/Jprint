package controller;


import model.CreateShapeCommand;
import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvas;
    private ShapeStore shapeStore;
    private ApplicationState appState;



    public MyMouseListener(PaintCanvasBase paintCanvas, ShapeStore shapeStore, ApplicationState appState){
        this.paintCanvas=paintCanvas;
        this.shapeStore=shapeStore;
        this.appState=appState;
    }

    @Override
    public void mousePressed(MouseEvent e){

        startPoint=new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e){
        endPoint=new Point(e.getX(),e.getY());
        ICommand command=new CreateShapeCommand(startPoint,endPoint,shapeStore,paintCanvas,appState);
        command.run();
    }



}
