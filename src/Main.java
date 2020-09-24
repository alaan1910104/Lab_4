import Props.BirdMaker;
import Props.HouseMaker;
import Props.RayMaker;
import Props.StarMaker;
import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage pstage){
        // constant
        final int screenHeight = 600;
        final int screenWidth = 1200;
        final int radiusSun = (int)(screenHeight / 17);
        final int radiusMoon = (int)(screenHeight / 14);


        //BackGround
        Rectangle bg1 = new Rectangle(0,0, (int)(screenWidth/2), screenHeight);
        bg1.setFill(Color.LIGHTGRAY);

        Rectangle bg2 = new Rectangle((int)(screenWidth/2), 0, (int)(screenWidth/2), screenHeight);
        bg2.setFill(Color.BLACK);

        Group bg = new Group(bg1, bg2);


        //birds
        BirdMaker birdMaker = new BirdMaker(screenHeight);

        Group bribs = new Group(
                birdMaker.create(screenWidth/4, screenHeight/8),
                birdMaker.create(screenWidth/8, screenHeight / 4)
        );


        //stars
        StarMaker starMaker = new StarMaker();

        Group stars = new Group(
                starMaker.create((9 *screenWidth/16), screenHeight/12, 7, 5),
                starMaker.create((12 *screenWidth/16), (2 * screenHeight/9), 11, 4),
                starMaker.create((21 *screenWidth/32), screenHeight/7, 9, 3),
                starMaker.create((10 *screenWidth/16), (2 * screenHeight/8), 5, 2)
        );


        //houses
        HouseMaker houseMaker = new HouseMaker(screenHeight, screenWidth);

        Group houses = new Group(
                houseMaker.create((int)(screenWidth / 6), (int)(screenHeight/2.5), Color.DARKGRAY),
                houseMaker.create((int)( 8 * screenWidth / 12), (int)(screenHeight/2.5), Color.BLACK)
        );


        //sun
        Circle sun = new Circle(((int)(4 * screenWidth/10)), (int)(screenHeight / 6), radiusSun, Color.YELLOW);

        FillTransition fl = new FillTransition(Duration.seconds(2), sun);
        fl.setToValue(Color.ORANGE);
        fl.setAutoReverse(true);
        fl.setCycleCount(Timeline.INDEFINITE);
        fl.play();


        //rays
        RayMaker rayMaker = new RayMaker();

        Group rays = rayMaker.create(sun.getCenterX(), sun.getCenterY(), sun.getRadius());


        //moon
        Circle moon = new Circle((int)(screenWidth - (screenWidth/10)), (int)(screenHeight / 8), radiusMoon);

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


        // Group de tout les props
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


        //show
        pstage.setScene(new Scene(gp));
        pstage.setTitle("Title!");
        pstage.show();
    }
}
