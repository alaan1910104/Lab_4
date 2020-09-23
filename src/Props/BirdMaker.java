package Props;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.time.Instant;

public class BirdMaker {

    public Group create(int x, int y){
        // Constants
        final int wingspan = 50;

        // arc 1
        QuadCurve wing1 = new QuadCurve(
                x,
                y,
                 x + wingspan / 2,
                y - (5 * wingspan/8),
                x + wingspan,
                y - wingspan / 10
        );
        wing1.setStroke(Color.BLACK);
        wing1.setFill(Color.TRANSPARENT);

        // arc 2
        QuadCurve wing2 = new QuadCurve(
                x,
                y,
                x - wingspan / 2,
                y - (5 * wingspan/8),
                x - wingspan,
                y - wingspan / 10
        );
        wing2.setStroke(Color.BLACK);
        wing2.setFill(Color.TRANSPARENT);
        // animation

        final int deltaWing = 2;
        int slideCounter = 0;


       Timeline tmUp = new Timeline();
       Timeline tmDown = new Timeline();
       tmUp.setOnFinished((event -> tmUp.play()));
       tmUp.setCycleCount(Timeline.INDEFINITE);
       tmDown.setOnFinished((event -> tmDown.play()));
       tmDown.setCycleCount(Timeline.INDEFINITE);


       KeyFrame kf1 = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
           wing1.setEndY(wing1.getEndY() - deltaWing);
           wing2.setEndY(wing2.getEndY() - deltaWing);

           wing1.setStartY(wing1.getStartY() + deltaWing / 2);
           wing2.setStartY(wing2.getStartY() + deltaWing / 2);

               if(wing1.getEndY() <= y - (4 * wingspan / 5)){
                   tmUp.pause();
                   tmDown.playFromStart();
               }
               if(wing1.getEndY() >= y + wingspan / 10){
                   tmDown.pause();
                   tmUp.playFromStart();
               }
           }
       });

       KeyFrame kf2 = new KeyFrame(Duration.millis(50), (event -> {
           wing1.setEndY(wing1.getEndY() + deltaWing);
           wing2.setEndY(wing2.getEndY() + deltaWing);

           wing1.setStartY(wing1.getStartY() - deltaWing / 2);
           wing2.setStartY(wing2.getStartY() - deltaWing / 2);

           if(wing1.getEndY() <= y - (4 * wingspan / 5)){
               tmUp.pause();
               tmDown.playFromStart();
           }
           if(wing1.getEndY() >= y + wingspan / 10){
               tmDown.pause();
               tmUp.playFromStart();
           }
       }));
       tmUp.getKeyFrames().add(kf1);
       tmDown.getKeyFrames().add(kf2);

       tmUp.play();

        return new Group(wing1, wing2);
    }
}
