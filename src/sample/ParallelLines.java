package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ParallelLines extends Obstacles{
    private final Rectangle line1[];
    private final Rectangle line2[];
    private final Rectangle line3[];
    private final ArrayList<Rectangle[]> parallelLines = new ArrayList<>();

    public ParallelLines(double lenY){
        double temp = lenY;
        Rectangle rectRed[] = new Rectangle[3];
        for(int i=0;i<3;i++){
            rectRed[i] = new Rectangle();
            rectRed[i].setHeight(12);
            rectRed[i].setWidth(150);
            rectRed[i].setY(temp);
            temp-=150;
            if(i==0)
                rectRed[i].setFill(Color.RED);
            if(i==1)
                rectRed[i].setFill(Color.BLUE);
            if(i==2)
                rectRed[i].setFill(Color.VIOLET);
        }
        temp = lenY;
        Rectangle rectViolet[] = new Rectangle[3];
        for(int i=0;i<3;i++){
            rectViolet[i] = new Rectangle();
            rectViolet[i].setHeight(12);
            rectViolet[i].setWidth(150);
            rectViolet[i].setY(temp);
            rectViolet[i].setX(150);
            temp-=150;
            if(i==0)
                rectViolet[i].setFill(Color.VIOLET);
            if(i==1)
                rectViolet[i].setFill(Color.VIOLET);
            if(i==2)
                rectViolet[i].setFill(Color.BLUE);
        }

        temp = lenY;
        Rectangle rectBlue[] = new Rectangle[3];
        for(int i=0;i<3;i++){
            rectBlue[i] = new Rectangle();
            rectBlue[i].setHeight(12);
            rectBlue[i].setWidth(150);
            rectBlue[i].setY(temp);
            rectBlue[i].setX(300);
            temp-=150;
            if(i==0)
                rectBlue[i].setFill(Color.YELLOW);
            if(i==1)
                rectBlue[i].setFill(Color.YELLOW);
            if(i==2)
                rectBlue[i].setFill(Color.RED);
        }
        temp = lenY;
        Rectangle rectYellow[] = new Rectangle[3];
        for(int i=0;i<3;i++){
            rectYellow[i] = new Rectangle();
            rectYellow[i].setHeight(12);
            rectYellow[i].setWidth(150);
            rectYellow[i].setY(temp);
            rectYellow[i].setX(450);
            if(i==0)
                rectYellow[i].setFill(Color.BLUE);
            if(i==1)
                rectYellow[i].setFill(Color.RED);
            if(i==2)
                rectYellow[i].setFill(Color.YELLOW);
            temp-=150;
        }

        line1 = new Rectangle[]{rectRed[0], rectViolet[0], rectBlue[0], rectYellow[0]};
        line2 = new Rectangle[]{rectRed[1], rectViolet[1], rectBlue[1], rectYellow[1]};
        line3 = new Rectangle[]{rectRed[2], rectViolet[2], rectBlue[2], rectYellow[2]};
        parallelLines.add(line1);
        parallelLines.add(line2);
        parallelLines.add(line3);
    }

    @Override
    public void move() {
        if(this.line1[0].getX() == -150)
            this.line1[0].setX(this.line1[3].getX()+150);
        this.line1[0].setX(this.line1[0].getX()-1);
        if(this.line1[1].getX() == -150)
            this.line1[1].setX(this.line1[0].getX()+150);
        this.line1[1].setX(this.line1[1].getX()-1);
        if(this.line1[2].getX() == -150)
            this.line1[2].setX(this.line1[1].getX()+150);
        this.line1[2].setX(this.line1[2].getX()-1);
        if(this.line1[3].getX() == -150)
            this.line1[3].setX(this.line1[2].getX()+150);
        this.line1[3].setX(this.line1[3].getX()-1);

        if(this.line2[0].getX() == -150)
            this.line2[0].setX(this.line2[3].getX()+150);
        this.line2[0].setX(this.line2[0].getX()-2);
        if(this.line2[1].getX() == -150)
            this.line2[1].setX(this.line2[0].getX()+150);
        this.line2[1].setX(this.line2[1].getX()-2);
        if(this.line2[2].getX() == -150)
            this.line2[2].setX(this.line2[1].getX()+150);
        this.line2[2].setX(this.line2[2].getX()-2);
        if(this.line2[3].getX() == -150)
            this.line2[3].setX(this.line2[2].getX()+150);
        this.line2[3].setX(this.line2[3].getX()-2);

        if(this.line3[0].getX() == -150)
            this.line3[0].setX(this.line3[3].getX()+150);
        this.line3[0].setX(this.line3[0].getX()-3);
        if(this.line3[1].getX() == -150)
            this.line3[1].setX(this.line3[0].getX()+150);
        this.line3[1].setX(this.line3[1].getX()-3);
        if(this.line3[2].getX() == -150)
            this.line3[2].setX(this.line3[1].getX()+150);
        this.line3[2].setX(this.line3[2].getX()-3);
        if(this.line3[3].getX() == -150)
            this.line3[3].setX(this.line3[2].getX()+150);
        this.line3[3].setX(this.line3[3].getX()-3);
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
        for(Rectangle line[]: this.parallelLines){
            for (Rectangle rectangle : line) {
                rectangle.setY(rectangle.getY() - temp);
            }
        }
    }

    public Rectangle[] getParallelLineI(int i){
        return this.parallelLines.get(i);
    }
}
