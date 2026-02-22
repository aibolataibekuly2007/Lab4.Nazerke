import java.util.Random;

public class MultiThreadLoggerTest {
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("\n=== Multi-threaded Logger Test ===");

        Logger logger = Logger.getInstance("config/logger.properties");

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    int level = random.nextInt(3);
                    String message = String.format("Thread %d message %d", threadId, j);

                    switch (level) {
                        case 0:
                            logger.info(message);
                            break;
                        case 1:
                            logger.warning(message);
                            break;
                        case 2:
                            logger.error(message);
                            break;
                    }

                    try {
                        Thread.sleep(random.nextInt(50));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "Thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n=== All threads finished ===");
    }
}