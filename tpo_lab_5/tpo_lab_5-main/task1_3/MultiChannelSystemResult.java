package lab_5.task1_3;

public class MultiChannelSystemResult {
    private double avgQueueLength;
    private double rejectionProbability;
    public MultiChannelSystemResult(double avgQueueLength, double rejectionProbability){
        this.avgQueueLength = avgQueueLength;
        this.rejectionProbability = rejectionProbability;
    }

    public double getAvgQueueLength(){
        return avgQueueLength;
    }

    public double getRejectionProbability(){
        return rejectionProbability;
    }

}
