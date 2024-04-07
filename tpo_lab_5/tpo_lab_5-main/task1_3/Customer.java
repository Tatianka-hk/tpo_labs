package lab_5.task1_3;

public class Customer {
    private int id;
    private int serviceTime;

    public Customer(int id, int serviceTime) {
        this.id = id;
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public int getServiceTime() {
        return serviceTime;
    }
}
