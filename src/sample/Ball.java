package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;

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

    public void checkCollision(ArrayList<Arc> circleArcs, ArrayList<Rectangle> lineRects, ArrayList<Line> rhombusLines,
                               Rectangle[] par1, Rectangle[] par2, Rectangle[] par3, ArrayList<Line> squareLines) {
        for(Rectangle a: lineRects){
            if(this.getBall().getBoundsInParent().intersects(a.getBoundsInParent())){
                if(!a.getFill().equals(this.getBall().getFill())){
                    Main.isAlive = false;
                }
            }
        }
        for(Rectangle a: par1){
            if(this.getBall().getBoundsInParent().intersects(a.getBoundsInParent())){
                if(!a.getFill().equals(this.getBall().getFill())){
                    Main.isAlive = false;
                }
            }
        }for(Rectangle a: par2){
            if(this.getBall().getBoundsInParent().intersects(a.getBoundsInParent())){
                if(!a.getFill().equals(this.getBall().getFill())){
                    Main.isAlive = false;
                }
            }
        }for(Rectangle a: par3){
            if(this.getBall().getBoundsInParent().intersects(a.getBoundsInParent())){
                if(!a.getFill().equals(this.getBall().getFill())){
                    Main.isAlive = false;
                }
            }
        }

    }
}


