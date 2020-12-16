package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public class ConcentricCircle extends Obstacles{
    private final Arc arc1;
    private final Arc arc2;
    private final Arc arc3;
    private final Arc arc4;
    private final Arc bigArc1;
    private final Arc bigArc2;
    private final Arc bigArc3;
    private final Arc bigArc4;
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private final ArrayList<Arc> bigCircle = new ArrayList<>();
    private final ArrayList<Arc> smallCircle = new ArrayList<>();

    public ConcentricCircle(float x, float y, float rx, float ry, float startAngle, float length, int width){
        bigArc1 = new Arc();
        bigArc1.setCenterX(x);
        bigArc1.setCenterY(y);
        bigArc1.setRadiusX(rx);
        bigArc1.setRadiusY(ry);
        bigArc1.setStartAngle(startAngle);
        bigArc1.setLength(length);
        bigArc1.setType(ArcType.OPEN);
        bigArc1.setFill(Color.TRANSPARENT);
        bigArc1.setStroke(Color.BLUE);
        bigArc1.setStrokeWidth(width);

        bigArc2 = new Arc();
        bigArc2.setCenterX(x);
        bigArc2.setCenterY(y);
        bigArc2.setRadiusX(rx);
        bigArc2.setRadiusY(ry);
        bigArc2.setStartAngle(startAngle+90.0f);
        bigArc2.setLength(length);
        bigArc2.setType(ArcType.OPEN);
        bigArc2.setFill(Color.TRANSPARENT);
        bigArc2.setStroke(Color.YELLOW);
        bigArc2.setStrokeWidth(width);

        bigArc3 = new Arc();
        bigArc3.setCenterX(x);
        bigArc3.setCenterY(y);
        bigArc3.setRadiusX(rx);
        bigArc3.setRadiusY(ry);
        bigArc3.setStartAngle(startAngle+180.0f);
        bigArc3.setLength(length);
        bigArc3.setType(ArcType.OPEN);
        bigArc3.setFill(Color.TRANSPARENT);
        bigArc3.setStroke(Color.VIOLET);
        bigArc3.setStrokeWidth(width);

        bigArc4 = new Arc();
        bigArc4.setCenterX(x);
        bigArc4.setCenterY(y);
        bigArc4.setRadiusX(rx);
        bigArc4.setRadiusY(ry);
        bigArc4.setStartAngle(startAngle+270.0f);
        bigArc4.setLength(length);
        bigArc4.setType(ArcType.OPEN);
        bigArc4.setFill(Color.TRANSPARENT);
        bigArc4.setStroke(Color.RED);
        bigArc4.setStrokeWidth(width);

        arc1 = new Arc();
        arc1.setCenterX(x);
        arc1.setCenterY(y);
        arc1.setRadiusX(rx-120);
        arc1.setRadiusY(ry-120);
        arc1.setStartAngle(startAngle);
        arc1.setLength(length-9);
        arc1.setType(ArcType.OPEN);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setStroke(Color.BLUE);
        arc1.setStrokeWidth(width);
//
        arc2 = new Arc();
        arc2.setCenterX(x);
        arc2.setCenterY(y);
        arc2.setRadiusX(rx-120);
        arc2.setRadiusY(ry-120);
        arc2.setStartAngle(startAngle+90.0f);
        arc2.setLength(length-9);
        arc2.setType(ArcType.OPEN);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.YELLOW);
        arc2.setStrokeWidth(width);
//
        arc3 = new Arc();
        arc3.setCenterX(x);
        arc3.setCenterY(y);
        arc3.setRadiusX(rx-120);
        arc3.setRadiusY(ry-120);
        arc3.setStartAngle(startAngle+180.0f);
        arc3.setLength(length-9);
        arc3.setType(ArcType.OPEN);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setStroke(Color.VIOLET);
        arc3.setStrokeWidth(width);
//
        arc4 = new Arc();
        arc4.setCenterX(x);
        arc4.setCenterY(y);
        arc4.setRadiusX(rx-120);
        arc4.setRadiusY(ry-120);
        arc4.setStartAngle(startAngle+270.0f);
        arc4.setLength(length-9);
        arc4.setType(ArcType.OPEN);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setStroke(Color.RED);
        arc4.setStrokeWidth(width);

        bigCircle.add(bigArc1);
        bigCircle.add(bigArc2);
        bigCircle.add(bigArc3);
        bigCircle.add(bigArc4);
        smallCircle.add(arc1);
        smallCircle.add(arc2);
        smallCircle.add(arc3);
        smallCircle.add(arc4);

        starView.setX(210);
        starView.setY(y);
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);

    }


    @Override
    public void move() {
        this.arc1.setStartAngle(this.arc1.getStartAngle()+2);
        this.arc2.setStartAngle(this.arc2.getStartAngle()+2);
        this.arc3.setStartAngle(this.arc3.getStartAngle()+2);
        this.arc4.setStartAngle(this.arc4.getStartAngle()+2);

        this.bigArc1.setStartAngle(this.bigArc1.getStartAngle()-2);
        this.bigArc2.setStartAngle(this.bigArc2.getStartAngle()-2);
        this.bigArc3.setStartAngle(this.bigArc3.getStartAngle()-2);
        this.bigArc4.setStartAngle(this.bigArc4.getStartAngle()-2);
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
        for(Arc arc: bigCircle){
            arc.setCenterY(arc.getCenterY()-temp);
        }
        for (Arc arc: smallCircle){
            arc.setCenterY(arc.getCenterY()-temp);
        }
        this.getStarView().setY(this.getStarView().getY()-temp);
    }

    public ArrayList<Arc> getSmallCircle(){
        return this.smallCircle;
    }
    public ArrayList<Arc> getBigCircle(){
        return this.bigCircle;
    }

    public ImageView getStarView(){
        return starView;
    }

    public void removeStar(){
        this.starView.setImage(null);
    }
}
