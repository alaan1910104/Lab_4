package Props;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;


public class StarMaker {

    public Polygon create(int x, int y, int r1, int duration){
        // constants

        int r2 = r1 + (r1*2);

        double[][] points = new double[10][2];

        for(int xcont = 0; xcont < 10; xcont++){
            if((xcont % 2) == 0){
            points[xcont][0] = x + (r2 * Math.cos(Math.toRadians(90 + (72 * xcont))));
            points[xcont][1] = y - (r2 * Math.sin(Math.toRadians(90 + (72 * xcont))));
            }
            else{
            points[xcont][0] = x + (r1 * Math.cos(Math.toRadians(126 + (72 * xcont))));
            points[xcont][1] = y - (r1 * Math.sin(Math.toRadians(126 + (72 * xcont))));
            }
        }

        // etoile
        Polygon star = new Polygon(

                points[0][0], points[0][1],
                points[9][0], points[9][1],
                points[8][0], points[8][1],
                points[7][0], points[7][1],
                points[6][0], points[6][1],
                points[5][0], points[5][1],
                points[4][0], points[4][1],
                points[3][0], points[3][1],
                points[2][0], points[2][1],
                points[1][0], points[1][1]
        );

        star.setFill(Color.YELLOW);

        //fade transition
        FadeTransition fade = new FadeTransition(Duration.seconds(duration), star);
        fade.setFromValue(1.0);
        fade.setToValue(0.1);
        fade.setAutoReverse(true);
        fade.setCycleCount(Animation.INDEFINITE);
        fade.playFromStart();

        return star;

    };
}
