package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MyMouseListener;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        //initial a shape store for later store the shape
        ShapeStore store = new ShapeStore();
        ApplicationState appState = new ApplicationState(uiModule, paintCanvas, store);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();


        //add mouseListener to the canvas
        paintCanvas.addMouseListener(new MyMouseListener(paintCanvas,store,appState));


        // For example purposes only; remove all lines below from your final project.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        // Filled in rectangle
//        Graphics2D graphics2d = paintCanvas.getGraphics2D();
//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//
//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);

        // Clears the Canvas
        //paintCanvas.repaint();
    }
}
