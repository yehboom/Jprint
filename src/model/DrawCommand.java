package model;

import model.interfaces.IShape;
import model.interfaces.IStrategy;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;


public class DrawCommand implements ICommand {
    //private IShape shape;
    private PaintCanvasBase paintCanvas;
    private Point startPoint;
    private Point endPoint;

    private IStrategy strategy;

    //constructor
    DrawCommand(IStrategy Strategy, PaintCanvasBase paintCanvas){

        this.strategy=Strategy;
        this.paintCanvas=paintCanvas;
    }




    @Override
    public void run(){
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        //strategy Use to draw
        strategy.draw(graphics2d);
    }
}
