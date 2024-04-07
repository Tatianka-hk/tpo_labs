package lab_1;

public class Counter {
        private int count = 0;

        public void increment() {
            count++;
//            System.out.println("Counter +" + count);
        }

        public void decrement() {
            count--;
//            System.out.println("Counter -" + count);
        }
       // сихронізація методів
        public synchronized void increment_synchronized_method() {
            count++;
        }

        public synchronized void decrement_synchronized_method() {
            count--;
        }
    // сихронізація блоків
        public void increment_synchronized_block() {
            synchronized (this) {
                count++;
            }
        }

        public void decrement_synchronized_block() {
            synchronized (this) {
                count--;
            }
        }

        public int getCount() {
            return count;
        }
}
