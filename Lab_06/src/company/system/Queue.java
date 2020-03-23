package company.system;
import java.util.Random;
import java.util.concurrent.Semaphore;

    public class Queue {
        private static final boolean[] PLACES = new boolean[2];
        private static final Semaphore SEMAPHORE = new Semaphore(2, true);

        public static void main(String[] args) throws InterruptedException {
            for (int i = 1; i <= 7; i++) {
                new Thread(new Client(i)).start();
                Thread.sleep(200);
            }
        }

        public static class Client implements Runnable {
            private int number;

            public Client(int number) {
                this.number = number;
            }

            @Override
            public void run() {
                System.out.printf("Клиент №%d стал в очередь.\n", number);
                try {
                    SEMAPHORE.acquire();

                    int parkingNumber = -1;

                    //Ищем свободное место и паркуемся
                    synchronized (PLACES){
                        for (int i = 0; i < 5; i++)
                            if (!PLACES[i]) {
                                PLACES[i] = true;
                                parkingNumber = i;
                                System.out.printf("Клиент №%d занял кассу %d.\n", number, i);
                                break;
                            }
                    }

                    Thread.sleep(new Random().nextInt(3000));

                    synchronized (PLACES) {
                        PLACES[parkingNumber] = false;
                    }

                    System.out.printf("Клиент №%d покинул очередь.\n", number);
                    SEMAPHORE.release();
                } catch (InterruptedException e) {
                }
            }
        }
    }
