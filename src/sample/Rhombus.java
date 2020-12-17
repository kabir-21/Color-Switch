package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Rhombus extends Obstacles{
    private final Line r1;
    private final Line r2;
    private final Line r3;
    private final Line r4;
    private double pivotX;
    private double pivotY;
    private Rotate rotate = new Rotate();
    private Timeline timeline;
    private final Group g = new Group();
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private final RotateTransition r = new RotateTransition();
    private final ArrayList<Line> rhombusArr = new ArrayList<>();
    Rhombus(double lenX, double lenY){
        r1 = new Line(lenX, lenY,lenX+200, lenY);
        r1.setStrokeWidth(12);
        r1.setStroke(Color.RED);
        r1.setStrokeLineCap(StrokeLineCap.ROUND);

        r2 = new Line(lenX, lenY,lenX-55, lenY+200);
        r2.setStrokeWidth(12);
        r2.setStroke(Color.BLUE);
        r2.setStrokeLineCap(StrokeLineCap.ROUND);

        r3 = new Line(lenX+200,lenY,lenX+145, lenY+200);
        r3.setStrokeWidth(12);
        r3.setStroke(Color.YELLOW);
        r3.setStrokeLineCap(StrokeLineCap.ROUND);

        r4 = new Line(lenX-55, lenY+200,lenX+145,lenY+200);
        r4.setStrokeWidth(12);
        r4.setStroke(Color.VIOLET);
        r4.setStrokeLineCap(StrokeLineCap.ROUND);

        pivotX = lenX+72.5;
        pivotY = lenY+96.14;
        rotate.setPivotX(this.pivotX);
        rotate.setPivotY(this.pivotY);
        starView.setX(pivotX);
        starView.setY(pivotY);
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);
        r1.getTransforms().add(rotate);
        r2.getTransforms().add(rotate);
        r3.getTransforms().add(rotate);
        r4.getTransforms().add(rotate);
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(10), new KeyValue(rotate.angleProperty(), 360)));

        rhombusArr.add(r1);
        rhombusArr.add(r2);
        rhombusArr.add(r3);
        rhombusArr.add(r4);
    }

    public ArrayList<Line> getRhombus(){
        return rhombusArr;
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
        for (Line line : rhombusArr) {
            line.setLayoutY(line.getLayoutY() - temp);
        }
        this.getStarView().setY(this.getStarView().getY()-temp);
        pivotY-=temp;

    }
    public ImageView getStarView(){
        return starView;
    }

    @Override
    public Shape getShape() {
        return this.r1;
    }

    public void removeStar(){
        this.starView.setImage(null);
    }
}
