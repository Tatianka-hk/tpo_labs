package lab_5.task1_3;

import java.util.concurrent.ArrayBlockingQueue;

public class Channel implements Runnable {
    private ArrayBlockingQueue<Customer> queue;
    private int serviceTime;
    private boolean stopped = false;

    public Channel(ArrayBlockingQueue<Customer> queue, int serviceTime) {
        this.queue = queue;
        this.serviceTime = serviceTime;
    }

    public void run() {
        while (!stopped) {
            Customer customer;
            try {
                // Беремо клієнта з черги
                customer = queue.take();
            } catch (InterruptedException e) {
                System.out.println("Interrupted while taking client from queue.");
                return;
            }
            // Обслуговуємо клієнта
            try {
                Thread.sleep(serviceTime);
            } catch (InterruptedException e) {
                System.out.println("Interrupted while servicing client.");
            }

            System.out.println("Client " + customer.getId() + " serviced by channel " + Thread.currentThread().getId() + ".");
        }
    }
    public void stop() {
        this.stopped = true;
    }
}