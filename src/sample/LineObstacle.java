package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class LineObstacle extends Obstacles{
    private final Rectangle rectRed;
    private final Rectangle rectViolet;
    private final Rectangle rectBlue;
    private final Rectangle rectYellow;
    private final ImageView cSwitch;
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private final ArrayList<Rectangle> line = new ArrayList<>();

    LineObstacle(double lenY){
        rectRed = new Rectangle();
        rectRed.setHeight(12);
        rectRed.setWidth(150);
        rectRed.setY(lenY);
        rectRed.setFill(Color.RED);

        rectViolet = new Rectangle();
        rectViolet.setHeight(12);
        rectViolet.setWidth(150);
        rectViolet.setY(lenY);
        rectViolet.setX(150);
        rectViolet.setFill(Color.VIOLET);

        rectBlue = new Rectangle();
        rectBlue.setHeight(12);
        rectBlue.setWidth(150);
        rectBlue.setY(lenY);
        rectBlue.setX(300);
        rectBlue.setFill(Color.BLUE);

        starView.setX(210);
        starView.setY(lenY-30);
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);

        rectYellow = new Rectangle();
        rectYellow.setHeight(12);
        rectYellow.setWidth(150);
        rectYellow.setY(lenY);
        rectYellow.setX(450);
        rectYellow.setFill(Color.YELLOW);
        line.add(rectRed);
        line.add(rectViolet);
        line.add(rectBlue);
        line.add(rectYellow);
        cSwitch = new ImageView(Main.colorSwitch);
        cSwitch.setX(210);
        cSwitch.setY(lenY+40);
        cSwitch.setPreserveRatio(true);
        cSwitch.setFitHeight(20);
    }
    public ArrayList<Rectangle> getLine(){
        return line;
    }

    @Override
    public void move(){
        if(rectRed.getX() == -150)
            rectRed.setX(rectYellow.getX()+150);
        rectRed.setX(rectRed.getX()-3);
        if(rectViolet.getX() == -150)
            rectViolet.setX(rectRed.getX()+150);
        rectViolet.setX(rectViolet.getX()-3);
        if(rectBlue.getX() == -150)
            rectBlue.setX(rectViolet.getX()+150);
        rectBlue.setX(rectBlue.getX()-3);
        if(rectYellow.getX() == -150)
            rectYellow.setX(rectBlue.getX()+150);
        rectYellow.setX(rectYellow.getX()-3);
    }

    @Override
    public double getPositionX() {
        return rectBlue.getX();
    }

    @Override
    public double getPositionY() {
        return rectBlue.getY()-25;
    }

    @Override
    public void moveDown(double temp) {
        for (Rectangle rectangle : line) {
            rectangle.setY(rectangle.getY() - temp);
        }
        this.getStarView().setY(this.getStarView().getY()-temp);

    }

    public ImageView getSwitchView(){
        return this.cSwitch;
    }

    public void removeSwitch(){
        this.cSwitch.setImage(null);
    }
    public ImageView getStarView(){
        return starView;
    }

    @Override
    public Shape getShape() {
        return this.rectBlue;
    }

    public void removeStar(){
        this.starView.setImage(null);
    }


}
