package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;
import model.persistence.ApplicationState;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;


public class DrawCommand implements ICommand {
    //private IShape shape;
    private PaintCanvasBase paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private ApplicationState appState;

    private IStrategy strategy;

    //constructor
    DrawCommand(IStrategy Strategy, PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.strategy=Strategy;
        this.paintCanvas=paintCanvas;
        this.appState = appState;
    }




    @Override
    public void run(){
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        //strategy Use to draw
        strategy.draw(graphics2d);

        System.out.println("DrawCommand");
//        CommandHistory.add(this);
    }

//    @Override
//    public void undo() {
//        appState.setDelete();
//        System.out.println("undo");
//    }
//
//    @Override
//    public void redo() {
//        Graphics2D graphics2d = paintCanvas.getGraphics2D();
//        //strategy Use to draw
//        strategy.draw(graphics2d);
//        System.out.println("redo");
//
//    }
}
