import Props.House;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args){ launch(args);}

    @Override
    public void start(Stage pstage){
        // constant
        final int screenHeight = 600;
        final int screenWidth = 1200;
        //BG
        Rectangle bg1 = new Rectangle(0,0, (int)(screenWidth/2), screenHeight);
        bg1.setFill(Color.LIGHTGRAY);
        Rectangle bg2 = new Rectangle((int)(screenWidth/2), 0, (int)(screenWidth/2), screenHeight);
        bg2.setFill(Color.BLACK);
        //birds
        //stars
        //houses
        House house = new House();
        //sun
        Circle sun = new Circle(((int)(screenWidth/2) - 100),100,35,Color.YELLOW);
        //rays
        //moon
        Circle moon = new Circle((int)(screenWidth - 100), 100, 42);
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


        Group gp = new Group(bg1,
                bg2,
                sun,
                moon,
                house.create((int)(screenWidth/4) - 105, (int)(screenHeight/2.5), Color.DARKGRAY),
                house.create((int)( 3 * screenWidth/4) - 105, (int)(screenHeight/2.5), Color.BLACK)
        );

        pstage.setScene(new Scene(gp));
        pstage.setTitle("Title!");
        pstage.show();
    }
}
