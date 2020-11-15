package sample;

import com.sun.prism.Graphics;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball{
    private Circle ball;
    private final double radius;
    private final double positionX = 205;
    final double BOUNCE = -0.85;
    double y_speed = 0;
    private double velocity;
    private Color c;
//    private int count;
//    private int velocity;
    //physical constants
    final double GRAVITY = 0.1;


    public Ball(int radius, Color c){
        this.radius = radius;
        this.c = c;
        this.ball = new Circle(this.radius, this.c);
        this.ball.relocate(this.positionX, 300);
    }

    public void setPosition(double y) {
        this.ball.relocate(this.positionX, this.ball.getLayoutY()+y);
    }

    public Point2D getPosition() {
        return new Point2D(ball.getCenterX(), ball.getCenterY());
    }

    public void disappear() {

    }
    public Circle getBall(){
        return ball;
    }

//    public int getVelocity() {
//        return velocity;
//    }
//
//    public void setVelocity(int velocity) {
//        this.velocity = velocity;
//    }

    public void move(){
        velocity+= GRAVITY;
        this.setPosition(velocity);
    }
}


