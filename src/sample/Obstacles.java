package sample;

public abstract class Obstacles {
    protected abstract void move();
    protected abstract double getPositionX();
    protected abstract double getPositionY();
    protected abstract void moveDown(double temp);
}
