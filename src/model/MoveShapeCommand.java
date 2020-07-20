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

public class MoveShapeCommand implements ICommand, ISubject {
    private Point startPoint;
    private Point endPoint;
    private IShape newShape;
    private ShapeStore store;
    private PaintCanvasBase paintCanvas;
    private ApplicationState appState;
    private IStrategy strategy;

    private ArrayList<IShape> shapeList;

    private List<IObserver> observers = new ArrayList<>();


    public MoveShapeCommand(Point startPoint, Point endPoint, ShapeStore store, PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.store = store;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        shapeList = store.getShapeList();
    }

    @Override
    public void run() {
        Graphics2D g = paintCanvas.getGraphics2D();

        int moveX = endPoint.getX() - startPoint.getX();
        int moveY = endPoint.getY() - startPoint.getY();

        for (IShape s : store.getSelectShapeList()) {
            Point tempStartPoint = s.getStartPoint();
            Point tempEndPoint = s.getEndPoint();

            int tempStartPonintX = tempStartPoint.getX() + moveX;
            int tempStartPointY = tempStartPoint.getY() + moveY;

            int tempEndPointX = tempEndPoint.getX() + moveX;
            int tempEndPointY = tempEndPoint.getY() + moveY;

            s.setStartPoint(new Point(tempStartPonintX, tempStartPointY));
            s.setEndPoint(new Point(tempEndPointX, tempEndPointY));

            g.setColor(Color.GREEN);
            g.fillRect(s.getStartPoint().getX(), s.getStartPoint().getY(), s.getWidth(), s.getHeight());


        }

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
