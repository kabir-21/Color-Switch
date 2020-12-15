package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class VerticalLines extends Obstacles{
    private final Rectangle rectRed;
    private final Rectangle rectViolet;
    private final Rectangle rectBlue;
    private final Rectangle rectYellow;
    private final Rectangle rectRed2;
    private final Rectangle rectViolet2;
    private final Rectangle rectBlue2;
    private final Rectangle rectYellow2;
    private final ArrayList<Rectangle> lines = new ArrayList<>();

    public VerticalLines(double lenY){
        rectRed = new Rectangle();
        rectRed.setHeight(150);
        rectRed.setWidth(12);
        rectRed.setY(lenY);
        rectRed.setX(0);
        rectRed.setFill(Color.RED);
        rectRed2 = new Rectangle();
        rectRed2.setHeight(150);
        rectRed2.setWidth(12);
        rectRed2.setY(lenY);
        rectRed2.setX(70);
        rectRed2.setFill(Color.RED);

        rectViolet = new Rectangle();
        rectViolet.setHeight(150);
        rectViolet.setWidth(12);
        rectViolet.setY(lenY);
        rectViolet.setX(140);
        rectViolet.setFill(Color.VIOLET);
        rectViolet2 = new Rectangle();
        rectViolet2.setHeight(150);
        rectViolet2.setWidth(12);
        rectViolet2.setY(lenY);
        rectViolet2.setX(210);
        rectViolet2.setFill(Color.VIOLET);

        rectBlue = new Rectangle();
        rectBlue.setHeight(150);
        rectBlue.setWidth(12);
        rectBlue.setY(lenY);
        rectBlue.setX(280);
        rectBlue.setFill(Color.BLUE);
        rectBlue2 = new Rectangle();
        rectBlue2.setHeight(150);
        rectBlue2.setWidth(12);
        rectBlue2.setY(lenY);
        rectBlue2.setX(350);
        rectBlue2.setFill(Color.BLUE);

        rectYellow = new Rectangle();
        rectYellow.setHeight(150);
        rectYellow.setWidth(12);
        rectYellow.setY(lenY);
        rectYellow.setX(420);
        rectYellow.setFill(Color.YELLOW);
        rectYellow2 = new Rectangle();
        rectYellow2.setHeight(150);
        rectYellow2.setWidth(12);
        rectYellow2.setY(lenY);
        rectYellow2.setX(490);
        rectYellow2.setFill(Color.YELLOW);
        lines.add(rectRed);
        lines.add(rectRed2);
        lines.add(rectViolet);
        lines.add(rectViolet2);
        lines.add(rectBlue);
        lines.add(rectBlue2);
        lines.add(rectYellow);
        lines.add(rectYellow2);
    }

    @Override
    public void move() {
        if(rectRed.getX() == -12)
            rectRed.setX(rectYellow.getX()+70);
        rectRed.setX(rectRed.getX()-3);
        if(rectViolet.getX() == -12)
            rectViolet.setX(rectRed.getX()+70);
        rectViolet.setX(rectViolet.getX()-3);
        if(rectBlue.getX() == -12)
            rectBlue.setX(rectViolet.getX()+70);
        rectBlue.setX(rectBlue.getX()-3);
        if(rectYellow.getX() == -12)
            rectYellow.setX(rectBlue.getX()+70);
        rectYellow.setX(rectYellow.getX()-3);
        //        if(this.rectRed.getX() == -12)
//            this.rectRed.setX(560);
//        if(this.rectRed2.getX() == -12)
//            this.rectRed2.setX(560);
//        if(this.rectViolet.getX() == -12)
//            this.rectViolet.setX(560);
//        if(this.rectViolet2.getX() == -12)
//            this.rectViolet2.setX(560);
//        if(this.rectBlue.getX() == -12)
//            this.rectBlue.setX(560);
//        if(this.rectBlue2.getX() == -12)
//            this.rectBlue2.setX(560);
//        if(this.rectYellow.getX() == -12)
//            this.rectYellow.setX(560);
//        if(this.rectYellow2.getX() == -12)
//            this.rectYellow2.setX(560);
//
//        this.rectRed.setX(this.rectRed.getX()-3);
//        this.rectRed2.setX(this.rectRed2.getX()-3);
//        this.rectViolet.setX(this.rectViolet.getX()-3);
//        this.rectViolet2.setX(this.rectViolet2.getX()-3);
//        this.rectBlue.setX(this.rectBlue.getX()-3);
//        this.rectBlue2.setX(this.rectBlue2.getX()-3);
//        this.rectYellow.setX(this.rectYellow.getX()-3);
//        this.rectYellow2.setX(this.rectYellow2.getX()-3);
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

    public ArrayList<Rectangle> getVerticalLines(){
        return this.lines;
    }
}
