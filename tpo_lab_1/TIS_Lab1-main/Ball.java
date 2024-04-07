 package lab_1;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.Stack;

 class Ball {
  private Component canvas;
  private static final int Diametr = 20;
  private int x = 0;
  private int y= 0;
  private int dx = 2;
  private int dy = 2;
  private boolean isInPool = false;
  private   Color color = Color.gray;


  public Ball(Component c){
   this.canvas = c;


   if(Math.random()<0.5){
    x = new Random().nextInt(this.canvas.getWidth());
    y = 0;
   }else{
    x = 0;
    y = new Random().nextInt(this.canvas.getHeight());
   }
  }

  public Ball(Component c, Color color){
   this.canvas = c;
   this.color = color;


   if(Math.random()<0.5){
    x = new Random().nextInt(this.canvas.getWidth());
    y = 0;
   }else{
    x = 0;
    y = new Random().nextInt(this.canvas.getHeight());
   }
  }

  public static void f(){
   int a = 0;
  }

  public void draw (Graphics2D g2){
   Ellipse2D.Double ball = new Ellipse2D.Double(x, y, Diametr, Diametr);
   g2.setColor(this.color);
   g2.fill(ball);

  }

  public void move(){
   x+=dx;
   y+=dy;
   if(x<0){
    x = 0;
    dx = -dx;
   }
   if(x+Diametr>=this.canvas.getWidth()){
    x = this.canvas.getWidth()-Diametr;
    dx = -dx;
   }
   if(y<0){
    y=0;
    dy = -dy;
   }
   if(y+Diametr>=this.canvas.getHeight()){
    y = this.canvas.getHeight()-Diametr;
    dy = -dy;
   }
   this.canvas.repaint();
   this.canvas.revalidate();

  }
  public int getR(){return this.Diametr/2;};
  public int getD(){return this.Diametr;};
  public int getX(){return this.x;};
  public int getY(){return this.y;};
  public void setIsInPool(boolean f){ this.isInPool = f;};
  public boolean getIsInPool(){return this.isInPool;};
  public void setColor(Color color){this.color = color;}
  public Color getColor(){ return this.color;}


 }

