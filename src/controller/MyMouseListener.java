package controller;


import model.CreateShapeCommand;
import model.MoveShapeCommand;
import model.Point;
import model.SelectShapeCommand;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.ISubject;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static model.StartAndEndPointMode.*;

public class MyMouseListener extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvas;
    private ShapeStore shapeStore;
    private ApplicationState appState;
    private ICommand command;


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

        //draw mode
        if (appState.getActiveStartAndEndPointMode().compareTo(DRAW) == 0) {
            System.out.println("draw mode");
            command = new CreateShapeCommand(startPoint, endPoint, shapeStore, paintCanvas, appState);

        } else if (appState.getActiveStartAndEndPointMode().compareTo(SELECT) == 0) {
            System.out.println("select mode");
            command = new SelectShapeCommand(startPoint, endPoint, shapeStore, paintCanvas, appState);

        } else if (appState.getActiveStartAndEndPointMode().compareTo(MOVE) == 0) {
            System.out.println("Move mode");
            command = new MoveShapeCommand(startPoint, endPoint, shapeStore, paintCanvas, appState);
        }


        command.run();
    }



}
