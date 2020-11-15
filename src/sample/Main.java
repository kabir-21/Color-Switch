package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
        final Scene[] s = new Scene[10];
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pane p = new Pane();
                Ball b = new Ball(10, Color.RED);
                p.getChildren().add(b.getBall());
                s[1] = new Scene(p,420,780, Color.WHITE);
                primaryStage.setScene(s[1]);
            }
        };
        Image image = new Image(new FileInputStream("C:\\Users\\kabni\\Downloads\\colorswitchincon.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(100);
        imageView.setPreserveRatio(true);
        Group g = new Group(imageView);
        Button startButton = new Button("Start");
        startButton.setPrefHeight(10);
        startButton.setPrefWidth(90);
        startButton.setLayoutX(175);
        startButton.setLayoutY(400);
        startButton.setOnAction(event);

        g.getChildren().add(startButton);
        s[0] = new Scene(g,420,780, Color.WHITE);
        primaryStage.setScene(s[0]);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
