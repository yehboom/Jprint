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
        //this.shape=shape;
        this.strategy=Strategy;
        this.paintCanvas=paintCanvas;
    }




    @Override
    public void run(){
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        strategy.draw(graphics2d);
//        Graphics2D graphics2d = paintCanvas.getGraphics2D();
//        if(shape.toString().equals("Rectangle")){
//            // Filled in rectangle
//            //Graphics2D graphics2d = paintCanvas.getGraphics2D();
//            graphics2d.setColor(Color.GREEN);
//            startPoint=shape.getStartPoint();
//            graphics2d.fillRect(startPoint.getX(), startPoint.getY(), shape.getWidth(), shape.getHeight());
//
//        }else if(shape.toString().equals("Triangle")){
//            // Filled in Triangle
//            //Graphics2D graphics2d = paintCanvas.getGraphics2D();
//            graphics2d.setColor(Color.RED);
//            startPoint=shape.getStartPoint();
//            endPoint=shape.getEndPoint();
//            graphics2d.fillPolygon(new int[] {startPoint.getX(), endPoint.getX(), startPoint.getX()}, new int[] {startPoint.getY(), endPoint.getY(),  endPoint.getY()}, 3);
//
//        }else if(shape.toString().equals("Ellipse")){
//            System.out.println("Ellipse");
//
////            //create ellipse
//            graphics2d.setColor(Color.YELLOW);
//            startPoint=shape.getStartPoint();
//            graphics2d.fillOval(startPoint.getX(),startPoint.getY(),shape.getWidth(),shape.getWidth());
//
//        }










    }




}
