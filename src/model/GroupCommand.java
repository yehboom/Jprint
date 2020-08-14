package model;

import model.interfaces.IShape;
import model.interfaces.Ithing;
import model.persistence.ShapeStore;
import view.interfaces.ICommand;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements IComponent, ICommand {

    private List<Ithing> children;
    private List<Ithing> selectShapeList;
    private ShapeStore store;
    private List<Ithing> allList;
    private Graphics2D g;

    public GroupCommand(List<Ithing> selectShapeList, ShapeStore store, Graphics2D g) {
        children = new ArrayList<>();
        this.selectShapeList = selectShapeList;
        this.store = store;
        this.g = g;
    }

    public void addChild(Ithing component) {
        children.add(component);
    }

    public int getSize() {
        return this.children.size();
    }

    @Override
    public void run() {
        allList = store.getShapeList();

        //remove select shape from the original shape list
        //add it to the list
        for (Ithing s : selectShapeList) {
            allList.remove(s);
            addChild(s);
        }

        //get the group from factory and put it into the main list
        Ithing newGroup = GroupFactory.createShapeGroup(children, g);
        allList.add(newGroup);


    }

}
