package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Ball{
    private final Circle ball;
    private Color c;

    public Ball(int radius, Color c){
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
        this.getBall().setCenterY(this.getBall().getCenterY() + 2.5);
    }

    public void checkCollision(ArrayList<Rectangle> line1Rects) {
        for(int i=0; i<line1Rects.size(); i++){
            if(line1Rects.get(i).getBoundsInParent().intersects(this.getBall().getBoundsInParent())){
                if(!line1Rects.get(i).getFill().equals(this.getBall().getFill())){
                    Main.timer.stop(); Main.timer2.stop(); Main.rect1.stop(); //Main.cross_timer.stop();
                }
            }
        }
//        for(int i=0; i<crossArr.size(); i++){
//            if(crossArr.get(i).getBoundsInParent().intersects(this.getBall().getBoundsInParent())){
//                if(!crossArr.get(i).getFill().equals(this.getBall().getFill())){
//                    Main.timer.stop(); Main.timer2.stop(); Main.rect1.stop(); //Main.cross_timer.stop();
//                }
//            }
//        }
    }
}


