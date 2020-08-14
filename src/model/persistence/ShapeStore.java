package model.persistence;

import model.interfaces.IShape;
import model.interfaces.Ithing;

import java.util.ArrayList;

public class ShapeStore {

    private ArrayList<Ithing> shapeList;

    private ArrayList<Ithing> selectShapeList;

    //constructor
    public ShapeStore() {

        shapeList = new ArrayList<>();
        selectShapeList = new ArrayList<>();
    }

    public void addShape(Ithing s) {
        shapeList.add(s);
    }

    public ArrayList<Ithing> getShapeList() {
        return this.shapeList;
    }

    public void addSelectShape(Ithing s) {
        selectShapeList.add(s);
    }

    public ArrayList<Ithing> getSelectShapeList() {
        return this.selectShapeList;
    }

    public void cleanSelectShapeList() {
        this.selectShapeList = new ArrayList<>();
    }


}
/*
public class ShapeStore {

    private ArrayList<IShape> shapeList;

    private ArrayList<IShape> selectShapeList;

    //constructor
    public ShapeStore(){

        shapeList=new ArrayList<>();
        selectShapeList = new ArrayList<>();
    }

    public void addShape(IShape s){
        shapeList.add(s);
    }

    public ArrayList<IShape> getShapeList() {
        return this.shapeList;
    }

    public void addSelectShape(IShape s) {
        selectShapeList.add(s);
    }

    public ArrayList<IShape> getSelectShapeList() {
        return this.selectShapeList;
    }

    public void cleanSelectShapeList() {
        this.selectShapeList = new ArrayList<>();
    }


}
 */
