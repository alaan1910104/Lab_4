package Props;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class House extends Node {

    public Group create(int x, int y, Color shadowcolor){
        //constant
        int width = 210;
        int height = 120;
        int conection = 100;
        //mur
        Rectangle mur = new Rectangle(x + 10,
                y + conection,
                width - 20,
                height);
        mur.setFill(Color.BEIGE);
        mur.setEffect(new DropShadow(10, -20, 20, shadowcolor));

        //fenetre
        Rectangle fenetre = new Rectangle(
                x + (int)( width / 2),
                y + conection + (int)(height / 4),
                (int)(width / 3),
                (int)(height / 3)
                );
        fenetre.setFill(Color.CYAN);
        //bars
        Rectangle recbar = new Rectangle(
                x + (int)( width / 2),
                y + conection + (int)(height / 4),
                (int)(width / 3),
                (int)(height / 3)
        );

        int girth = 3;

        recbar.setFill(Color.TRANSPARENT);
        recbar.setStroke(Color.GRAY);
        recbar.setStrokeWidth(girth);

        Line bar1 = new Line(
                x + (int)( width / 2) + girth,
                (int)((y + conection + (height / 4)) + (height/6)),
                x + (int)(5 * width / 6) - girth,
                (int)((y + conection + (height / 4)) + (height/6))
        );
        bar1.setStroke(Color.GRAY);
        bar1.setStrokeWidth(girth);

        Line bar2 = new Line(
                (int)((x + ( width / 2)) + width/6),
                y + conection + (int)(height / 4) + girth,
                (int)((x + ( width / 2)) + width/6),
                y + conection +  (int)(7 * height / 12) - girth
        );
        bar2.setStroke(Color.GRAY);
        bar2.setStrokeWidth(girth);

        Group fent = new Group(fenetre, recbar, bar1, bar2);
        //toit
        Polygon toit = new Polygon(
                x, y + conection,
                x + width, y + conection,
                x + (int)(width/2), y
                );
        toit.setFill(Color.DARKRED);
        toit.setEffect(new DropShadow(10, -20, 25, shadowcolor));

        //porte
        Rectangle port = new Rectangle(
                x + (int)(width / 6),
                y + conection + (int)(height/3.5),
                (int)(width/4),
                (int)(2.5 * height/3.5) + 1);
        port.setFill(Color.SADDLEBROWN);

        Circle knob = new Circle(
                x + (int)(5 * width / 14),
                y + conection + (int)(3 * height/5),
                (int)(width / 18),
                Color.YELLOWGREEN);

        Group porte = new Group(port,knob);
        //shadow


        return new Group(toit, mur, porte, fent);
    }

}
