package model;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;
import model.interfaces.ISubject;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class SelectShapeCommand implements ICommand, ISubject {
    private Point startPoint;
    private Point endPoint;
    private IShape newShape;
    private ShapeStore store;
    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private IStrategy strategy;

    private ArrayList<IShape> shapeList;


    public SelectShapeCommand(Point startPoint, Point endPoint, ShapeStore store, PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.store = store;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        shapeList = store.getShapeList();
    }

    @Override
    public void run() {


        //this is mouse start point and end point
        java.awt.Rectangle r = new java.awt.Rectangle();

        int width;
        int height;

        if (endPoint.getX() < startPoint.getX() && endPoint.getY() < startPoint.getY()) {
            //reverse direction
            width = startPoint.getX() - endPoint.getX();
            height = startPoint.getY() - endPoint.getY();

            // newShape.setStartPoint(endPoint);
            // newShape.setEndPoint(startPoint);
            r.setRect(endPoint.getX(), endPoint.getY(), width, height);

        } else {
            width = endPoint.getX() - startPoint.getX();
            height = endPoint.getY() - startPoint.getY();
            r.setRect(startPoint.getX(), startPoint.getY(), width, height);
        }


        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.MAGENTA);
        graphics2d.draw(r);

        //need to iteration the shape list and find collision
        //taking out the shape from the list
        for (IShape s : shapeList) {
            java.awt.Rectangle r2 = new java.awt.Rectangle();
            r2.setRect(s.getStartPoint().getX(), s.getStartPoint().getY(), s.getWidth(), s.getHeight());
            graphics2d.setColor(Color.RED);
            graphics2d.draw(r2);


            if (r.intersection(r2).height > 0 && r.intersection(r2).getWidth() > 0) {
                System.out.println("intersection");
                System.out.println("height: " + r.intersection(r2).height);

                store.addSelectShape(s);
                java.awt.Rectangle r3 = r.intersection(r2);
                graphics2d.setColor(Color.GREEN);
                graphics2d.draw(r3);


            } else {
                System.out.println("Not intersection!!!!");
            }

        }


//        //test
//        java.awt.Rectangle r2=new java.awt.Rectangle();
//        r2.setRect(startPoint.getX()+100, startPoint.getY()+100, 100, 100);


        System.out.println("In select shape command");

    }

    @Override
    public void registerObserver(IObserver observer) {

    }

    @Override
    public void removeObserver(IObserver observer) {

    }

}
