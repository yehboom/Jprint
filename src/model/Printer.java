package model;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.Ithing;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.List;

public class Printer {


    public static void print(List<Ithing> allList, Graphics2D g, PaintCanvasBase paintCanvas) {
        System.out.println("print!!!");

        g.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
        g.setColor(Color.white);
        g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        for (Ithing s : allList) {
            ((IShape) s).update((IObserver) s, g);
        }
    }
}
