import Props.BirdMaker;
import Props.HouseMaker;
import Props.StarMaker;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage pstage){
        // constant
        final int screenHeight = 600;
        final int screenWidth = 1200;
        final int radiusSun = 35;
        final int radiusMoon = 42;

        //BG
        Rectangle bg1 = new Rectangle(0,0, (int)(screenWidth/2), screenHeight);
        bg1.setFill(Color.LIGHTGRAY);
        Rectangle bg2 = new Rectangle((int)(screenWidth/2), 0, (int)(screenWidth/2), screenHeight);
        bg2.setFill(Color.BLACK);
        Group bg = new Group(bg1, bg2);

        //birds
        BirdMaker bm = new BirdMaker();

        Group bribs = new Group(bm.create(screenWidth/2, screenHeight/2));

        //stars
        StarMaker starMaker = new StarMaker();
        Group stars = new Group(
                starMaker.create((9 *screenWidth/16), screenHeight/12, 7, 5),
                starMaker.create((12 *screenWidth/16), (2 * screenHeight/9), 11, 4),
                starMaker.create((21 *screenWidth/32), screenHeight/7, 9, 3),
                starMaker.create((10 *screenWidth/16), (2 * screenHeight/8), 5, 2)
        );

        //houses
        HouseMaker house = new HouseMaker();
        Group houses = new Group(
                house.create((int)(screenWidth / 6), (int)(screenHeight/2.5), Color.DARKGRAY),
                house.create((int)( 8 * screenWidth / 12), (int)(screenHeight/2.5), Color.BLACK)
        );

        //sun
        int xSun =((int)(4 * screenWidth/10));
        int ySun =(int)(screenHeight / 6);
        Circle sun = new Circle(xSun, ySun, radiusSun, Color.YELLOW);

        //rays
        int raysize = 60;
        Group rays = new Group();

         for(int counter = 0; counter < 8; counter++){
             double cos = (Math.cos(Math.toRadians(45 * counter)));
             double sin = (Math.sin(Math.toRadians( 45 * counter)));

             int tempsize = raysize;

             if(counter % 2 == 0){
                 tempsize = raysize / 2;
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

        RotateTransition rot = new RotateTransition(Duration.seconds(10), rays);
         rot.setByAngle(360);
         rot.setCycleCount(Timeline.INDEFINITE);
         rot.setOnFinished((event -> rot.play()));
         rot.play();

        //moon
        Circle moon = new Circle((int)(screenWidth - (screenWidth/10)), 100, radiusMoon);
        Stop[] moonstops = new Stop[]{
                new Stop(0, Color.LIGHTGRAY),
                new Stop(1, Color.BLACK)
        };
        LinearGradient moongradient = new LinearGradient(
                0,0,
                1,1,
                true,
                CycleMethod.NO_CYCLE,
                moonstops
        );

        moon.setFill(moongradient);

        // text
        Text txt1 = new Text((int)( 3 * screenHeight / 8), (int)(11 * screenHeight / 12), "Jour");
        txt1.setFont(new Font((int)(screenWidth / 20)));
        Text txt2 = new Text((int)( 7 * screenWidth / 10), (int)(11 * screenHeight / 12), "Nuit");
        txt2.setFill(Color.LIGHTGRAY);
        txt2.setFont(new Font( (int)(screenWidth / 20)));

        Group txt = new Group(txt1, txt2);

        Group gp = new Group(
                bg,
                bribs,
                sun,
                rays,
                moon,
                stars,
                houses,
                txt
        );

        pstage.setScene(new Scene(gp));
        pstage.setTitle("Title!");
        pstage.show();
    }
}
