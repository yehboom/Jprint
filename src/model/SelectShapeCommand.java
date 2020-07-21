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
import java.util.List;

public class SelectShapeCommand implements ICommand, ISubject {
    private Point startPoint;
    private Point endPoint;
    private IShape newShape;
    private ShapeStore store;
    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private IStrategy strategy;

    private ArrayList<IShape> shapeList;

    private List<IObserver> observers = new ArrayList<>();


    public SelectShapeCommand(Point startPoint, Point endPoint, ShapeStore store, PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.store = store;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        store.cleanSelectShapeList();

    }

    @Override
    public void run() {


        shapeList = store.getShapeList();
        System.out.println("shapeListSize: " + shapeList.size());
        System.out.println("selectShapeList Size" + store.getSelectShapeList().size());

        //this is mouse start point and end point
        java.awt.Rectangle r = new java.awt.Rectangle();

        int width;
        int height;

        if (endPoint.getX() < startPoint.getX() && endPoint.getY() < startPoint.getY()) {
            //reverse direction
            width = startPoint.getX() - endPoint.getX();
            height = startPoint.getY() - endPoint.getY();


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
            //registerObserver
            registerObserver(s);

            java.awt.Rectangle r2 = new java.awt.Rectangle();


            //fix for the triangle different direction
            if (s.getStartPoint().getX() > s.getEndPoint().getX() && s.getStartPoint().getY() < s.getEndPoint().getY()) {
                r2.setRect(s.getEndPoint().getX(), s.getStartPoint().getY(), s.getWidth(), s.getHeight());
            } else if (s.getStartPoint().getX() < s.getEndPoint().getX() && s.getStartPoint().getY() > s.getEndPoint().getY()) {
                r2.setRect(s.getStartPoint().getX(), s.getEndPoint().getY(), s.getWidth(), s.getHeight());
            } else {
                r2.setRect(s.getStartPoint().getX(), s.getStartPoint().getY(), s.getWidth(), s.getHeight());
            }

            graphics2d.setColor(Color.RED);
            graphics2d.draw(r2);

            //click and drag select
            if (r.intersection(r2).height > 0 && r.intersection(r2).getWidth() > 0 ||
                    (startPoint.getX() < (r2.x + r2.width) && startPoint.getY() < (r2.y + r2.height) && startPoint.getX() > r2.x && startPoint.getY() > r2.y)) {
                System.out.println("intersection");
                System.out.println("height: " + r.intersection(r2).height);

                store.addSelectShape(s);
                java.awt.Rectangle r3 = r.intersection(r2);
                graphics2d.setColor(Color.GREEN);
                graphics2d.draw(r3);


            } else {
                //System.out.println("Not intersection!!!!");
            }

        }


//        //test
//        java.awt.Rectangle r2=new java.awt.Rectangle();
//        r2.setRect(startPoint.getX()+100, startPoint.getY()+100, 100, 100);


        System.out.println("In select shape command");

    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

}
