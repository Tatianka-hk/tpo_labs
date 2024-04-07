package lab_1;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class OCanvas extends JPanel{
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Pool> pools = new ArrayList<>();

    public int amount = 0;
    public void add(Ball b){
        this.balls.add(b);
        repaint();
    }
    public void add_pool(Pool b){
        this.pools.add(b);
    }
    @Override
    public void paintComponent(Graphics g){
//        System.out.println("Кількість зниклих кульок - " + amount);

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for (Ball b : balls) {
            for (Pool p : pools) {
                double d = Math.sqrt(Math.pow(p.getX() - b.getX(), 2) + Math.pow(p.getY() -  b.getY(), 2));
                if (d + b.getR() < p.getR()) {
                    b.setIsInPool(true);
                    amount++;
                    System.out.println("Кількість зниклих кульок - " + amount);
                    balls.remove(b);
                    break;
                }
            }
            if(!b.getIsInPool()){
                b.draw(g2);
            }

        }
        for(int i=0; i<pools.size();i++){
            Pool p = pools.get(i);
            p.draw(g2);
        }
    }

}