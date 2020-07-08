package model.persistence;

import model.interfaces.IShape;

import java.util.ArrayList;

public class ShapeStore {

    private ArrayList<IShape> shapeList;

    //constructor
    public ShapeStore(){
        shapeList=new ArrayList<>();
    }

    public void addShape(IShape s){
        shapeList.add(s);
    }

}
