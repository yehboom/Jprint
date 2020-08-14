package model.interfaces;

import java.awt.*;

public interface IObserver extends Ithing {
    void update(IObserver observer, Graphics2D g);
}
