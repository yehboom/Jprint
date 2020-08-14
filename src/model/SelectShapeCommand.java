package model;

import model.factory.ShapeFactory;
import model.interfaces.*;
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

    private ArrayList<Ithing> shapeList;




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


        Graphics2D graphics2d = paintCanvas.getGraphics2D();

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
//        graphics2d.setColor(Color.MAGENTA);
//        graphics2d.draw(r);


        for (Ithing s1 : shapeList) {
            IShape s = (IShape) s1;

            s.setSelect(false);

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

                if (s.getSelect() == false) {
                    s.setSelect(true);
                }


            }

        }
        //set select list to appState and wait it for further movement
        appState.setSelectList(store.getSelectShapeList());


        graphics2d.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        System.out.println("select list's shapelist size!!!!!:" + shapeList.size());
        for (Ithing s1 : shapeList) {
            IShape s = (IShape) s1;

            s.update(s, graphics2d);
        }
    }


}
