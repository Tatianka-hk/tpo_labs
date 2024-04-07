package lab_1;
// 5 завдання 1 частина
public class Thread_5 extends Thread {
    private boolean flag = false;
    public  Control control;
    private Character ch = ' ';
    private  int amount = 100;
    public Thread_5(Character ch, boolean flag) {
        this.ch = ch;
        this.flag = flag;
    }
    public Thread_5(Character ch, boolean flag, Control control) {
        this.ch = ch;
        this.flag = flag;
        this.control = control;
    }

    @Override
    public void run() {
        if (flag) {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (control) {
                        while (control.flag && ch  == '|') {
                            control.wait();
                        }

                        while (!control.flag && ch  == '-') {
                            control.wait();
                        }
                        System.out.print(this.ch);
                        control.flag = !control.flag;
                        control.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            for (int i = 0; i < amount; i++) {
            System.out.print(this.ch);
           }
        }
    }
}
