package lab_1;

import javax.swing.*;
import java.util.concurrent.locks.ReentrantLock;
public class Main {



    public static void main(String[] args) {

        // для завдань 1-4
//        BounceFrame frame = new BounceFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        frame.setVisible(true);
//        System.out.println("Thread name = " + Thread.currentThread().getName());


        //для 5 завдання
//        Runnable runnable_part_1 = new Runnable() {
//            public void run() {
//
//                System.out.println("PART 1");
//                Thread first = new Thread(new Thread_5('|', false));
//                Thread second = new Thread(new Thread_5('-', false));
//                first.start();
//                second.start();
//            }
//        };
//
//        Runnable runnable_part_2 = new Runnable() {
//            public void run() {
//                System.out.println("");
//                System.out.println("PART 2");
//                Control control = new Control();
//                Thread_5 first= new Thread_5('|', true,control);
//                Thread_5 second= new Thread_5('-', true,control);
//                first.start();
//                second.start();
//            }
//        };
//
//
//        try {
//            Thread part_1=new Thread(runnable_part_1);
//            part_1.start();
//            part_1.join();
//            Thread.sleep(10);
//            Thread part_2 = new Thread(runnable_part_2);
//            part_2.start();
//            part_2.join();
//
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
        // 6 завдання перша частина
//        Counter counter =new Counter();
//        Runnable run1 = new Runnable () {
//            public void run() {
//                for (int i = 0; i < 1_000_000; i++) {
//                    counter.increment();
//                }
//            }
//        };
//        //1_000_000
//        Runnable run2 = new Runnable () {
//            public void run() {
//                for (int i = 0; i < 1_000_000; i++) {
//                    counter.decrement();
//                }
//            }
//        };
//        Runnable unsynchronized_part = new Runnable () {
//            public void run() {
//                Thread inc = new Thread(run1);
//                Thread decr = new Thread(run2);
//
//                inc.start();
//                decr.start();
//            }
//        };
//        Thread part1 = new Thread(unsynchronized_part);
//        part1.start();
//        try {
//            part1.join();
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        System.out.println("Counter for part 6.1 = " + counter.getCount());



        // 6 завдання друга частина
//        Counter counter =new Counter();
//        Runnable run1 = new Runnable () {
//            public void run() {
//                for (int i = 0; i < 1_000_000; i++) {
//                    counter.increment_synchronized_method();
//                }
//            }
//        };
//        //1_000_000
//        Runnable run2 = new Runnable () {
//            public void run() {
//                for (int i = 0; i < 1_000_000; i++) {
//                    counter.decrement_synchronized_method();
//                }
//            }
//        };
//        Runnable unsynchronized_part = new Runnable () {
//            public void run() {
//                Thread inc = new Thread(run1);
//                Thread decr = new Thread(run2);
//
//                inc.start();
//                decr.start();
//            }
//        };
//        Thread part2 = new Thread(unsynchronized_part);
//        part2.start();
//        try {
//            part2.join();
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        System.out.println("Counter for part 6.2 = " + counter.getCount());
//        // 6 завдання 3 частина
//        Counter counter =new Counter();
//        Runnable run1 = new Runnable () {
//            public void run() {
//                for (int i = 0; i < 1_000_000; i++) {
//                    counter.increment_synchronized_block();
//                }
//            }
//        };
//        //1_000_000
//        Runnable run2 = new Runnable () {
//            public void run() {
//                for (int i = 0; i < 1_000_000; i++) {
//                    counter.decrement_synchronized_block();
//                }
//            }
//        };
//        Runnable unsynchronized_part = new Runnable () {
//            public void run() {
//                Thread inc = new Thread(run1);
//                Thread decr = new Thread(run2);
//
//                inc.start();
//                decr.start();
//            }
//        };
//        Thread part3 = new Thread(unsynchronized_part);
//        part3.start();
//        try {
//            part3.join();
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        System.out.println("Counter for part 6.3 = " + counter.getCount());

        // 6 завдання 4 частина
        Counter counter =new Counter();
        ReentrantLock lock = new ReentrantLock();
        Runnable run1 = new Runnable () {
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    lock.lock();
                    counter.increment();
                    lock.unlock();
                }
            }
        };
        //1_000_000
        Runnable run2 = new Runnable () {
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    lock.lock();
                    counter.decrement();
                    lock.unlock();
                }
            }
        };
        Runnable unsynchronized_part = new Runnable () {
            public void run() {
                Thread inc = new Thread(run1);
                Thread decr = new Thread(run2);

                inc.start();
                decr.start();
            }
        };
        Thread part4 = new Thread(unsynchronized_part);
        part4.start();
        try {
            part4.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Counter for part 6.4 = " + counter.getCount());

    }
}