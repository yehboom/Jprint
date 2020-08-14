package model;

import model.interfaces.IShape;

import model.interfaces.Ithing;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;


import java.awt.*;
import java.util.List;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeStore store;
    private PaintCanvasBase paintCanvas;
    private Graphics2D g;
    private List<Ithing> allList;
    private List<Ithing> selectList;


    public DeleteCommand(ShapeStore store, PaintCanvasBase paintCanvas, Graphics2D g, List<Ithing> selectList) {

        this.store = store;
        this.paintCanvas = paintCanvas;
        this.g = g;
        this.selectList = selectList;

    }


    @Override
    public void run() {
        allList = store.getShapeList();

        if (allList.size() == 0) {
            return;
        }

        for (Ithing s : selectList) {
            allList.remove(s);
        }

        Printer.print(allList, g, paintCanvas);
        CommandHistory.add(this);

    }

    @Override
    public void undo() {

        for (Ithing s : selectList) {
            allList.add(s);
        }

        Printer.print(allList, g, paintCanvas);


    }

    @Override
    public void redo() {
        delete();

        Printer.print(allList, g, paintCanvas);

    }

    public void delete() {

        if (allList.size() == 0) {
            return;
        }
        for (Ithing s : selectList) {
            allList.remove(s);
        }


    }
}
