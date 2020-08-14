package model;

import model.interfaces.*;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveShapeCommand implements ICommand, ISubject, IUndoable {
    private Point startPoint;
    private Point endPoint;
    private IShape newShape;
    private ShapeStore store;
    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private IStrategy strategy;

    private ArrayList<Ithing> shapeList;

    private List<IObserver> observers = new ArrayList<>();

    private ArrayList<Ithing> selectShapelist;


    public MoveShapeCommand(Point startPoint, Point endPoint, ShapeStore store, PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.store = store;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        shapeList = store.getShapeList();
        selectShapelist = store.getSelectShapeList();
    }

    @Override
    public void run() {
        move();
        CommandHistory.add(this);
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Graphics2D g) {
        for (IObserver observer : observers) {
            observer.update(observer, g);
        }
    }

    @Override
    public void undo() {
        Graphics2D g = paintCanvas.getGraphics2D();

        int moveX = endPoint.getX() - startPoint.getX();
        int moveY = endPoint.getY() - startPoint.getY();

        for (Ithing s1 : store.getShapeList()) {
            registerObserver((IShape) s1);
        }


        for (Ithing s : selectShapelist) {
            Point tempStartPoint = ((IShape) s).getStartPoint();
            Point tempEndPoint = ((IShape) s).getEndPoint();

            int tempStartPointX = tempStartPoint.getX() - moveX;
            int tempStartPointY = tempStartPoint.getY() - moveY;
            int tempEndPointX = tempEndPoint.getX() - moveX;
            int tempEndPointY = tempEndPoint.getY() - moveY;

            ((IShape) s).setStartPoint(new Point(tempStartPointX, tempStartPointY));
            ((IShape) s).setEndPoint(new Point(tempEndPointX, tempEndPointY));

        }
        g.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
        g.setColor(Color.white);
        g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        notifyObservers(g);

    }

    public void move() {
        Graphics2D g = paintCanvas.getGraphics2D();

        int moveX = endPoint.getX() - startPoint.getX();
        int moveY = endPoint.getY() - startPoint.getY();

        System.out.println("move list's shapelist size!!!!!:" + store.getShapeList().size());
        for (Ithing s1 : store.getShapeList()) {
            registerObserver((IShape) s1);
        }


        for (Ithing s : selectShapelist) {
            Point tempStartPoint = ((IShape) s).getStartPoint();
            Point tempEndPoint = ((IShape) s).getEndPoint();

            int tempStartPointX = tempStartPoint.getX() + moveX;
            int tempStartPointY = tempStartPoint.getY() + moveY;
            int tempEndPointX = tempEndPoint.getX() + moveX;
            int tempEndPointY = tempEndPoint.getY() + moveY;

            ((IShape) s).setStartPoint(new Point(tempStartPointX, tempStartPointY));
            ((IShape) s).setEndPoint(new Point(tempEndPointX, tempEndPointY));

        }
        g.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
        g.setColor(Color.white);
        g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());


        notifyObservers(g);

    }

    @Override
    public void redo() {
        move();

    }
}
