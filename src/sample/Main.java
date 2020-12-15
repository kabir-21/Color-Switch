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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application{
    protected static Ball b;
    protected static int HEIGHT = 780;
    protected static int WIDTH = 420;
    protected static double MID = 100;
    protected static AnimationTimer timer;
    protected static AnimationTimer timer2;
    protected static Stage pStage;
    protected static int points = 0;
    protected final double acc = 0.25;
    protected double position, velocity = 0;
    protected static Color currentColor;
    protected static Color colors[] = {Color.RED, Color.VIOLET, Color.BLUE, Color.YELLOW};
    protected static ArrayList<Obstacles> allObstacles = new ArrayList<>();
    protected static String logoUrl = "https://www.dafont.com/forum/attach/orig/8/8/885774.png?1";
    protected static String colorUrl = "https://i.pinimg.com/originals/06/5f/43/065f43d6e9f4f5aa89d46f1a77804bfa.png";
    public static Image colorSwitch = new Image(colorUrl);
    protected static Image image = new Image(logoUrl);
    protected final Scene[] s = new Scene[10];
    protected final Player[] user = new Player[1];
    protected final int random[] = new int[8];

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
//        ImageView orb = new ImageView(new Image(new FileInputStream("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/a706c6f4-c546-46c4-9b8f-6dc4b7aae1a1/d20nc45-a4bd3c4f-5643-4d95-a96f-8232efe7f84b.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvYTcwNmM2ZjQtYzU0Ni00NmM0LTliOGYtNmRjNGI3YWFlMWExXC9kMjBuYzQ1LWE0YmQzYzRmLTU2NDMtNGQ5NS1hOTZmLTgyMzJlZmU3Zjg0Yi5wbmcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.lA1_6584IIca2NRXVqOKfs_ZW8jarNy3OnAvWngKGpU")));
        ImageView imageView = new ImageView(image);
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
//
//        while(true){
//
//        }






        Pane p = new Pane();
//        Image pauseImage = new Image("C:\\Users\\kabni\\Downloads\\pause.png");
//        ImageView pauseImageView = new ImageView(pauseImage);
//        pauseImageView.setFitHeight(30);
//        pauseImageView.setPreserveRatio(true);
        Button pauseButton = new Button("| |");
        TextField score = new TextField(""+points);
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
        });
        b = new Ball(10, colors[new Random().nextInt(colors.length)]);
        Pane arcPane = new Pane();
        Circle circle = new Circle(210,-1000, 100, 100, 0,90,12);

        ConcurrentCircle concurrentCircle = new ConcurrentCircle(210,-2500,200,200,0,90,12);
        allObstacles.add(concurrentCircle);
        Pane conc = new Pane();
        allObstacles.add(circle);
        ParallelLines parallelLines = new ParallelLines(-1300);
        allObstacles.add(parallelLines);
        Pane parallelPane = new Pane();
        VerticalLines verticalLines = new VerticalLines(-2000);
        allObstacles.add(verticalLines);
        Pane verticalPane = new Pane();
        Pane p2 = new Pane();
        Pane p3 = new Pane();
        Pane p4 = new Pane();
        Pane p5 = new Pane();
        Pane squarePane = new Pane();

        LineObstacle lineObstacle1 = new LineObstacle(20);
        allObstacles.add(lineObstacle1);
        Cross cross = new Cross(90,300, currentColor, pickRandomColor());
        allObstacles.add(cross);
        Rhombus rhombus = new Rhombus(160,-350);
        allObstacles.add(rhombus);
