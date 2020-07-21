package model.interfaces;

import java.awt.*;

public interface IObserver {
    void update(IObserver observer, Graphics2D g);
}
