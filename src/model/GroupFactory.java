package model;

import model.interfaces.Ithing;

import java.awt.*;
import java.util.List;

public class GroupFactory {


    public static Ithing createShapeGroup(List<Ithing> children, Graphics2D g) {
        return new Group(children, g);//Need to override the implement in the group
    }
}
