package model;

import model.interfaces.IShape;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawCommand implements ICommand {
    private IShape shape;
    private PaintCanvasBase paintCanvas;
    private Point startPoint;

    //constructor
    DrawCommand(IShape shape, PaintCanvasBase paintCanvas){
        this.shape=shape;
        this.paintCanvas=paintCanvas;
    }


    @Override
    public void run(){
        // Filled in rectangle
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        startPoint=shape.getStartPoint();
        graphics2d.fillRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());

    }




}
