package Props;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.util.Duration;


public class BirdMaker {
    private  int screenHeight;

    public BirdMaker(int screenHeight){
        this.screenHeight = screenHeight;
    }

    public Group create(int x, int y){
        // Constants
        final int wingspan = (int)(screenHeight / 12);
        final int deltaWing = 2;

        // arc 1
        QuadCurve wing1 = new QuadCurve(
                x,
                y,
                 x + (int)(wingspan / 2),
                y - (int)(5 * wingspan/8),
                x + wingspan,
                y - (int)(wingspan / 10)
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
        // timeline for wings going up
        Timeline tmUp = new Timeline();
        tmUp.setOnFinished((event -> tmUp.play()));
        tmUp.setCycleCount(Timeline.INDEFINITE);

        // timeline for wings going down
        Timeline tmDown = new Timeline();
        tmDown.setOnFinished((event -> tmDown.play()));
        tmDown.setCycleCount(Timeline.INDEFINITE);


        // diminuire le endY et augmenter le startY, pause et commencer a decendre s'ill arrive au fin
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
           }
        });

        // augmenter le end y et diminuire le startY, pause et commercer a monter s'ill arrive en bas
        KeyFrame kf2 = new KeyFrame(Duration.millis(50), (event -> {
           wing1.setEndY(wing1.getEndY() + deltaWing);
           wing2.setEndY(wing2.getEndY() + deltaWing);

           wing1.setStartY(wing1.getStartY() - deltaWing / 2);
           wing2.setStartY(wing2.getStartY() - deltaWing / 2);

           if(wing1.getEndY() >= y + wingspan / 10){
               tmDown.pause();
               tmUp.playFromStart();
           }
        }));

        // add les keyframes
        tmUp.getKeyFrames().add(kf1);
        tmDown.getKeyFrames().add(kf2);

        tmUp.play();


        return new Group(wing1, wing2);
    }
}
