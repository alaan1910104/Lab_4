package Props;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class RayMaker {


    public Group create(double xSun, double ySun, double radiusSun){
        //constant / group
        final int raySize = (int)(radiusSun * 2);
        Group rays = new Group();

        // create 8 rays, each 45 degrees
        for(int counter = 0; counter < 8; counter++){
            double cos = (Math.cos(Math.toRadians(45 * counter)));
            double sin = (Math.sin(Math.toRadians( 45 * counter)));
            int tempsize = raySize;

            if(counter % 2 == 0){
                tempsize = raySize / 2;
            }

            Line l = new Line(
                    (int)(xSun + (radiusSun + (radiusSun / 2)) * cos),
                    (int)(ySun - (radiusSun + (radiusSun/2)) *  sin),
                    (int)(xSun + (radiusSun + ((radiusSun / 2) + tempsize)) *  cos),
                    (int)(ySun - (radiusSun + ((radiusSun/2) + tempsize)) *  sin)
            );

            l.setStroke(Color.YELLOW);

            rays.getChildren().add(l);
        }

        // transition de rotation
        RotateTransition rot = new RotateTransition(Duration.seconds(10), rays);
        rot.setByAngle(360);
        rot.setCycleCount(Timeline.INDEFINITE);
        rot.setOnFinished((event -> rot.play()));
        rot.play();

        return rays;
    }
}
