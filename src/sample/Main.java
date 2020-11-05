package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Color Switch");
        Pane p = new Pane();
        Scene s = new Scene(p,420,780, Color.WHITE);
//        Circle ball = new Circle(10, Color.RED);
//        ball.relocate(205,750);
        Ball b = new Ball(10, Color.RED);
        p.getChildren().add(b.getBall());
        EventHandler<Event> eventHandler = new EventHandler<Event>() {
            AnimationTimer timer2;
            @Override
            public void handle(Event event) {
                if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
                    timer2 = new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            mouseHandle();
                        }

                        private void mouseHandle() {
                            b.setPosition(b.GRAVITY);
                        }
                    };
                    timer2.start();
                }
                if(event.getEventType() == KeyEvent.KEY_PRESSED){
                    timer2.stop();
                    AnimationTimer timer = new AnimationTimer() {
                        private long lastUpdate = 0;
                        @Override
                        public void handle(long l) {
                            if(l-lastUpdate>= 28_000_000){
                                lastUpdate = l;
                            }
//                            long elapsed = l-lastUpd/sed/1_000_000_000.0;
                            keyHandle();
//                            lastUpdate = l;
                        }
                        private void keyHandle() {
                            b.setPosition(-100);
                            stop();
                        }
                    };
                    timer.start();
                    timer2.start();
                }
//                if(keyEvent.getCode().equals(KeyCode.UP)){
//
//                }
            }


        };
//        WildCard wild = new WildCard(15, Color.BLACK);
//        p.getChildren().add(wild.getBall());c
        s.setOnMouseClicked(eventHandler);
        s.setOnKeyPressed(eventHandler);
//        Timeline timeline = new Timeline(new KeyFrame(Duration.INDEFINITE));
//        s.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getButton().equals(MouseButton.PRIMARY)) {
//                    AnimationTimer timer = new AnimationTimer() {
//                        @Override
//                        public void handle(long l) {
//                            handleMouse(event);
//                        }
//                    };
//                    timer.start();
//                }
//                if(event.getButton().equals(MouseButton.SECONDARY)){
////                    handleUp(event);
//                    AnimationTimer timer2 = new AnimationTimer() {
//                        @Override
//                        public void handle(long l) {
//                            handleUp(event);
//                        }
//                    };
//                    timer2.start();
////                    timer.stop();
//                }
//            }
//
//            private void handleUp(MouseEvent event) {
//                b.setPosition(-100);
//            }
//
//            private void handleMouse(MouseEvent event) {
//                b.setPosition(b.GRAVITY);
//            }
//        });
//        ImageView imageView = new ImageView(wild.image);
//        imageView.
//        imageView.setFitHeight(10);
//        imageView.setFitWidth(10);
//        imageView.setPreserveRatio(true);
//        imageView.setX(205);imageView.setY(600);
//        final HBox pictureRegion = new HBox();
//        pictureRegion.getChildren().add(imageView);
        //        Group root2 = new Group(imageView);
//        p.getChildren().add(pictureRegion);
        primaryStage.setScene(s);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
