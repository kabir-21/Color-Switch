package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Rhombus extends Obstacles{
    private final Rectangle r1;
    private final Rectangle r2;
    private final Rectangle r3;
    private final Rectangle r4;
    private final ArrayList<Rectangle> rhombusArr = new ArrayList<>();
    Rhombus(double lenX, double lenY){
        r1 = new Rectangle();
        r1.setHeight(17);
        r1.setWidth(200);
        r1.setY(lenY);
        r1.setX(lenX);
        r1.setFill(Color.RED);
        r1.setArcWidth(15);
        r1.setArcHeight(15);

        r2 = new Rectangle();
        r2.setHeight(17);
        r2.setWidth(200);
        r2.setY(lenY+173);
        r2.setX(lenX-100);
        r2.setFill(Color.BLUE);
        r2.setArcWidth(15);
        r2.setArcHeight(15);

        r3 = new Rectangle();
        r3.setHeight(200);
        r3.setWidth(17);
        r3.setY(lenY-5);
        r3.setX(lenX-55);
        r3.setFill(Color.YELLOW);
        r3.setArcWidth(15);
        r3.setArcHeight(15);

        r4 = new Rectangle();
        r4.setHeight(200);
        r4.setWidth(17);
        r4.setY(lenY-5);
        r4.setX(lenX+135);
        r4.setFill(Color.VIOLET);
        r4.setArcWidth(15);
        r4.setArcHeight(15);
        r3.setRotate(30);
        r4.setRotate(30);

        rhombusArr.add(r1);
        rhombusArr.add(r2);
        rhombusArr.add(r3);
        rhombusArr.add(r4);
    }

    public ArrayList<Rectangle> getRhombus(){
        return rhombusArr;
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
        for (Rectangle rectangle : rhombusArr) {
            rectangle.setY(rectangle.getY() - temp/10);
        }
    }
}
