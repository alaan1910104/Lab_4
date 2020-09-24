package Props;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class HouseMaker {

    private int screenHeight, screenWidth;

    public HouseMaker(int screenHeight, int screenWidth){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public Group create(int x, int y, Color shadowcolor){
        //constant
        final int width = (int)(this.screenWidth * 0.18);
        final int height = (int)(this.screenHeight * 0.2);
        final int heightToit = (int)(3 * height / 4);


        //mur
        Rectangle mur = new Rectangle(
                x + (int)(width/10),
                y + heightToit,
                width - (int)(width/5),
                height);

        mur.setFill(Color.BEIGE);
        mur.setEffect(new DropShadow(10, -(width / 5), (width / 10), shadowcolor));


        //fenetre
        Rectangle fenetre = new Rectangle(
                x + (int)( width / 2),
                y + heightToit + (int)(height / 4),
                (int)(width / 3),
                (int)(height / 3)
                );

        fenetre.setFill(Color.CYAN);


        //bars
        int girth = 3;

        Rectangle perimetreFenetre = new Rectangle(
                x + (int)( width / 2),
                y + heightToit + (int)(height / 4),
                (int)(width / 3),
                (int)(height / 3)
        );

        perimetreFenetre.setFill(Color.TRANSPARENT);
        perimetreFenetre.setStroke(Color.GRAY);
        perimetreFenetre.setStrokeWidth(girth);

        Line bar1 = new Line(
                x + (int)( width / 2) + girth,
                (int)((y + heightToit + (height / 4)) + (height/6)),
                x + (int)(5 * width / 6) - girth,
                (int)((y + heightToit + (height / 4)) + (height/6))
        );

        bar1.setStroke(Color.GRAY);
        bar1.setStrokeWidth(girth);

        Line bar2 = new Line(
                (int)((x + ( width / 2)) + width/6),
                y + heightToit + (int)(height / 4) + girth,
                (int)((x + ( width / 2)) + width/6),
                y + heightToit +  (int)(7 * height / 12) - girth
        );

        bar2.setStroke(Color.GRAY);
        bar2.setStrokeWidth(girth);

        Group fenetreComplet = new Group(fenetre, perimetreFenetre, bar1, bar2);


        //toit
        Polygon toit = new Polygon(
                x, y + heightToit,
                x + width, y + heightToit,
                x + (int)(width/2), y
                );

        toit.setFill(Color.DARKRED);
        toit.setEffect(new DropShadow(10, -(width / 5), (width / 10) + 5, shadowcolor));


        //porte
        Rectangle port = new Rectangle(
                x + (int)(width / 6),
                y + heightToit + (int)(height/3.5),
                (int)(width/4),
                (int)(2.5 * height/3.5) + 1
        );

        port.setFill(Color.SADDLEBROWN);

        Circle knob = new Circle(
                x + (int)(5 * width / 14),
                y + heightToit + (int)(3 * height/5),
                (int)(width / 18),
                Color.YELLOWGREEN
        );

        Group porte = new Group(port,knob);


        return new Group(toit, mur, porte, fenetreComplet);
    }

}
