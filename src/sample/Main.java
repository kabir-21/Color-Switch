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
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application{
    protected static Ball b;
    protected static int HEIGHT = 780;
    protected static int WIDTH = 420;
    protected static double MID = 390;
    protected static AnimationTimer floortimer; 
    public static AnimationTimer timer;
    public static AnimationTimer timer2;
    public static AnimationTimer rect1;
    public static AnimationTimer cross_timer;
    public static AnimationTimer rhombus_timer;
    public static AnimationTimer circle_timer;
    public static Stage pStage;
    public static Color currentColor;
    public static Color colors[] = {Color.RED, Color.VIOLET, Color.BLUE, Color.YELLOW};

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
        pStage = primaryStage;
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
        enterName.setStyle("-fx-text-fill: white");
        enterName.setBackground(Background.EMPTY);
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
        newGame.setStyle("-fx-text-fill: white");
        newGame.setBackground(Background.EMPTY);
        newGame.setOnAction(actionEvent1 -> {
            try {
                insideNewGame(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        Button loadGame = new Button("Load Game");
        loadGame.setPrefHeight(10);
        loadGame.setPrefWidth(90);
        loadGame.setLayoutX(175);
        loadGame.setLayoutY(440);
        loadGame.setStyle("-fx-text-fill: white");
        loadGame.setBackground(Background.EMPTY);

        FileChooser fileChooser = new FileChooser();
        loadGame.setOnAction(actionEvent -> insideLoadGame(fileChooser, loadGame, primaryStage));

        Button exitButton = new Button("Exit");
        exitButton.setPrefHeight(10);
        exitButton.setPrefWidth(90);
        exitButton.setLayoutX(175);
        exitButton.setLayoutY(480);
        exitButton.setStyle("-fx-text-fill: white");
        exitButton.setBackground(Background.EMPTY);
        exitButton.setOnAction(actionEvent1 -> primaryStage.close());

        insideStart.getChildren().add(exitButton);
        insideStart.getChildren().add(newGame); insideStart.getChildren().add(loadGame);
        s[2] = new Scene(insideStart,420,780, Color.BLACK);
        s[2].getStylesheets().add("https://fonts.googleapis.com/css2?family=Concert+One");
        insideStart.setStyle("-fx-font-family: 'Concert One', cursive; -fx-font-size: 15");
        primaryStage.setScene(s[2]);
    }

    public void insideNewGame(Stage primaryStage) throws FileNotFoundException {
        Pane p = new Pane();
//        Image pauseImage = new Image("C:\\Users\\kabni\\Downloads\\pause.png");
//        ImageView pauseImageView = new ImageView(pauseImage);
//        pauseImageView.setFitHeight(30);
//        pauseImageView.setPreserveRatio(true);
        Button pauseButton = new Button("| |");
        TextField score = new TextField("00");
        score.setStyle("-fx-text-fill: white");
        score.setBackground(Background.EMPTY);
        score.setLayoutX(0);
        score.setLayoutY(750);
        pauseButton.setStyle("-fx-font-weight: bold");
        pauseButton.setStyle("-fx-text-fill: white");
        pauseButton.setBackground(Background.EMPTY);
        pauseButton.setLayoutX(390);
        pauseButton.setLayoutY(750);
        pauseButton.setPrefSize(30,30);
        pauseButton.setDefaultButton(false);
        pauseButton.setOnAction(actionEvent -> {
            pauseGame(primaryStage);
            timer.stop();
            timer2.stop();
            rect1.stop();
            cross_timer.stop();
        });
        b = new Ball(10, colors[new Random().nextInt(colors.length)]);
        Pane arcPane = new Pane();
        Circle circle = new Circle(210,290, 100, 100, 0,90,12);
        Pane p2 = new Pane();
        Pane p3 = new Pane();
        Pane p4 = new Pane();
        Pane p5 = new Pane();
        Pane squarePane = new Pane();
        Line line1 = new Line(20);
        Cross cross = new Cross(90,300, currentColor, pickRandomColor());
        Rhombus rhombus = new Rhombus(160,-350);
        Image star = new Image(new FileInputStream("C:\\Users\\kabni\\Downloads\\star.png"));
        ImageView starView = new ImageView(star);
        starView.setX(205);
        starView.setY(-320);
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);
        Square square = new Square();
        ArrayList<Rectangle> sqaureArr = square.getSquare();
        ArrayList<Rectangle> crossArr = cross.getCross();
        ArrayList<Rectangle> line1Rects = line1.getLine();
        arcPane.getChildren().addAll(circle.getCircle());
        p2.getChildren().addAll(line1.getLine()); //p2.getChildren().addAll(cross.getCross());
        p3.getChildren().addAll(cross.getCross());
        p4.getChildren().addAll(rhombus.getRhombus());
        p5.getChildren().addAll(square.getSquare());
        p.getChildren().add(b.getBall());

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                b.getBall().setCenterY(b.getBall().getCenterY() - 12);
//                b.checkCollision(line1Rects, cross);//, crossArr);
                double temp = MID+b.getBall().getCenterY();
                if(temp<0){
//                    b.getBall().setCenterY(b.getBall().getCenterY() + temp/2);
                    line1.moveDown(temp);
                    cross.moveDown(temp);
                    rhombus.moveDown(temp);
                    square.moveDown(temp);
                }
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
                    cross_timer=new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            cross.move();
                        }
                    };
                    cross_timer.start();
                    rhombus_timer = new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            rhombus.move();
                        }
                    };
                    rhombus_timer.start();
                    circle_timer = new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            circle.move();
                        }
                    };
                    circle_timer.start();
                }
            }
        };
        Group game = new Group();
        game.getChildren().add(p);
        game.getChildren().add(p2);
        game.getChildren().add(p3);
        game.getChildren().add(p4);
        game.getChildren().add(p5);
        game.getChildren().add(arcPane);
        game.getChildren().add(pauseButton);
        game.getChildren().add(starView);
        game.getChildren().add(score);
        s[3] = new Scene(game,420,780, Color.BLACK);
        s[3].setOnMouseClicked(eventHandler);
        s[3].setOnKeyPressed(keyEvent -> timer.start());
        s[3].setOnKeyReleased(keyEvent -> timer.stop());
        primaryStage.setScene(s[3]);
    }

    public Color pickRandomColor() {
        Color c;
        while(true){
            c = colors[new Random().nextInt(colors.length)];
            if(c!=currentColor)
                break;
        }
        return c;
    }

    public void pauseGame(Stage primaryStage) {
        ImageView imageView = new ImageView(image);
        imageView.setX(8);
        imageView.setY(100);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        Group pauseMenu = new Group(imageView);

        Button resumeButton = new Button("Resume");
        resumeButton.setPrefHeight(10);
        resumeButton.setPrefWidth(90);
        resumeButton.setLayoutX(175);
        resumeButton.setLayoutY(400);
        resumeButton.setStyle("-fx-text-fill: white");
        resumeButton.setBackground(Background.EMPTY);
        resumeButton.setOnAction(actionEvent -> {
            primaryStage.setScene(s[3]);
        }
        );

        Button saveButton = new Button("Save Game");
        saveButton.setPrefHeight(10);
        saveButton.setPrefWidth(90);
        saveButton.setLayoutX(175);
        saveButton.setLayoutY(440);
        saveButton.setStyle("-fx-text-fill: white");
        saveButton.setBackground(Background.EMPTY);

        Button restartButton = new Button("Restart");
        restartButton.setPrefHeight(10);
        restartButton.setPrefWidth(90);
        restartButton.setLayoutX(175);
        restartButton.setLayoutY(480);
        restartButton.setStyle("-fx-text-fill: white");
        restartButton.setBackground(Background.EMPTY);

        Button exitButton = new Button("Exit");
        exitButton.setPrefHeight(10);
        exitButton.setPrefWidth(90);
        exitButton.setLayoutX(175);
        exitButton.setLayoutY(520);
        exitButton.setOnAction(actionEvent1 -> primaryStage.close());
        exitButton.setStyle("-fx-text-fill: white");
        exitButton.setBackground(Background.EMPTY);

        pauseMenu.getChildren().add(resumeButton);
        pauseMenu.getChildren().add(saveButton);
        pauseMenu.getChildren().add(exitButton);
        pauseMenu.getChildren().add(restartButton);
        s[4] = new Scene(pauseMenu, 420, 780, Color.BLACK);
        s[4].getStylesheets().add("https://fonts.googleapis.com/css2?family=Concert+One");
        pauseMenu.setStyle("-fx-font-family: 'Concert One', cursive; -fx-font-size: 15");
        primaryStage.setScene(s[4]);
    }

    public void insideLoadGame(FileChooser fileChooser, Button loadGame, Stage primaryStage) {
        loadGame.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
        });
    }

    public static void gameOver() {
        timer.stop();
        timer2.stop();
        rect1.stop();
        cross_timer.stop();
    }
}