//        String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
//        Image star = new Image(starUrl);
//        ImageView starView = new ImageView(star);
//        starView.setX(205);
//        starView.setY(-320);
//        starView.setPreserveRatio(true);
//        starView.setFitHeight(20);
        Square square = new Square();
        allObstacles.add(square);
        ArrayList<Rectangle> sqaureArr = square.getSquare();
        ArrayList<Rectangle> crossArr = cross.getCross();
        ArrayList<Rectangle> line1Rects = lineObstacle1.getLine();
        arcPane.getChildren().addAll(circle.getCircle());
        conc.getChildren().addAll(concurrentCircle.getBigCircle());
        conc.getChildren().addAll(concurrentCircle.getSmallCircle());
        parallelPane.getChildren().addAll(parallelLines.getParallelLineI(0));
        parallelPane.getChildren().addAll(parallelLines.getParallelLineI(1));
        parallelPane.getChildren().addAll(parallelLines.getParallelLineI(2));
        verticalPane.getChildren().addAll(verticalLines.getVerticalLines());
        p2.getChildren().addAll(lineObstacle1.getLine());
        p2.getChildren().add(lineObstacle1.getSwitchView());
        p3.getChildren().addAll(cross.getCross());
        p4.getChildren().addAll(rhombus.getRhombus());
        p5.getChildren().addAll(square.getSquare());
        p.getChildren().add(b.getBall());
        Group game = new Group();
        position = b.getBall().getCenterY();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                velocity = -4;
                if(rhombus.getStarView().getBoundsInParent().intersects(b.getBall().getBoundsInParent())){
                    if(rhombus.getStarView().getImage() != null)
                        score.setText(""+(++points));
                    rhombus.removeStar();
                    System.out.println(points);
                }
                if(circle.getStarView().getBoundsInParent().intersects(b.getBall().getBoundsInParent())){
                    if(circle.getStarView().getImage() != null)
                        score.setText(""+(++points));
                    circle.removeStar();
                    System.out.println(points);
                }
                if(b.getBall().getBoundsInParent().intersects(lineObstacle1.getSwitchView().getBoundsInParent())){
                    if(lineObstacle1.getSwitchView().getImage() != null){
                        b.getBall().setFill(pickRandomColor());
                        currentColor = (Color) b.getBall().getFill();
                        lineObstacle1.removeSwitch();
                    }
                }
//                b.checkCollision(line1Rects, cross);//, crossArr);
                double temp = MID+b.getBall().getCenterY();
                if(b.getBall().getCenterY()<MID){
                    for(Obstacles entry: allObstacles){
                        entry.moveDown(velocity*1.5);
                    }
//                    for(Map.Entry<Obstacles,Boolean> entry: allObstacles.entrySet()){
//                        if(!entry.getValue()){
//                            allObstacles.replace(entry.getKey(),false,true);
//                        }
//                    }
//                    b.getBall().setCenterY(b.getBall().getCenterY() + temp/2);
//                    line1.moveDown(-8);
//                    cross.moveDown(-8);
//                    rhombus.moveDown(-8);
//                    square.moveDown(-8);
//                    circle.moveDown(-8);
//                    starView.setY(starView.getY()-8);
//                    parallelLines.moveDown(-8);
                }
            }
        };

        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    timer2 = new AnimationTimer(){
                        @Override
                        public void handle(long l) {
                            velocity+=acc;
                            position+=velocity;
                            b.getBall().setCenterY(position);
                            lineObstacle1.move();
                            cross.move();
                            rhombus.move();
                            circle.move();
                            parallelLines.move();
                            verticalLines.move();
                            concurrentCircle.move();
                        }
                    };
                    timer2.start();
                }
            }
        };
        game.getChildren().add(rhombus.getStarView());
        game.getChildren().add(circle.getStarView());
        game.getChildren().add(p);
        game.getChildren().add(p2);
        game.getChildren().add(p3);
        game.getChildren().add(p4);
        game.getChildren().add(p5);
        game.getChildren().add(arcPane);
        game.getChildren().add(conc);
        game.getChildren().add(parallelPane);
        game.getChildren().add(verticalPane);
        game.getChildren().add(pauseButton);
//        game.getChildren().add(starView);
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
    }
}