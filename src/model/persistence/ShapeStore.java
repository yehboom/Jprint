package model.persistence;

import model.EllipseStrategy;
import model.TriangleStrategy;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.RectangleStrategy;

import java.util.ArrayList;

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

//    @Override
//    public void update() {
//
//        for (IShape s1 : shapeList) {
//            if (s1.toString().equals("Ellipse")) {
//                strategy = new EllipseStrategy(s1);
//            } else if (s1.toString().equals("Triangle")) {
//                strategy = new TriangleStrategy(s1);
//            } else if (s1.toString().equals("Rectangle")) {
//                strategy = new RectangleStrategy(s1);
//            }
//            strategy.draw(g);
//        }
//    }
}
