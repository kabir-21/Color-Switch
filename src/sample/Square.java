package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Square extends Obstacles{
    private final Rectangle red;
    private final Rectangle violet;
    private final Rectangle blue;
    private final Rectangle yellow;
    private final ArrayList<Rectangle> square = new ArrayList<>();

    public Square() {
        red = new Rectangle();
        red.setHeight(12);
        red.setWidth(200);
        red.setX(120);
        red.setY(-800);
        red.setFill(Color.RED);

        violet = new Rectangle();
        violet.setHeight(200);
        violet.setWidth(12);
        violet.setX(120);
        violet.setY(-800);
        violet.setFill(Color.VIOLET);

        blue = new Rectangle();
        blue.setHeight(200);
        blue.setWidth(12);
        blue.setX(320);
        blue.setY(-800);
        blue.setFill(Color.BLUE);

        yellow = new Rectangle();
        yellow.setHeight(12);
        yellow.setWidth(217);
        yellow.setX(120);
        yellow.setY(-600);
        yellow.setFill(Color.YELLOW);

        square.add(red); square.add(violet); square.add(blue); square.add(yellow);
    }

    public ArrayList<Rectangle> getSquare(){
        return square;
    }

    @Override
    public void move() {

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
        for (Rectangle rectangle : square) {
            rectangle.setY(rectangle.getY() - temp);
        }
    }
}
