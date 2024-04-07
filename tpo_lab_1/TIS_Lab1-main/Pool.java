package lab_1;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Pool {
    private Component canvas;
    private static final int Diametr = 40;

    private int x = 0;
    private int y = 0;

    public Pool(Component c, int x,int y){
        this.canvas = c;
        this.x = x;
        this.y = y;
    }
    public void draw (Graphics2D g2){
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x,y,Diametr,Diametr));

    }
    public int getR(){return this.Diametr/2;};
    public int getD(){return this.Diametr;};
    public int getX(){return this.x;};
    public int getY(){return this.y;};



}
