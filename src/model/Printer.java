package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.List;

public class Printer {


    public static void print(List<IShape> allList, Graphics2D g, PaintCanvasBase paintCanvas) {

        g.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());
        g.setColor(Color.white);
        g.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        for (IShape s : allList) {
            s.update(s, g);
        }
    }
}
