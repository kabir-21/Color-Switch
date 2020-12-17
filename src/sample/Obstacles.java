package sample;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public abstract class Obstacles {
    protected abstract void move();
    protected abstract double getPositionX();
    protected abstract double getPositionY();
    protected abstract void moveDown(double temp);
    public abstract ImageView getStarView();
    public abstract Shape getShape();
}
