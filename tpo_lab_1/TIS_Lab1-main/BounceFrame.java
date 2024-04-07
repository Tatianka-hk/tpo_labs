package lab_1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;
public class BounceFrame extends JFrame {
    private OCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new OCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
//        для 3 частини лаби
        JButton buttonRed = new JButton("Add red ball");
        JButton buttonBlue = new JButton("Add blue ball");
        //
        JButton JOINEX = new JButton("JOIN()");
        JButton buttonStop = new JButton("Stop");
        //додавання луз
//        Pool p1 = new Pool(canvas, 0, 0);
//        canvas.add_pool(p1);
//        Pool p2 = new Pool(canvas,WIDTH-60, 0);
//        canvas.add_pool(p2);
//        Pool p3 = new Pool(canvas, 0 , HEIGHT-120);
//        canvas.add_pool(p3);
//        Pool p4 = new Pool(canvas, WIDTH-60, HEIGHT-120);
//        canvas.add_pool(p4);

        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        JOINEX.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int option = rand.nextInt(2); // випадкове ціле число 0 або 1
                Ball b;
                if (option == 0) {
                     b = new Ball(canvas, Color.RED);
                } else {
                    b = new Ball(canvas, Color.BLUE);
                }


                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();

                try {
                    thread.join();
                    SwingUtilities.invokeLater(() -> canvas.repaint());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Thread name = " +
                        thread.getName());
//                thread.join();

            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        buttonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.RED);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());

//                System.exit(0);
            }
        });
        buttonBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.BLUE);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(Thread.NORM_PRIORITY);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());

//                System.exit(0);
            }
        });



//        buttonPanel.add(buttonStart);
        // для 3 частини завдання
//        buttonPanel.add(buttonRed);
//        buttonPanel.add(buttonBlue);
        // для 4 частини завдання
//        buttonPanel.add(JOINEX);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
