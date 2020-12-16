import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static AtomicInteger x = new AtomicInteger(0);
    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            synchronized (x) {
                int value = x.get();
                while (value < 999999) {
                    value = x.addAndGet(1);
                    if ((value % 10) + (value / 10) % 10 + (value / 100) % 10 == (value / 1000)
                            % 10 + (value / 10000) % 10 + (value / 100000) % 10) {
                        System.out.println(value);
                        count++;
                    }
                }
            }
        }
    }
}