package lab_1;

import java.awt.*;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try{
            for(int i=1; i<100; i++){
//                System.out.println("i = "
//                        + i);
                b.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
//                System.out.println("COLOR Thread name = "
//                        + b.getColor());
                Thread.sleep(5);
                if (b.getIsInPool()) {
                    // Thread.currentThread().interrupt();
                    System.out.println("Зник потік name = "+ Thread.currentThread().getName());
                    break;
                }


            }
        } catch(InterruptedException ex){

        }
    }
}