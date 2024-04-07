package lab_5.task1_3;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelSimulation {
    private static final int NUM_SIMULATIONS = 10;

    public static void run() {

        ExecutorService executor = Executors.newFixedThreadPool(NUM_SIMULATIONS);

        List<SimulationTask> tasks = new ArrayList<>();
        for (int i = 0; i < NUM_SIMULATIONS; i++) {
            tasks.add(new SimulationTask(new MultiChannelSystem()));
        }

        try {
            List<Future<MultiChannelSystemResult>> results = executor.invokeAll(tasks);

            double sumQueueLengths = 0;
            double sumRejectionProbabilities = 0;
            for (int i = 0; i < NUM_SIMULATIONS; i++) {
                MultiChannelSystemResult result = results.get(i).get();
                sumQueueLengths += result.getAvgQueueLength();
                sumRejectionProbabilities += result.getRejectionProbability();
                System.out.println("Simulation " + i + ": Average queue length: "+result.getAvgQueueLength()+", Rejection probability: "+result.getRejectionProbability() );
            }

            double overallMeanQueueLength = sumQueueLengths / NUM_SIMULATIONS;
            double overallRejectionProbability = sumRejectionProbabilities / NUM_SIMULATIONS;

            System.out.println("Overall mean queue length: " + overallMeanQueueLength);
            System.out.println("Overall rejection probability: " + overallRejectionProbability);

            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class SimulationTask implements Callable<MultiChannelSystemResult> {
        private MultiChannelSystem system;

        public SimulationTask(MultiChannelSystem system) {
            this.system = system;
        }

        @Override
        public MultiChannelSystemResult call() throws Exception {
            return system.simulate();
        }
    }
}


