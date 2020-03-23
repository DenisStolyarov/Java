package company.system;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        var sem = new Semaphore(Shower.capacity);
        var shower = new Shower(sem);
        var rand = new Random();
        for (int i = 0; i < 8; i++) {
            var person = new Person(shower);
            person.id = i;
            person.gender = rand.nextInt(2) == 0 ? Gender.W : Gender.M;
            new Thread(person, "Name-" + i).start();
        }
    }

    public static class Shower {
        public static final int capacity = 5;
        public Gender gender;
        private int places = 0;
        Semaphore sem;

        Shower(Semaphore sem) {
            this.sem = sem;
        }

        public void enter(Person p) {

            try {

                while (places >= capacity || this.gender != p.gender) {

                    if (this.gender == null) {
                        this.gender = p.gender;
                    }

                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                sem.acquire();

                places++;
                System.out.println("Person " + p.id + "-" + p.gender.toString() + ":Enter in shower.");
                Thread.sleep(new Random(0).nextInt(1500) + 500);

                places--;
                if (places == 0) {
                    this.gender = null;
                }

                System.out.println("Person " + p.id + "-" + p.gender.toString() + ":Exit from shower.");
                sem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Person implements Runnable {
        public int id;
        public Gender gender;
        Shower shower;

        Person(Shower shower) {
            this.shower = shower;
        }

        public void run() {
            shower.enter(this);
        }
    }

    public enum Gender {
        M,
        W,
    }
}
