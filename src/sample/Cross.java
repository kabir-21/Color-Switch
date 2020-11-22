package sample;

import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Cross extends Obstacles{
    private Rectangle cross1;
    private Rectangle cross2;
    private ArrayList<Rectangle> cross = new ArrayList<>();

    Cross(double lenX, double lenY, Color c1, Color c2){
        this.cross1.setHeight(17);
        this.cross1.setWidth(150);
        this.cross1.setY(lenY);
        this.cross1.setX(lenX);
        this.cross1.setFill(c1);
        this.cross1.setArcWidth(15);
        this.cross1.setArcHeight(15);
        this.cross1.setRotate(90);

        this.cross2.setHeight(17);
        this.cross2.setWidth(150);
        this.cross2.setY(lenY);
        this.cross2.setX(lenX);
        this.cross2.setFill(c2);
        this.cross2.setArcWidth(15);
        this.cross2.setArcHeight(15);
        this.cross2.setRotate(180);
        cross.add(cross1); cross.add(cross2);
    }

    public ArrayList<Rectangle> getCross(){
        return cross;
    }

    @Override
    protected void move() {
        RotateTransition r1=new RotateTransition();
        r1.setAxis(Rotate.Z_AXIS);
        r1.setByAngle(360);
        r1.setCycleCount(100);
        r1.setDuration(Duration.millis(700));
        r1.setNode(this.cross1);
        RotateTransition r2=new RotateTransition();
        r2.setAxis(Rotate.Z_AXIS);
        r2.setByAngle(360);
        r2.setCycleCount(100);
        r2.setDuration(Duration.millis(700));
        r2.setNode(this.cross2);
        r1.play();
        r2.play();
    }

    @Override
    protected double getPositionX() {
        return 0;
    }

    @Override
    protected double getPositionY() {
        return 0;
    }

    @Override
    public void moveDown(double temp) {

    }
}
