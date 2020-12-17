package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application{
    protected static Ball b;
    protected static int HEIGHT = 780;
    protected static int WIDTH = 420;
    protected static double MID = -250;
    protected static AnimationTimer timer;
    protected static AnimationTimer timer2;
    protected static Stage pStage;
    protected static int points = 0;
    protected final double acc = 0.25;
    protected double position, velocity = 0;
    protected Pane game = new Pane();
    protected static Color currentColor;
    protected static Color[] colors = {Color.RED, Color.VIOLET, Color.BLUE, Color.YELLOW};
    protected static ArrayList<Obstacles> allObstacles = new ArrayList<>();
    protected static ArrayList<ImageView> stars = new ArrayList<>();
    protected static String logoUrl = "https://www.dafont.com/forum/attach/orig/8/8/885774.png?1";
    protected static String colorUrl = "https://i.pinimg.com/originals/06/5f/43/065f43d6e9f4f5aa89d46f1a77804bfa.png";
    public static Image colorSwitch = new Image(colorUrl);
    protected static Image image = new Image(logoUrl);
    protected final Scene[] s = new Scene[10];
    protected final Player[] user = new Player[1];
    protected final int[] random = new int[7];
    protected int obstacleNumber = -1;
    public static int currentObs = 0;
    public ArrayList<Arc> circleArcs;
    public ArrayList<Rectangle> lineRects;
    public Rectangle[] par1;
    public Rectangle[] par2;
    public Rectangle[] par3;
    public ArrayList<Line> rhombusLines;
    public ArrayList<Line> squareLines;
    protected double lastY = -2000;
    public static boolean isAlive = true;
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private ImageView switchView = new ImageView(colorSwitch);
    public ArrayList<Group> p = new ArrayList<>();
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
        Circle menu_c=new Circle(210,550,50,50,0,90,12);
        Circle menu_c1=new Circle(210,550,100,100,0,90,12);
        Circle menu_c2=new Circle(210,550,75,75,0,90,12);
        Circle menu_c3=new Circle(210,550,125,125,0,90,12);
        Rectangle menu_r1=new Rectangle();
        menu_r1.setX(190);
        menu_r1.setY(546);
        menu_r1.setHeight(8);
        menu_r1.setWidth(40);
        menu_r1.setFill(Color.RED);
        Rectangle menu_r2=new Rectangle();
        menu_r2.setX(206);
        menu_r2.setY(530);
        menu_r2.setHeight(40);
        menu_r2.setWidth(8);
        menu_r2.setFill(Color.YELLOW);
        menu_r1.setArcWidth(5);
        menu_r1.setArcHeight(5);
        menu_r2.setArcWidth(5);
        menu_r2.setArcHeight(5);
        Rotate menu_cross=new Rotate();
        menu_cross.setAngle(2);
        menu_cross.setPivotX(210);
        menu_cross.setPivotY(550);
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
        gName.getChildren().addAll(menu_c.getCircle());
        gName.getChildren().addAll(menu_c1.getCircle());
        gName.getChildren().addAll(menu_c2.getCircle());
        gName.getChildren().addAll(menu_c3.getCircle());
        gName.getChildren().addAll(menu_r1,menu_r2);
        enterName.setLayoutX(128);
        enterName.setLayoutY(380);
        enterName.setOnAction(actionEvent -> insideStart(primaryStage, enterName.getText(), imageView));

        s[0] = new Scene(gName, WIDTH, HEIGHT, Color.BLACK);
        s[0].getStylesheets().add("https://fonts.googleapis.com/css2?family=Concert+One");
        gName.setStyle("-fx-font-family: 'Concert One', cursive; -fx-font-size: 15");
        primaryStage.setScene(s[0]);
        AnimationTimer home_screen=new AnimationTimer() {
            @Override
            public void handle(long l) {
                menu_c.move();
                menu_c1.move();
                menu_c2.move();
                menu_c3.move();
                menu_r1.getTransforms().add(menu_cross);
                menu_r2.getTransforms().add(menu_cross);
            }
        };
        home_screen.start();
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
        Button pauseButton = new Button("| |");
        TextField score = new TextField(""+points);
        score.setStyle("-fx-text-fill: white");
        score.setOpacity(20);
        score.setBackground(Background.EMPTY);
        score.setLayoutX(-1);
        score.setLayoutY(748);
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
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
        p.add(new Group(b.getBall()));
        game.getChildren().add(p.get(0));
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);
        starView.setX(210);
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);
        switchView.setPreserveRatio(true);
        switchView.setFitHeight(20);
        switchView.setX(210);
        game.getChildren().add(starView);
        game.getChildren().add(switchView);
        Circle cOb = new Circle(210, 300, 100, 100, 0, 90, 12);
        game.getChildren().addAll(cOb.getCircle());
        circleArcs = cOb.getCircle();
        allObstacles.add(cOb);
        LineObstacle lOb = new LineObstacle(0);
        game.getChildren().addAll(lOb.getLine());
        allObstacles.add(lOb);
        lineRects = lOb.getLine();
        Rhombus rOb = new Rhombus(150,-500);
        game.getChildren().addAll(rOb.getRhombus());
        allObstacles.add(rOb);
        rhombusLines = rOb.getRhombus();
        ParallelLines pOb = new ParallelLines(-800);
        game.getChildren().addAll(pOb.getParallelLineI(0));
        par1 = pOb.getParallelLineI(0);
        game.getChildren().addAll(pOb.getParallelLineI(1));
        par2 = pOb.getParallelLineI(1);
        game.getChildren().addAll(pOb.getParallelLineI(2));
        par3 = pOb.getParallelLineI(2);
        allObstacles.add(pOb);
        Square sqOb = new Square(110,-1600);
        game.getChildren().addAll(sqOb.getSquare());
        allObstacles.add(sqOb);
        squareLines = sqOb.getSquare();
        starView.setY(allObstacles.get(currentObs).getPositionY());

        switchView.setY((allObstacles.get(currentObs).getPositionY()+allObstacles.get(currentObs+1).getPositionY())/2);
        generateObstacles();

        ArrayList<Group> g2 = new ArrayList<>();
        for(int i=1;i<p.size();i++){
            g2.add(p.get(i));
        }
        position = b.getBall().getCenterY();
        timer = new AnimationTimer() {

            int counter=0;
            @Override
            public void handle(long l) {
                velocity = -4;
                b.checkCollision(circleArcs,lineRects,rhombusLines,par1, par2, par3, squareLines);
                if(!isAlive)
                    gameOver(primaryStage);
                if(b.getBall().getBoundsInParent().intersects(starView.getBoundsInParent())){
                    currentObs++;
                    starView.setY(allObstacles.get(currentObs).getPositionY());
                    score.setText(""+(++points));
                }
                if(b.getBall().getBoundsInParent().intersects(switchView.getBoundsInParent())){
                    b.getBall().setFill(pickRandomColor());
                    currentColor = (Color)b.getBall().getFill();
                    switchView.setY((allObstacles.get(currentObs).getPositionY()+allObstacles.get(currentObs+1).getPositionY())/2);
                }
                if(b.getBall().getCenterY()<MID && counter==0){
                    counter++;
                    for(Obstacles entry: allObstacles){
                        entry.moveDown(velocity*3);
                    }
                    starView.setY(starView.getY()-(velocity*3));
                    switchView.setY(switchView.getY()-(velocity*3));
                }
                else {
                    counter=0;
                }
            }
        };

        EventHandler<Event> eventHandler = new EventHandler<>() {
            @Override
            public void handle(Event event) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    timer2 = new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            velocity += acc;
                            position += velocity;
                            b.getBall().setCenterY(position);
                            for (Obstacles o : allObstacles) {
                                o.move();
                            }
                        }
                    };
                    timer2.start();
                }
            }
        };
        game.getChildren().add(pauseButton);
        game.getChildren().add(score);
        s[3] = new Scene(game,420,780, Color.BLACK);
        game.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        s[3].setOnMouseClicked(eventHandler);
        s[3].setOnKeyPressed(keyEvent -> timer.start());
        s[3].setOnKeyReleased(keyEvent -> timer.stop());
        primaryStage.setScene(s[3]);
    }

    public Color pickRandomColor() {
        Color c;
        do {
            c = colors[new Random().nextInt(colors.length)];
        } while (c == currentColor);
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

    public void gameOver(Stage primaryStage) {
        game = new Pane();
        p = new ArrayList<>();
        timer.stop();
        timer2.stop();
        ImageView imageView = new ImageView(image);
        imageView.setX(8);
        imageView.setY(100);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        Group over = new Group(imageView);

        Text oops = new Text();
        oops.setFill(Color.WHITE);
        oops.setLayoutX(75);
        oops.setLayoutY(350);

        oops.setText("OOPS "+user[0].getName()+", you collided \n\twith the obstacle");
        oops.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        over.getChildren().add(oops);
        Button restartButton = new Button("Restart");
        restartButton.setPrefHeight(10);
        restartButton.setPrefWidth(90);
        restartButton.setLayoutX(175);
        restartButton.setLayoutY(480);
        restartButton.setStyle("-fx-text-fill: white");
        restartButton.setBackground(Background.EMPTY);
        restartButton.setOnAction(actionEvent -> {
            try {
                insideNewGame(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        over.getChildren().add(restartButton);

        Button exitButton = new Button("Exit");
        exitButton.setPrefHeight(10);
        exitButton.setPrefWidth(90);
        exitButton.setLayoutX(175);
        exitButton.setLayoutY(520);
        exitButton.setOnAction(actionEvent1 -> primaryStage.close());
        exitButton.setStyle("-fx-text-fill: white");
        exitButton.setBackground(Background.EMPTY);
        over.getChildren().add(exitButton);

        s[5] = new Scene(over, 420, 780, Color.BLACK);
        s[5].getStylesheets().add("https://fonts.googleapis.com/css2?family=Concert+One");
        over.setStyle("-fx-font-family: 'Concert One', cursive; -fx-font-size: 15");
        primaryStage.setScene(s[5]);
    }

    public void generateObstacles(){
         for(int i=0;i<50;i++){
            int temp;
            do {
                temp = new Random().nextInt(random.length);
            } while (temp == obstacleNumber);

             //                case 1: {
             ////                    System.out.println("doing case 1");
             //                    obstacleNumber = 1;
             //                    Cross c;
             //                    if(lastY != 0)
             //                        c = new Cross(90, lastY - 100, currentColor, pickRandomColor());
             //                    else
             //                        c = new Cross(90, lastY+300, currentColor, pickRandomColor());
             //                    allObstacles.add(c);
             //                    lastY -= 100;
             ////                    p.add(new Group());
             //                    p.getChildren().addAll(c.getCross());
             //                    break;
             //                }
             switch (temp) {
                 case 0 -> {
                     obstacleNumber = 0;
                     LineObstacle l;
                     l = new LineObstacle(lastY);
                     allObstacles.add(l);
                     p.add(new Group());
                     p.get(p.size()-1).getChildren().addAll(l.getLine());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 300;
                     break;
                 }
                 case 1 -> {
                     obstacleNumber = 1;
                     Circle c;
                     c = new Circle(210, (float) lastY, 100, 100, 0, 90, 12);
                     allObstacles.add(c);
                     p.add(new Group());
                     p.get(p.size()-1).getChildren().addAll(c.getCircle());
//                     p.get(p.size()-1).getChildren().add(c.getStarView());
//                     stars.add(c.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 500;;
                     break;
                 }
                 case 2 -> {
                     obstacleNumber = 2;
                     ConcentricCircle c;
                     c = new ConcentricCircle(210, (float) lastY, 200, 200, 0, 90, 12);
                     allObstacles.add(c);
                     p.add(new Group());
                     p.get(p.size()-1).getChildren().addAll(c.getBigCircle());
                     p.get(p.size()-1).getChildren().addAll(c.getSmallCircle());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 800;
                     break;
                 }
                 case 3 -> {
                     obstacleNumber = 3;
                     ParallelLines pl;
                     pl = new ParallelLines(lastY);
                     allObstacles.add(pl);
                     p.add(new Group());
                     p.get(p.size()-1).getChildren().addAll(pl.getParallelLineI(0));
                     p.get(p.size()-1).getChildren().addAll(pl.getParallelLineI(1));
                     p.get(p.size()-1).getChildren().addAll(pl.getParallelLineI(2));
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 800;
                     break;
                 }
                 case 4 -> {
                     obstacleNumber = 4;
                     Rhombus r;
                     r = new Rhombus(150, lastY-150);
                     allObstacles.add(r);
                     p.add(new Group());
                     p.get(p.size()-1).getChildren().addAll(r.getRhombus());
//                     p.get(p.size()-1).getChildren().add(r.getStarView());
//                     stars.add(r.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 650;
                     break;
                 }
                 case 5 -> {
                     obstacleNumber = 5;
                     Square s;
                     s = new Square(110, lastY);
                     allObstacles.add(s);
                     p.add(new Group());
                     p.get(p.size()-1).getChildren().addAll(s.getSquare());
//                     p.get(p.size()-1).getChildren().add(s.getStarView());
//                     stars.add(s.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 600;
                     break;
                 }
//                 case 6 -> {
////                    System.out.println("doing case 7");
//                     obstacleNumber = 6;
//                     VerticalLines v;
////                     if (lastY != 0)
//                         v = new VerticalLines(lastY);
////                     else
////                         v = new VerticalLines(lastY + 300);
//                     allObstacles.add(v);
//                     p.add(new Group());
//                     p.get(p.size()-1).getChildren().addAll(v.getVerticalLines());
//                     game.getChildren().add(p.get(p.size()-1));
//
//                     lastY += (p.get(p.size()-1).getBoundsInParent().getMinY()-100);
//                     System.out.println("lasty is: "+lastY);
//
////                    p.add(new Group());
////                     p.getChildren().addAll(v.getVerticalLines());
//                     break;
//                 }
             }
        }
    }
}