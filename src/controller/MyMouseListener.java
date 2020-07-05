package controller;


import model.Point;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {

    private Point startPoint;
    private PaintCanvasBase paintCanvas;

    public MyMouseListener(PaintCanvasBase paintCanvas){
        this.paintCanvas=paintCanvas;
    }

    @Override
    public void mousePressed(MouseEvent e){
        startPoint=new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e){


        // Filled in rectangle
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);

        int width;
        int height;

        if(e.getX()<startPoint.getX() && e.getY()<startPoint.getY()){
            width =startPoint.getX()- e.getX();
            height = startPoint.getY()-e.getY();
            graphics2d.fillRect(e.getX(), e.getY(), width, height);
        }else {
            width = e.getX()-startPoint.getX();
            height = e.getY()-startPoint.getY();
            graphics2d.fillRect(startPoint.getX(), startPoint.getY(), width, height);
        }


    }

}
