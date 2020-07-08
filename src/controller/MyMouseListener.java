package controller;


import model.CreateShapeCommand;
import model.Point;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvas;
    private ShapeStore shapeStore;


    public MyMouseListener(PaintCanvasBase paintCanvas,ShapeStore shapeStore){
        this.paintCanvas=paintCanvas;
        this.shapeStore=shapeStore;
    }

    @Override
    public void mousePressed(MouseEvent e){
        startPoint=new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e){
        endPoint=new Point(e.getX(),e.getY());
        ICommand command=new CreateShapeCommand(startPoint,endPoint,shapeStore,paintCanvas);
        command.run();
    }

}
