package model;

import model.interfaces.IShape;

import model.persistence.ShapeStore;
import view.interfaces.ICommand;


import java.util.List;

public class DeleteCommand implements ICommand, IUndoable {

    private ShapeStore store;


    public DeleteCommand(ShapeStore store) {

        this.store = store;

    }


    @Override
    public void run() {
        List<IShape> allList = store.getShapeList();
        List<IShape> selectList = store.getSelectShapeList();
//        System.out.println("Appstate allList size"+allList.size());
        if (allList.size() == 0) {
            return;
        }
//        System.out.println("Appstate select size"+selectList.size());
        for (IShape s : selectList) {
            allList.remove(s);
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
