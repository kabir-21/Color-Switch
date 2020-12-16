package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableDoubleValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.security.Key;
import java.util.ArrayList;

public class Square extends Obstacles{
    private final Line red;
    private final Line violet;
    private final Line blue;
    private final Line yellow;
    private double pivotX;
    private double pivotY;
    private final ArrayList<Line> square = new ArrayList<>();
    private Rotate r1 = new Rotate();
    private Timeline timeline;

    public Square(double lenX, double lenY) {
        red = new Line(lenX,lenY,lenX+200,lenY);
        red.setStroke(Color.RED);
        red.setStrokeWidth(12);
        red.setStrokeLineCap(StrokeLineCap.ROUND);

//        red = new Rectangle();
//        red.setHeight(12);
//        red.setWidth(200);
//        red.setX(lenX);
//        red.setY(lenY);
//        red.setFill(Color.RED);
        yellow = new Line(lenX,lenY,lenX, lenY+200);
//        violet = new Rectangle();
//        violet.setHeight(200);
//        violet.setWidth(12);
//        violet.setX(lenX);
//        violet.setY(lenY);
        yellow.setStroke(Color.YELLOW);
        yellow.setStrokeWidth(12);
        yellow.setStrokeLineCap(StrokeLineCap.ROUND);

        blue = new Line(lenX+200, lenY, lenX+200,lenY+200);
//        blue.setHeight(200);
//        blue.setWidth(12);
//        blue.setX(lenX+200);
//        blue.setY(lenY);
        blue.setStroke(Color.BLUE);
        blue.setStrokeWidth(12);
        blue.setStrokeLineCap(StrokeLineCap.ROUND);

        violet = new Line(lenX,lenY+200,lenX+200, lenY+200);
//        yellow.setHeight(12);
//        yellow.setWidth(217);
//        yellow.setX(120);
//        yellow.setY(lenY+200);
        violet.setStroke(Color.VIOLET);
        violet.setStrokeWidth(12);
        violet.setStrokeLineCap(StrokeLineCap.ROUND);

        pivotX = lenX+100;
        pivotY = lenY+100;
        r1.setPivotX(this.pivotX);
        r1.setPivotY(this.pivotY);
        red.getTransforms().add(r1);
        yellow.getTransforms().add(r1);
        violet.getTransforms().add(r1);
        blue.getTransforms().add(r1);
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(r1.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(10), new KeyValue(r1.angleProperty(), 360)));
        square.add(red); square.add(violet); square.add(blue); square.add(yellow);
    }

    public ArrayList<Line> getSquare(){
        return square;
    }

    @Override
    public void move() {
//        r1.setPivotX(this.getPivotX());
//        r1.setAxis();
//        r1.setPivotY(this.getPivotY());

        timeline.play();
//        Timeline tt = new Timeline(new KeyFrame(Duration.ZERO), new KeyValue(r1.angleProperty(),0), new KeyFrame(Duration.seconds(1), new KeyValue(r1.angleProperty())));
//        r1.setAngle(180);
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
        for (Line line : square) {
            line.setLayoutY(line.getLayoutY() - temp);
        }
        pivotY-=temp;
    }

    public double getPivotX() {
        return pivotX;
    }

    public void setPivotX(double pivotX) {
        this.pivotX = pivotX;
    }

    public double getPivotY() {
        return pivotY;
    }

    public void setPivotY(double pivotY) {
        this.pivotY = pivotY;
    }
}
