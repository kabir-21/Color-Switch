package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.List;

public class Main extends Application{
    static Ball b;
    public static int HEIGHT = 780;
    public static int WIDTH = 420;
    public static double MID = 370;
    public static AnimationTimer floortimer;

    static Color colorsArr[] = {Color.RED, Color.YELLOW, Color.VIOLET, Color.BLUE};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Color Switch");
        Text devText = new Text("Developed By:");
        Text devKabir = new Text("KABIR [2019365]");
        Text devSejal = new Text("SEJAL [2019102]");
        devText.setFill(Color.WHITE);
        devKabir.setFill(Color.WHITE);
        devSejal.setFill(Color.WHITE);
        devText.setLayoutX(50); devText.setLayoutY(700);
        devKabir.setLayoutX(50); devKabir.setLayoutY(720);
        devSejal.setLayoutX(50); devSejal.setLayoutY(740);
        Image image = new Image(new FileInputStream("C:\\Users\\kabni\\Downloads\\switch.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(8);
        imageView.setY(100);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        final Player[] user = new Player[1];
        final Scene[] s = new Scene[10];

        Group gName = new Group(imageView);
        TextField enterName = new TextField("Enter your name");
        enterName.setAlignment(Pos.CENTER);
        gName.getChildren().add(enterName);
        gName.getChildren().add(devText);
        gName.getChildren().add(devKabir);
        gName.getChildren().add(devSejal);
        enterName.setLayoutX(128);
        enterName.setLayoutY(380);

        enterName.setOnAction(actionEvent -> {
            user[0] = new Player(enterName.getText());
            Group insideStart = new Group(imageView);
            Button newGame = new Button("New Game");
            newGame.setPrefHeight(10);
            newGame.setPrefWidth(90);
            newGame.setLayoutX(175);
            newGame.setLayoutY(400);
            newGame.setOnAction(actionEvent1 ->{
                Pane p = new Pane();
                b = new Ball(10, Color.RED);
                Pane p2 = new Pane();
                Rectangle r = new Rectangle();
                r.setHeight(17);
                r.setWidth(150);
                r.setY(20);
                r.setFill(Color.RED);
                Rectangle r2 = new Rectangle();
                r2.setHeight(17);
                r2.setWidth(150);
                r2.setY(20);
                r2.setX(150);
                r2.setFill(Color.VIOLET);
                Rectangle r3 = new Rectangle();
                r3.setHeight(17);
                r3.setWidth(150);
                r3.setY(20);
                r3.setX(300);
                r3.setFill(Color.BLUE);
                Rectangle r4 = new Rectangle();
                r4.setHeight(17);
                r4.setWidth(150);
                r4.setY(20);
                r4.setX(450);
                r4.setFill(Color.YELLOW);
                p2.getChildren().addAll(r,r2,r3,r4);
                p.getChildren().add(b.getBall());

                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        b.getBall().setCenterY(b.getBall().getCenterY() - 14.5);
//                        double temp = MID-b.getBall().getCenterY();
//                        if(temp<0)
//                            moveFloor(r,r2,r3,r4, temp);
                    }
                };

                EventHandler<Event> eventHandler = new EventHandler<Event>() {

                    AnimationTimer timer2;

                    @Override
                    public void handle(Event event) {
                        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                            AnimationTimer rect1 = new AnimationTimer() {
                                @Override
                                public void handle(long l) {
                                    if(r.getX() == -150)
                                        r.setX(r4.getX()+150);
                                    r.setX(r.getX()-3);
                                    if(r2.getX() == -150)
                                        r2.setX(r.getX()+150);
                                    r2.setX(r2.getX()-3);
                                    if(r3.getX() == -150)
                                        r3.setX(r2.getX()+150);
                                    r3.setX(r3.getX()-3);
                                    if(r4.getX() == -150)
                                        r4.setX(r3.getX()+150);
                                    r4.setX(r4.getX()-3);
                                }
                            };
                            rect1.start();
                            timer2 = new AnimationTimer() {
                                @Override
                                public void handle(long l) {
                                    mouseHandle();
                                }

                                private void mouseHandle() {
                                    b.getBall().setCenterY(b.getBall().getCenterY() + 2.5);
                                }
                            };
                            timer2.start();
                        }

                        if (event.getEventType() == KeyEvent.KEY_TYPED) {
                            timer.start();
                        }
                        if(event.getEventType()==KeyEvent.KEY_RELEASED){
                            timer.stop();
                        }
                    }
                };
                Group game = new Group();
                game.getChildren().add(p);
                game.getChildren().add(p2);
                s[3] = new Scene(game,420,780, Color.BLACK);
                s[3].setOnMouseClicked(eventHandler);
                s[3].setOnKeyTyped(eventHandler);
                s[3].setOnKeyReleased(eventHandler);
                primaryStage.setScene(s[3]);
            });

            Button loadGame = new Button("Load Game");
            loadGame.setPrefHeight(10);
            loadGame.setPrefWidth(90);
            loadGame.setLayoutX(175);
            loadGame.setLayoutY(440);

            Button exitButton = new Button("Exit");
            exitButton.setPrefHeight(10);
            exitButton.setPrefWidth(90);
            exitButton.setLayoutX(175);
            exitButton.setLayoutY(480);
            exitButton.setOnAction(actionEvent1 -> primaryStage.close());

            insideStart.getChildren().add(exitButton);
            insideStart.getChildren().add(newGame); insideStart.getChildren().add(loadGame);
            s[2] = new Scene(insideStart,420,780, Color.BLACK);
            s[2].getStylesheets().add("https://fonts.googleapis.com/css2?family=Concert+One");
            insideStart.setStyle("-fx-font-family: 'Concert One', cursive; -fx-font-size: 15");
            primaryStage.setScene(s[2]);
        });
        s[0] = new Scene(gName, WIDTH, HEIGHT, Color.BLACK);
        s[0].getStylesheets().add("https://fonts.googleapis.com/css2?family=Concert+One");
        gName.setStyle("-fx-font-family: 'Concert One', cursive; -fx-font-size: 15");
        primaryStage.setScene(s[0]);
        primaryStage.show();
    }

//    public static void moveFloor(Rectangle r, Rectangle r2, Rectangle r3, Rectangle r4, double len){
//        floortimer = new AnimationTimer(){
//            @Override
//            public void handle(long l) {
//                r.setY(r.getY()+len);
//                r2.setY(r2.getY()+len);
//                r3.setY(r3.getY()+len);
//                r4.setY(r4.getY()+len);
//
//                //                    List<Node> list = p.getChildren();
////                    floortimer.stop();
//            }
//        };
////        if(b.getBall().getCenterY()<=-325){
//            floortimer.start();
////            floortimer.stop();
////        }
//    }
    public static void main(String[] args) {
        launch(args);
    }
}