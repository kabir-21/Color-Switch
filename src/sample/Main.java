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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
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
    protected static double MID = -250;
    protected static AnimationTimer timer;
    protected static AnimationTimer timer2;
    protected static Stage pStage;
    protected static int points = 0;
    protected final double acc = 0.25;
    protected double position, velocity = 0;
    protected Group game = new Group();
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
    public ArrayList<Arc> concCircs;
    public ArrayList<Rectangle> lineRects;
    public ArrayList<Rectangle[]> par1;
    public ArrayList<Rectangle[]> par2;
    public ArrayList<Rectangle[]> par3;
    public ArrayList<Line> rhombusLines;
    public ArrayList<Line> squareLines;
    protected double lastY = 300;
    public Arc aaa = new Arc();
    String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
    Image star = new Image(starUrl);
    private ImageView starView = new ImageView(star);
    private ImageView switchView = new ImageView(colorSwitch);
    public ArrayList<Pane> p = new ArrayList<>();
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
        p.add(new Pane(b.getBall()));
        game.getChildren().add(p.get(0));
        generateObstacles();
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);
        starView.setX(210);
        starView.setY(allObstacles.get(currentObs).getPositionY());
        starView.setPreserveRatio(true);
        starView.setFitHeight(20);
        switchView.setPreserveRatio(true);
        switchView.setFitHeight(20);
        switchView.setX(210);
        switchView.setY((allObstacles.get(currentObs).getPositionY()+allObstacles.get(currentObs+1).getPositionY())/2);
        game.getChildren().add(starView);
        game.getChildren().add(switchView);
//        ParallelLines cc = new ParallelLines(500);
////        Circle cc = new Circle(210, 500, 100, 100, 0, 90, 12);
//        allObstacles.add(cc);
//        game.getChildren().addAll(cc.getParallelLineI(0));
//        game.getChildren().addAll(cc.getParallelLineI(1));
//        game.getChildren().addAll(cc.getParallelLineI(2));

        //        game.getChildren().addAll(cc.getBigCircle());
//        while(true){
//            int temp;
//            do {
//                temp = new Random().nextInt(random.length);
//            } while (temp == obstacleNumber);
//            switch (temp){
//                case 0:{
//                    obstacleNumber = 0;
//                    LineObstacle l = new LineObstacle(lastY-100);
//                    allObstacles.add(l);
//                    lastY-=100;
//                    p.getChildren().addAll(l.getLine());
//                    p.getChildren().add(l.getSwitchView());
//                }
//                case 1:{
//                    obstacleNumber = 1;
//                    Cross c = new Cross(90,lastY-100, currentColor,pickRandomColor());
//                    allObstacles.add(c);
//                    lastY-=100;
//                    p.getChildren().addAll(c.getCross());
//                }
//                case 2:{
//                    obstacleNumber = 2;
//                    Circle c = new Circle(210,(float)lastY-500, 100,100,0,90,12);
//                    allObstacles.add(c);
//                    lastY-=500;
//                    p.getChildren().addAll(c.getCircle());
//                    p.getChildren().add(c.getStarView());
//                }
//                case 3:{
//                    obstacleNumber = 3;
//                    ConcentricCircle c = new ConcentricCircle(210,(float)lastY-500,200,200,0,90,12);
//                    allObstacles.add(c);
//                    lastY-=500;
//                    p.getChildren().addAll(c.getSmallCircle());
//                    p.getChildren().addAll(c.getBigCircle());
//                    p.getChildren().add(c.getStarView());
//                }
//                case 4:{
//                    obstacleNumber = 4;
//                    ParallelLines pl = new ParallelLines(lastY-500);
//                    allObstacles.add(pl);
//                    lastY-=500;
//                    p.getChildren().addAll(pl.getParallelLineI(0));
//                    p.getChildren().addAll(pl.getParallelLineI(1));
//                    p.getChildren().addAll(pl.getParallelLineI(2));
//                }
//                case 5:{
//                    obstacleNumber = 5;
//                    Rhombus r = new Rhombus(160,lastY-500);
//                    allObstacles.add(r);
//                    lastY-=500;
//                    p.getChildren().addAll(r.getRhombus());
//                    p.getChildren().add(r.getStarView());
//                }
//                case 6:{
//                    obstacleNumber = 6;
//                    Square s = new Square(120,lastY-500);
//                    allObstacles.add(s);
//                    lastY-=500;
//                    p.getChildren().addAll(s.getSquare());
//                }
//                case 7:{
//                    obstacleNumber = 7;
//                    VerticalLines v = new VerticalLines(lastY-500);
//                    allObstacles.add(v);
//                    lastY-=500;
//                    p.getChildren().addAll(v.getVerticalLines());
//                }
//            }
//            game.getChildren().add(p);
//            break;
//        }

