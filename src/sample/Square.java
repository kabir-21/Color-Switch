package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableDoubleValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private final ArrayList<Line> square = new ArrayList<>();
    private Rotate r1 = new Rotate();
    private Timeline timeline;

    public Square(double lenX, double lenY) {
        red = new Line(lenX,lenY,lenX+200,lenY);
        red.setStroke(Color.RED);
        red.setStrokeWidth(12);
        red.setStrokeLineCap(StrokeLineCap.ROUND);

        yellow = new Line(lenX,lenY,lenX, lenY+200);
        yellow.setStroke(Color.YELLOW);
        yellow.setStrokeWidth(12);
        yellow.setStrokeLineCap(StrokeLineCap.ROUND);

        blue = new Line(lenX+200, lenY, lenX+200,lenY+200);
        blue.setStroke(Color.BLUE);
        blue.setStrokeWidth(12);
        blue.setStrokeLineCap(StrokeLineCap.ROUND);

        violet = new Line(lenX,lenY+200,lenX+200, lenY+200);
        violet.setStroke(Color.VIOLET);
        violet.setStrokeWidth(12);
        violet.setStrokeLineCap(StrokeLineCap.ROUND);

        pivotX = lenX+100;
        pivotY = lenY+100;
        r1.setPivotX(this.pivotX);
        r1.setPivotY(this.pivotY);
        starView.setX(pivotY);
        starView.setY(pivotY);

        starView.setPreserveRatio(true);
        starView.setFitHeight(20);
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
        timeline.play();
    }

    @Override
    public double getPositionX() {
        return 0;
    }

    @Override
    public double getPositionY() {
        return this.pivotY;
    }

    @Override
    public void moveDown(double temp) {
        for (Line line : square) {
            line.setLayoutY(line.getLayoutY() - temp);
        }
        pivotY-=temp;
        this.getStarView().setY(this.getStarView().getY()-temp);
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
    public ImageView getStarView(){
        return starView;
    }

    @Override
    public Shape getShape() {
        return this.blue;
    }

    public void removeStar(){
        this.starView.setImage(null);
    }

}
