package sample;

public abstract class Obstacles {
    protected abstract void move();
    protected abstract double getPositionX();
    protected abstract double getPositionY();
    public abstract void moveDown(double temp);
}
