package sample;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class Rhombus extends Obstacles{
    private final Rectangle r1;
    private final Rectangle r2;
    private final Rectangle r3;
    private final Rectangle r4;
    private final Group g = new Group();
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private final RotateTransition r = new RotateTransition();
    private final ArrayList<Rectangle> rhombusArr = new ArrayList<>();
    Rhombus(double lenX, double lenY){
        r1 = new Rectangle();
        r1.setHeight(12);
        r1.setWidth(200);
        r1.setY(lenY);
        r1.setX(lenX);
        r1.setFill(Color.RED);
        r1.setArcWidth(15);
        r1.setArcHeight(15);

        r2 = new Rectangle();
        r2.setHeight(12);
        r2.setWidth(200);
        r2.setY(lenY+173);
        r2.setX(lenX-100);
        r2.setFill(Color.BLUE);
        r2.setArcWidth(15);
        r2.setArcHeight(15);

        r3 = new Rectangle();
        r3.setHeight(200);
        r3.setWidth(12);
        r3.setY(lenY-5);
        r3.setX(lenX-55);
        r3.setFill(Color.YELLOW);
        r3.setArcWidth(15);
        r3.setArcHeight(15);

        r4 = new Rectangle();
        r4.setHeight(200);
        r4.setWidth(12);
        r4.setY(lenY-5);
        r4.setX(lenX+135);
        r4.setFill(Color.VIOLET);
        r4.setArcWidth(15);
        r4.setArcHeight(15);
        r3.setRotate(30);
        r4.setRotate(30);

        starView.setX(210);
        starView.setY(((2*lenY)+173)/2);
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);


        rhombusArr.add(r1);
        rhombusArr.add(r2);
        rhombusArr.add(r3);
        rhombusArr.add(r4);
        g.getChildren().add(r1);
        g.getChildren().add(r2);
        g.getChildren().add(r3);
        g.getChildren().add(r4);
    }

    public ArrayList<Rectangle> getRhombus(){
        return rhombusArr;
    }

    @Override
    public void move() {
        this.r.setNode(this.g);
//        r.setAxis(Rotate.);
        r.setByAngle(360);
        r.setCycleCount(100);
        r.setDuration(Duration.millis(700));
        r.play();
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
        for (Rectangle rectangle : rhombusArr) {
            rectangle.setY(rectangle.getY() - temp);
        }
        this.getStarView().setY(this.getStarView().getY()-temp);
    }
    public ImageView getStarView(){
        return starView;
    }

    public void removeStar(){
        this.starView.setImage(null);
    }
}
