package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WildCard {
    private Circle ball;
    private final int radius;
    private final double positionX = 202.5;
    private double positionY;
    private Color c;

    public WildCard(int radius, Color c){
        this.radius = radius;
        this.c = c;
        this.ball = new Circle(this.radius, this.c);
        this.ball.relocate(this.positionX, 600);
        this.positionY = 600;
    }
    public Circle getBall(){
        return ball;
    }

}
