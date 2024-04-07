package task_1;
import java.util.concurrent.locks.ReentrantLock;
class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public Bank(int n, int initialBalance) {
        accounts = new int[n];
        int i;
        for (i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }
        ntransacts = 0;
    }


    // код в лістингу до переписання

//    public void transfer(int from, int to, int amount) {
//        accounts[from] -= amount;
//        accounts[to] += amount;
//        ntransacts++;
//        if (ntransacts % NTEST == 0) {
//            test();
//        }
//    }


    //перший метод управління

//public synchronized void transfer(int from, int to, int amount) {
//    accounts[from] -= amount;
//    accounts[to] += amount;
//    ntransacts++;
//    if (ntransacts % NTEST == 0) {
//        test();
//    }
//}

//другий метод управління
//    public void transfer(int from, int to, int amount) {
//        lock.lock();
//        try {
//            accounts[from] -= amount;
//            accounts[to] += amount;
//            ntransacts++;
//            if (ntransacts % NTEST == 0) {
//                test();
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//третій метод управління
    public void transfer(int from, int to, int amount) throws InterruptedException {
        synchronized (lock) {
            while (accounts[from] < amount) {
                lock.wait();
            }
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            if (ntransacts % NTEST == 0) {
                test();
            }
            lock.notifyAll();
        }
    }
    public int size() {
        return accounts.length;
    }

    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            sum += accounts[i];
        }
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }
}