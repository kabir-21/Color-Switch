package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application{
    protected static Ball b;
    protected static int HEIGHT = 780;
    protected static int WIDTH = 420;
    protected static double MID = 390;
    protected static AnimationTimer floortimer;
    public static AnimationTimer timer;
    public static AnimationTimer timer2;
    public static AnimationTimer rect1;
//    public static AnimationTimer cross_timer;

    protected static Image image;
    static {
        try {
            image = new Image(new FileInputStream("C:\\Users\\kabni\\Downloads\\switch.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected final Scene[] s = new Scene[10];
    protected final Player[] user = new Player[1];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Color Switch");
        startGame(primaryStage);
        primaryStage.show();
    }

    public void startGame(Stage primaryStage){
        Text devText = new Text("Developed By:");
        Text devKabir = new Text("KABIR [2019365]");
        Text devSejal = new Text("SEJAL [2019102]");
        devText.setFill(Color.WHITE);
        devKabir.setFill(Color.WHITE);
        devSejal.setFill(Color.WHITE);
        devText.setLayoutX(50); devText.setLayoutY(700);
        devKabir.setLayoutX(50); devKabir.setLayoutY(720);
        devSejal.setLayoutX(50); devSejal.setLayoutY(740);
        ImageView imageView = new ImageView(image);
//        ImageView orb = new ImageView(new Image(new FileInputStream("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/a706c6f4-c546-46c4-9b8f-6dc4b7aae1a1/d20nc45-a4bd3c4f-5643-4d95-a96f-8232efe7f84b.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvYTcwNmM2ZjQtYzU0Ni00NmM0LTliOGYtNmRjNGI3YWFlMWExXC9kMjBuYzQ1LWE0YmQzYzRmLTU2NDMtNGQ5NS1hOTZmLTgyMzJlZmU3Zjg0Yi5wbmcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.lA1_6584IIca2NRXVqOKfs_ZW8jarNy3OnAvWngKGpU")));
        imageView.setX(8);
        imageView.setY(100);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        Group gName = new Group(imageView);
        TextField enterName = new TextField("Enter your name");
        enterName.setAlignment(Pos.CENTER);
        gName.getChildren().add(enterName);
        gName.getChildren().add(devText);
        gName.getChildren().add(devKabir);
        gName.getChildren().add(devSejal);
        enterName.setLayoutX(128);
        enterName.setLayoutY(380);

        enterName.setOnAction(actionEvent -> insideStart(primaryStage, enterName.getText(), imageView));

        s[0] = new Scene(gName, WIDTH, HEIGHT, Color.BLACK);
        s[0].getStylesheets().add("https://fonts.googleapis.com/css2?family=Concert+One");
        gName.setStyle("-fx-font-family: 'Concert One', cursive; -fx-font-size: 15");
        primaryStage.setScene(s[0]);
    }

    public void insideStart(Stage primaryStage, String name, ImageView imageView){
        user[0] = new Player(name);
        Group insideStart = new Group(imageView);
        Button newGame = new Button("New Game");
        newGame.setPrefHeight(10);
        newGame.setPrefWidth(90);
        newGame.setLayoutX(175);
        newGame.setLayoutY(400);
        newGame.setOnAction(actionEvent1 -> insideNewGame(primaryStage));

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
    }

    public void insideNewGame(Stage primaryStage) {
        Pane p = new Pane();
        b = new Ball(10, Color.RED);
        Pane p2 = new Pane();
        Pane squarePane = new Pane();
        Line line1 = new Line(20);
//        Cross cross = new Cross(90,370, Color.RED, Color.BLUE);
//        ArrayList<Rectangle> crossArr = cross.getCross();
        ArrayList<Rectangle> line1Rects = line1.getLine();
        p2.getChildren().addAll(line1.getLine()); //p2.getChildren().addAll(cross.getCross());
        p.getChildren().add(b.getBall());

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                b.getBall().setCenterY(b.getBall().getCenterY() - 14.5);
                b.checkCollision(line1Rects);//, crossArr);
                double temp = MID+b.getBall().getCenterY();
                if(temp<0)
                    line1.moveDown(temp);
            }
        };

        EventHandler<Event> eventHandler = new EventHandler<Event>() {


            @Override
            public void handle(Event event) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    rect1 = new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            line1.move();
                        }
                    };
                    rect1.start();
                    timer2 = new AnimationTimer(){
                        @Override
                        public void handle(long l) {
                            b.gravity();
                        }
                    };
                    timer2.start();
//                    cross_timer=new AnimationTimer() {
//                        @Override
//                        public void handle(long l) {
//                            cross.move();
//                        }
//                    };
//                    cross_timer.start();
                }
            }
        };
        Group game = new Group();
        game.getChildren().add(p);
        game.getChildren().add(p2);
        s[3] = new Scene(game,420,780, Color.BLACK);
        s[3].setOnMouseClicked(eventHandler);
        s[3].setOnKeyPressed(keyEvent -> timer.start());
        s[3].setOnKeyReleased(keyEvent -> timer.stop());
        primaryStage.setScene(s[3]);
    }
}