package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Ball{
    private final Circle ball;
    private Color c;

    public Ball(int radius, Color c){
        Main.currentColor = c;
        this.c = c;
        this.ball = new Circle(radius, this.c);
        double positionX = 205;
        this.ball.relocate(positionX, 700);
    }
    public void disappear() {

    }
    public Circle getBall(){
        return ball;
    }

    public void gravity() {
        this.getBall().setCenterY(this.getBall().getCenterY() + 1.5);

    }

    public void checkCollision(ArrayList<Rectangle> line1Rects, Cross cross) {
        for (Rectangle line1Rect : line1Rects) {
            if (line1Rect.getBoundsInParent().intersects(this.getBall().getBoundsInParent())) {
                if (!line1Rect.getFill().equals(this.getBall().getFill())) {
                    Main.gameOver();
//                    cross.r1.stop();
//                    cross.r2.stop();
//                    Main.timer.stop();
//                    Main.timer2.stop();
//                    Main.rect1.stop();
//                    Main.cross_timer.stop();
                }
            }
        }
        ArrayList<Rectangle> crossArr = cross.getCross();
        for (Rectangle rectangle : crossArr) {
            if (rectangle.getBoundsInParent().intersects(this.getBall().getBoundsInParent())) {
                if (!rectangle.getFill().equals(this.getBall().getFill())) {
                    Main.gameOver();
//                    cross.r1.stop();
//                    cross.r2.stop();
//                    Main.timer.stop();
//                    Main.timer2.stop();
//                    Main.rect1.stop();
//                    Main.cross_timer.stop();
                }
            }
        }
    }
}


