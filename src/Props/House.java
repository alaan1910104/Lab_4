package Props;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class House extends Node {
    private int x;
    private int y;

    public House(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Group create(){
        //constant
        int width = 210;
        int height = 120;
        int conection = 100;
        //mur
        Rectangle mur = new Rectangle(x + 10, y + conection, width - 20, height);
        mur.setFill(Color.BEIGE);
        //fenetre
        //bars
        //toit
        Polygon toit = new Polygon(
                x, y + conection,
                x + width, y + conection,
                x + (int)(width/2), y
                );
        toit.setFill(Color.DARKRED);
        //porte
        Rectangle porte = new Rectangle(
                x + (int)(width / 6),
                y + conection + (int)(height/3.5),
                50,
                (int)(2.5 * height/3.5) + 1);
        porte.setFill(Color.SADDLEBROWN);

        //shadow

        return new Group(mur,toit, porte);
    }

}
