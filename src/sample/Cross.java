package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Cross extends Obstacles{
    private final Rectangle cross1;
    private final Rectangle cross2;
    private final ArrayList<Rectangle> cross = new ArrayList<>();
    private Rotate r1 = new Rotate();
    private double pivotX;
    private double pivotY;
    private Timeline timeline;

    Cross(double lenX, double lenY, Color c1, Color c2){
        cross1 = new Rectangle();
        cross1.setHeight(12);
        cross1.setWidth(150);
        cross1.setY(lenY);
        cross1.setX(lenX);
        cross1.setFill(c1);
        cross1.setArcWidth(15);
        cross1.setArcHeight(15);
        cross1.setRotate(90);

        cross2 = new Rectangle();
        cross2.setHeight(12);
        cross2.setWidth(150);
        cross2.setY(lenY);
        cross2.setX(lenX);
        cross2.setFill(c2);
        cross2.setArcWidth(15);
        cross2.setArcHeight(15);
        cross2.setRotate(180);

        pivotX = lenX+75;
        pivotY = lenY;
        r1.setPivotX(this.pivotX);
        r1.setPivotY(this.pivotY);
        cross1.getTransforms().add(r1);
        cross2.getTransforms().add(r1);
//        r1.getTransforms().add(r1);
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(r1.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(10), new KeyValue(r1.angleProperty(), 360)));
//        r1.setAxis(Rotate.Z_AXIS);
//        r1.setByAngle(360);
//        r1.setCycleCount(100);
//        r1.setDuration(Duration.millis(700));
//        r1.setNode(this.cross1);
//        r2.setAxis(Rotate.Z_AXIS);
//        r2.setByAngle(360);
//        r2.setCycleCount(100);
//        r2.setDuration(Duration.millis(700));
//        r2.setNode(this.cross2);
        cross.add(cross1); cross.add(cross2);
    }

    public ArrayList<Rectangle> getCross(){
        return cross;
    }

    @Override
    public void move() {
        timeline.play();
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
            rectangle.setY(rectangle.getY() - temp);
        }
        pivotY-=temp;
    }

    @Override
    public ImageView getStarView() {
        return null;
    }

    @Override
    public Shape getShape() {
        return this.cross1;
    }

}

