package sample;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class VerticalLines extends Obstacles{
    private final Rectangle rectRed;
    private final Rectangle rectViolet;
    private final Rectangle rectBlue;
    private final Rectangle rectYellow;
    private final ArrayList<Rectangle> lines = new ArrayList<>();

    public VerticalLines(double lenY){
        rectRed = new Rectangle();
        rectRed.setHeight(150);
        rectRed.setWidth(12);
        rectRed.setY(lenY);
        rectRed.setX(0);
        rectRed.setFill(Color.RED);

        rectViolet = new Rectangle();
        rectViolet.setHeight(150);
        rectViolet.setWidth(12);
        rectViolet.setY(lenY);
        rectViolet.setX(50);
        rectViolet.setFill(Color.VIOLET);

        rectBlue = new Rectangle();
        rectBlue.setHeight(150);
        rectBlue.setWidth(12);
        rectBlue.setY(lenY);
        rectBlue.setX(100);
        rectBlue.setFill(Color.BLUE);

        rectYellow = new Rectangle();
        rectYellow.setHeight(150);
        rectYellow.setWidth(12);
        rectYellow.setY(lenY);
        rectYellow.setX(150);
        rectYellow.setFill(Color.YELLOW);

        lines.add(rectRed);
        lines.add(rectViolet);
        lines.add(rectBlue);
        lines.add(rectYellow);
    }

    @Override
    public void move() {
        if(rectRed.getX() == -12)
        rectRed.setX(rectYellow.getX()+50);
        rectRed.setX(rectRed.getX()-3);
        if(rectViolet.getX() == -12)
            rectViolet.setX(rectRed.getX()+50);
        rectViolet.setX(rectViolet.getX()-3);
        if(rectBlue.getX() == -12)
            rectBlue.setX(rectViolet.getX()+50);
        rectBlue.setX(rectBlue.getX()-3);
        if(rectYellow.getX() == -12)
            rectYellow.setX(rectBlue.getX()+50);
        rectYellow.setX(rectYellow.getX()-3);
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
        for (Rectangle rectangle : this.lines) {
            rectangle.setY(rectangle.getY() - temp);
        }
    }

    @Override
    public ImageView getStarView() {
        return null;
    }

    @Override
    public Shape getShape() {
        return this.rectBlue;
    }

    public ArrayList<Rectangle> getVerticalLines(){
        return this.lines;
    }
}