//        Image pauseImage = new Image("C:\\Users\\kabni\\Downloads\\pause.png");
//        ImageView pauseImageView = new ImageView(pauseImage);
//        pauseImageView.setFitHeight(30);
//        pauseImageView.setPreserveRatio(true);
//        LineObstacle l1 = new LineObstacle(500);
//        allObstacles.add(l1);
//        game.getChildren().addAll(l1.getLine());
//        System.out.println("now doing");
//        generateObstacles();
//        System.out.println("done generating");
//        System.out.println("size of all obs is: "+allObstacles.size());
//        Pane arcPane = new Pane();
//        Circle circle = new Circle(210,-1200, 100, 100, 0,90,12);
//        Square ss = new Square(110,-800);
//        allObstacles.add(ss);
//        ConcentricCircle concentricCircle = new ConcentricCircle(210,-2500,200,200,0,90,12);
//        allObstacles.add(concentricCircle);
//        Pane conc = new Pane();
//        allObstacles.add(circle);
//        ParallelLines parallelLines = new ParallelLines(-1500);
//        allObstacles.add(parallelLines);
//        Pane parallelPane = new Pane();
////        VerticalLines verticalLines = new VerticalLines(-2000);
////        allObstacles.add(verticalLines);
//        Pane verticalPane = new Pane();
//        Pane p2 = new Pane();
//        p2.getChildren().addAll(ss.getSquare());
//        Pane p3 = new Pane();
//        Pane p4 = new Pane();
//        Pane p5 = new Pane();
//        Pane squarePane = new Pane();
//
//        LineObstacle lineObstacle1 = new LineObstacle(20);
//        allObstacles.add(lineObstacle1);
//        Cross cross = new Cross(90,300, currentColor, pickRandomColor());
//        allObstacles.add(cross);
//        Rhombus rhombus = new Rhombus(150,-350);
//        allObstacles.add(rhombus);
////        String starUrl = "https://freepngimg.com/thumb/star/36741-4-3d-gold-star-transparent-background.png";
////        Image star = new Image(starUrl);
////        ImageView starView = new ImageView(star);
////        starView.setX(205);
////        starView.setY(-320);
////        starView.setPreserveRatio(true);
////        starView.setFitHeight(20);
////        Square square = new Square();
////        allObstacles.add(square);
////        ArrayList<Rectangle> sqaureArr = square.getSquare();
//        ArrayList<Rectangle> crossArr = cross.getCross();
//        ArrayList<Rectangle> line1Rects = lineObstacle1.getLine();
//        arcPane.getChildren().addAll(circle.getCircle());
//        conc.getChildren().addAll(concentricCircle.getBigCircle());
//        conc.getChildren().addAll(concentricCircle.getSmallCircle());
//        parallelPane.getChildren().addAll(parallelLines.getParallelLineI(0));
//        parallelPane.getChildren().addAll(parallelLines.getParallelLineI(1));
//        parallelPane.getChildren().addAll(parallelLines.getParallelLineI(2));
////        verticalPane.getChildren().addAll(verticalLines.getVerticalLines());
//        p2.getChildren().addAll(lineObstacle1.getLine());
//        p2.getChildren().add(lineObstacle1.getSwitchView());
//        p3.getChildren().addAll(cross.getCross());
//        p4.getChildren().addAll(rhombus.getRhombus());
//        p5.getChildren().addAll(square.getSquare());
//        p.getChildren().add(b.getBall());
        position = b.getBall().getCenterY();
        timer = new AnimationTimer() {

            int counter=0;
            @Override
            public void handle(long l) {
                velocity = -4;
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
//                for(Obstacles o: allObstacles){
//                    if(o.getStarView().getBoundsInParent().intersects(b.getBall().getBoundsInParent())){
//                        if(o.getStarView().getImage() != null)
//                            score.setText(""+(++points));
//                    }
//                }
//                if(rhombus.getStarView().getBoundsInParent().intersects(b.getBall().getBoundsInParent())){
//                    if(rhombus.getStarView().getImage() != null)
//                        score.setText(""+(++points));
//                    rhombus.removeStar();
////                    System.out.println(points);
//                }
//                if(circle.getStarView().getBoundsInParent().intersects(b.getBall().getBoundsInParent())){
//                    if(circle.getStarView().getImage() != null)
//                        score.setText(""+(++points));
//                    circle.removeStar();
////                    System.out.println(points);
//                }
//                if(b.getBall().getBoundsInParent().intersects(lineObstacle1.getSwitchView().getBoundsInParent())){
//                    if(lineObstacle1.getSwitchView().getImage() != null){
//                        b.getBall().setFill(pickRandomColor());
//                        currentColor = (Color) b.getBall().getFill();
//                        lineObstacle1.removeSwitch();
//                    }
//                }
                b.checkCollision();
                if(b.getBall().getCenterY()<MID && counter==0){
                    counter++;
                    for(Obstacles entry: allObstacles){
                        entry.moveDown(velocity*3);
                    }
                    starView.setY(starView.getY()-(velocity*3));
                    switchView.setY(switchView.getY()-(velocity*3));
//                    generateObstacles();
//                    red.setLayoutY(red.getLayoutY()-velocity);
//                    red.setY
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
                else {
                    counter=0;
                }
//                System.out.println(b.getBall().getCenterY());
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
//                            lineObstacle1.move();
//                            cross.move();
//                            rhombus.move();
//                            circle.move();
//                            parallelLines.move();
////                            verticalLines.move();
//                            concentricCircle.move();
//                            ss.move();
                        }
                    };
                    timer2.start();
                }
            }
        };
