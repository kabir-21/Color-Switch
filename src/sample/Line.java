package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Line extends Obstacles{
    private final Rectangle rectRed;
    private final Rectangle rectViolet;
    private final Rectangle rectBlue;
    private final Rectangle rectYellow;
    private final ArrayList<Rectangle> line = new ArrayList<>();

    Line(double lenY){
        rectRed = new Rectangle();
        rectRed.setHeight(17);
        rectRed.setWidth(150);
        rectRed.setY(lenY);
        rectRed.setFill(Color.RED);

        rectViolet = new Rectangle();
        rectViolet.setHeight(17);
        rectViolet.setWidth(150);
        rectViolet.setY(lenY);
        rectViolet.setX(150);
        rectViolet.setFill(Color.VIOLET);

        rectBlue = new Rectangle();
        rectBlue.setHeight(17);
        rectBlue.setWidth(150);
        rectBlue.setY(lenY);
        rectBlue.setX(300);
        rectBlue.setFill(Color.BLUE);

        rectYellow = new Rectangle();
        rectYellow.setHeight(17);
        rectYellow.setWidth(150);
        rectYellow.setY(20);
        rectYellow.setX(450);
        rectYellow.setFill(Color.YELLOW);
        line.add(rectRed);
        line.add(rectViolet);
        line.add(rectBlue);
        line.add(rectYellow);
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
        return rectBlue.getY();
    }

    @Override
    public void moveDown(double temp) {
        for (Rectangle rectangle : line) {
            rectangle.setY(rectangle.getY() - temp/10);
        }
    }

}
