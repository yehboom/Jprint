package model;

import model.factory.ShapeFactory;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.IStrategy;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectShapeCommand implements ICommand {
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


        //for display the select
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.MAGENTA);
        graphics2d.draw(r);
/////////////////////////////////


        for (IShape s : shapeList) {

            java.awt.Rectangle r2 = new java.awt.Rectangle();

            //fix for the triangle different direction (collision)
            if (s.getStartPoint().getX() > s.getEndPoint().getX() && s.getStartPoint().getY() < s.getEndPoint().getY()) {
                r2.setRect(s.getEndPoint().getX(), s.getStartPoint().getY(), s.getWidth(), s.getHeight());
            } else if (s.getStartPoint().getX() < s.getEndPoint().getX() && s.getStartPoint().getY() > s.getEndPoint().getY()) {
                r2.setRect(s.getStartPoint().getX(), s.getEndPoint().getY(), s.getWidth(), s.getHeight());
            } else {
                r2.setRect(s.getStartPoint().getX(), s.getStartPoint().getY(), s.getWidth(), s.getHeight());
            }


            //click and drag select
            if (r.intersection(r2).height > 0 && r.intersection(r2).getWidth() > 0 ||
                    (startPoint.getX() < (r2.x + r2.width) && startPoint.getY() < (r2.y + r2.height) && startPoint.getX() > r2.x && startPoint.getY() > r2.y)) {
                store.addSelectShape(s);


                if (s.toString().equals("Rectangle")) {
                    java.awt.Rectangle r3 = new java.awt.Rectangle();
                    r3.setRect(r2.getX() - 5, r2.getY() - 5, r2.getWidth() + 10, r2.getHeight() + 10);
                    Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
                    graphics2d.setStroke(stroke);
                    graphics2d.draw(r3);

                } else if (s.toString().equals("Ellipse")) {
                    System.out.println("it is not rectangle");
                    graphics2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
                    graphics2d.drawOval(s.getStartPoint().getX() - 5, s.getStartPoint().getY() - 5, s.getWidth() + 10, s.getHeight() + 10);

                } else if (s.toString().equals("Triangle")) {
                    System.out.println("it is not rectangle");
                    graphics2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
                    graphics2d.drawPolygon(new int[]{s.getStartPoint().getX() - 5, s.getEndPoint().getX() + 20, s.getStartPoint().getX() - 5},
                            new int[]{s.getStartPoint().getY() - 15, s.getEndPoint().getY() + 5, s.getEndPoint().getY() + 5}, 3);
                } else {
                    System.out.println("it is not rectangle");
                }





            }

        }
        //set select list to appState and wait it for further movement
        appState.setSelectList(store.getSelectShapeList());

    }


}