//        game.getChildren().add(rhombus.getStarView());
//        game.getChildren().add(circle.getStarView());
////        game.getChildren().add(p);
//        game.getChildren().add(p2);
//        game.getChildren().add(p3);
//        game.getChildren().add(p4);
//        game.getChildren().add(p5);
//        game.getChildren().add(arcPane);
//        game.getChildren().add(conc);
//        game.getChildren().add(parallelPane);
//        game.getChildren().add(verticalPane);
        game.getChildren().add(pauseButton);
        game.getChildren().add(score);
        s[3] = new Scene(game,420,780, Color.BLACK);
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

    public static void gameOver() {
        timer.stop();
        timer2.stop();
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
                    System.out.println("doing case 0");
                     obstacleNumber = 0;
                     LineObstacle l;
                     l = new LineObstacle(lastY);
                     allObstacles.add(l);
                     p.add(new Pane());
                     p.get(p.size()-1).getChildren().addAll(l.getLine());
//                     p.get(p.size()-1).getChildren().add(l.getStarView());
//                     stars.add(l.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 300;
                     System.out.println("lasty of line is: "+ lastY);
                     break;
                 }
                 case 1 -> {
                    System.out.println("doing case 2");
                     obstacleNumber = 1;
                     Circle c;
                     c = new Circle(210, (float) lastY, 100, 100, 0, 90, 12);
                     allObstacles.add(c);
                     p.add(new Pane());
                     p.get(p.size()-1).getChildren().addAll(c.getCircle());
//                     p.get(p.size()-1).getChildren().add(c.getStarView());
//                     stars.add(c.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 500;
                     System.out.println("lasty of circle is: "+lastY);
                     break;
                 }
                 case 2 -> {
                    System.out.println("doing case 3");
                     obstacleNumber = 2;
                     ConcentricCircle c;
                     c = new ConcentricCircle(210, (float) lastY, 200, 200, 0, 90, 12);
                     allObstacles.add(c);
                     p.add(new Pane());
                     p.get(p.size()-1).getChildren().addAll(c.getBigCircle());
                     p.get(p.size()-1).getChildren().addAll(c.getSmallCircle());
//                     p.get(p.size()-1).getChildren().add(c.getStarView());
//                     stars.add(c.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 800;
                     System.out.println("lasty of big circle is: "+lastY);
                     break;
                 }
                 case 3 -> {
                    System.out.println("doing case 4");
                     obstacleNumber = 3;
                     ParallelLines pl;
                     pl = new ParallelLines(lastY);
                     allObstacles.add(pl);
                     p.add(new Pane());
                     p.get(p.size()-1).getChildren().addAll(pl.getParallelLineI(0));
                     p.get(p.size()-1).getChildren().addAll(pl.getParallelLineI(1));
                     p.get(p.size()-1).getChildren().addAll(pl.getParallelLineI(2));
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 800;
                     System.out.println("lasty of parallel is: "+lastY);
                     break;
                 }
                 case 4 -> {
                    System.out.println("doing case 5");
                     obstacleNumber = 4;
                     Rhombus r;
                     r = new Rhombus(150, lastY);
                     allObstacles.add(r);
                     p.add(new Pane());
                     p.get(p.size()-1).getChildren().addAll(r.getRhombus());
//                     p.get(p.size()-1).getChildren().add(r.getStarView());
//                     stars.add(r.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 500;
                     System.out.println("lasty of rhombus is: "+lastY);
                     break;
                 }
                 case 5 -> {
                    System.out.println("doing case 6");
                     obstacleNumber = 5;
                     Square s;
                     s = new Square(110, lastY);
                     allObstacles.add(s);
                     p.add(new Pane());
                     p.get(p.size()-1).getChildren().addAll(s.getSquare());
//                     p.get(p.size()-1).getChildren().add(s.getStarView());
//                     stars.add(s.getStarView());
                     game.getChildren().add(p.get(p.size()-1));
                     lastY -= 600;
                     System.out.println("lasty of sqaure is : "+lastY);
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