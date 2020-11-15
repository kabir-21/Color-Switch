package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Color Switch");
        Image image = new Image(new FileInputStream("C:\\Users\\kabni\\Downloads\\colorswitchincon.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(100);
        imageView.setPreserveRatio(true);
        final Player[] user = new Player[1];
        final Scene[] s = new Scene[10];
//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Pane p = new Pane();
//                Ball b = new Ball(10, Color.RED);
//                p.getChildren().add(b.getBall());
//                s[2] = new Scene(p,420,780, Color.WHITE);
//                primaryStage.setScene(s[2]);
//            }
//        };
//        EventHandler<ActionEvent> eventExit = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                primaryStage.close();
//            }
//        };

        Group gName = new Group(imageView);
        TextField enterName = new TextField("Enter your name");
        enterName.setAlignment(Pos.CENTER);
        gName.getChildren().add(enterName);
        enterName.setLayoutX(140);
        enterName.setLayoutY(400);

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
                Ball b = new Ball(10, Color.RED);
                p.getChildren().add(b.getBall());
                s[3] = new Scene(p,420,780, Color.WHITE);
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
            s[2] = new Scene(insideStart,420,780, Color.WHITE);
            primaryStage.setScene(s[2]);
        });

        s[0] = new Scene(gName, 420, 780, Color.WHITE);
        primaryStage.setScene(s[0]);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
