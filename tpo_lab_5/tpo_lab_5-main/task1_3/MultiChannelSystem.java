package lab_5.task1_3;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;
public class MultiChannelSystem {
    private static final int NUM_CHANNELS = 4;
    private static final int QUEUE_SIZE = 10;
    private static final int SERVICE_TIME = 500;
    private static final int NUM_CUSTOMERS = 100;

    public static MultiChannelSystemResult simulate() {

        ArrayBlockingQueue<Customer> queue = new ArrayBlockingQueue<>(QUEUE_SIZE);
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CHANNELS);


        List<Channel> channels = new ArrayList<>();
        for (int i = 0; i < NUM_CHANNELS
                ; i++) {
            Channel channel = new Channel(queue, SERVICE_TIME);
            executor.execute(channel);
            channels.add(channel);
        }



        Random random = new Random();
        int rejectedCustomers = 0; // Кількість відмовлених клієнтів

        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            try {
                queue.put(new Customer(i, random.nextInt(1000)));
            } catch (InterruptedException e) {
                System.out.println("Interrupted while adding customer to queue.");
            }

            if (queue.remainingCapacity() == 0) {
                rejectedCustomers++;
            }
        }
        for (Channel channel : channels) {
            channel.stop();
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }



        double avgQueueLength = (double)queue.size() / NUM_CUSTOMERS;
        double rejectionProbability = (double)rejectedCustomers / NUM_CUSTOMERS;

        System.out.println("Average queue length: " + avgQueueLength);
        System.out.println("Rejection probability: " + rejectionProbability);
        return new MultiChannelSystemResult(avgQueueLength,rejectionProbability );
    }
}

