package sample;

import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Cross extends Obstacles{
    private final Rectangle cross1;
    private final Rectangle cross2;
    private final ArrayList<Rectangle> cross = new ArrayList<>();
    public RotateTransition r1;
    public RotateTransition r2;

    Cross(double lenX, double lenY, Color c1, Color c2){
        cross1 = new Rectangle();
        cross1.setHeight(17);
        cross1.setWidth(150);
        cross1.setY(lenY);
        cross1.setX(lenX);
        cross1.setFill(c1);
        cross1.setArcWidth(15);
        cross1.setArcHeight(15);
        cross1.setRotate(90);

        cross2 = new Rectangle();
        cross2.setHeight(17);
        cross2.setWidth(150);
        cross2.setY(lenY);
        cross2.setX(lenX);
        cross2.setFill(c2);
        cross2.setArcWidth(15);
        cross2.setArcHeight(15);
        cross2.setRotate(180);
        cross.add(cross1); cross.add(cross2);
    }

    public ArrayList<Rectangle> getCross(){
        return cross;
    }

    @Override
    public void move() {
        r1=new RotateTransition();
        r1.setAxis(Rotate.Z_AXIS);
        r1.setByAngle(360);
        r1.setCycleCount(100);
        r1.setDuration(Duration.millis(700));
        r1.setNode(this.cross1);
        r2=new RotateTransition();
        r2.setAxis(Rotate.Z_AXIS);
        r2.setByAngle(360);
        r2.setCycleCount(100);
        r2.setDuration(Duration.millis(700));
        r2.setNode(this.cross2);
        r1.play();
        r2.play();
    }

    @Override
    public double getPositionX() {
        return 0;
    }

    @Override
    public double getPositionY() {
        return 0;
    }

    @Override
    public void moveDown(double temp) {
        for (Rectangle rectangle : cross) {
            rectangle.setY(rectangle.getY() - temp/10);
        }
    }
}
