package task_3;
import java.util.Arrays;
public class Main {
    public static void main(String[] args){
        Journal journal = new Journal();
        Runnable r =
                new Runnable() {
                    @Override
                    public void run() {
                        (new Thread(
                                new Professor("Lecturer 1", Arrays.asList("ІП-01", "ІП-02", "ІП-03"),  journal)))
                                .start();
                        (new Thread(
                                new Professor("Assistant 1", Arrays.asList("ІП-01", "ІП-02", "ІП-03"),  journal)))
                                .start();
                        (new Thread(
                                new Professor("Assistant 2", Arrays.asList("ІП-01", "ІП-02", "ІП-03"),  journal)))
                                .start();
                        (new Thread(
                                new Professor("Assistant 3", Arrays.asList("ІП-01", "ІП-02", "ІП-03"),  journal)))
                                .start();
                    }
                };
        try {
            Thread t = new Thread(r);
            t.start();
            t.join();
        }catch (InterruptedException ex) {

        }
        journal.show();
    }

}
