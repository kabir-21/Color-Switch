package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

import javax.lang.model.type.NullType;
import java.util.ArrayList;

public class Circle extends Obstacles {
    private final Arc arc1;
    private final Arc arc2;
    private final Arc arc3;
    private final Arc arc4;
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private final ArrayList<Arc> circle = new ArrayList<>();
    public Circle(float x, float y, float rx, float ry, float startAngle, float length, int width){
        arc1 = new Arc();
        arc1.setCenterX(x);
        arc1.setCenterY(y);
        arc1.setRadiusX(rx);
        arc1.setRadiusY(ry);
        arc1.setStartAngle(startAngle);
        arc1.setLength(length);
        arc1.setType(ArcType.OPEN);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setStroke(Color.BLUE);
        arc1.setStrokeWidth(width);

        arc2 = new Arc();
        arc2.setCenterX(x);
        arc2.setCenterY(y);
        arc2.setRadiusX(rx);
        arc2.setRadiusY(ry);
        arc2.setStartAngle(startAngle+90.0f);
        arc2.setLength(length);
        arc2.setType(ArcType.OPEN);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.YELLOW);
        arc2.setStrokeWidth(width);

        arc3 = new Arc();
        arc3.setCenterX(x);
        arc3.setCenterY(y);
        arc3.setRadiusX(rx);
        arc3.setRadiusY(ry);
        arc3.setStartAngle(startAngle+180.0f);
        arc3.setLength(length);
        arc3.setType(ArcType.OPEN);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setStroke(Color.VIOLET);
        arc3.setStrokeWidth(width);

        arc4 = new Arc();
        arc4.setCenterX(x);
        arc4.setCenterY(y);
        arc4.setRadiusX(rx);
        arc4.setRadiusY(ry);
        arc4.setStartAngle(startAngle+270.0f);
        arc4.setLength(length);
        arc4.setType(ArcType.OPEN);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setStroke(Color.RED);
        arc4.setStrokeWidth(width);

        starView.setX(210);
        starView.setY(y);
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);

        circle.add(arc1);
        circle.add(arc2);
        circle.add(arc3);
        circle.add(arc4);
    }

    @Override
    public void move() {
        this.arc1.setStartAngle(this.arc1.getStartAngle()+2);
        this.arc2.setStartAngle(this.arc2.getStartAngle()+2);
        this.arc3.setStartAngle(this.arc3.getStartAngle()+2);
        this.arc4.setStartAngle(this.arc4.getStartAngle()+2);
    }

    @Override
    public double getPositionX() {
        return 0;
    }

    @Override
    public double getPositionY() {
        return this.arc1.getCenterY();
    }

    @Override
    public void moveDown(double temp) {
        for(Arc arc: circle){
            arc.setCenterY(arc.getCenterY()-temp);
        }
        this.getStarView().setY(this.getStarView().getY()-temp);
    }
    public ArrayList<Arc> getCircle(){
        return circle;
    }

    public ImageView getStarView(){
        return starView;
    }

    @Override
    public Shape getShape() {
        return this.arc1;
    }

    public void removeStar(){
        this.starView.setImage(null);
    }
}