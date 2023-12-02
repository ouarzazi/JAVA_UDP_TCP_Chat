//To better understand

public class ThreadTest extends Thread {
    private int counter;

    public void run() {
        while (true) {
            System.out.println(getName() + " : " + counter++);
            try {
                sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        //We create multiple instances of ThreadTest
        ThreadTest thread1 = new ThreadTest();
        ThreadTest thread2 = new ThreadTest();

        // We start the threads
        thread1.start();
        thread2.start();
    }
}
