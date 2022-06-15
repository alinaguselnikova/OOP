package NSU.ComputerScience.SimpleNumbers;

import java.util.concurrent.atomic.AtomicBoolean;


public class ParallelThread {
    public static Integer[] arr1;
    static int THREADS = Runtime.getRuntime().availableProcessors();
    static AtomicBoolean notPrime = new AtomicBoolean(false);

    public static boolean ThreadSearch(Integer[] arr, int NumberOfThreads) {
        notPrime.set(false);
        if (NumberOfThreads > 0 && NumberOfThreads < THREADS) THREADS = NumberOfThreads;
        Thread[] thr = new Thread[THREADS];
        arr1 = arr;

        for (int i = 0; i < THREADS; i++) {
            thr[i] = new Thread(new PrimeRun(i));
            thr[i].start();
        }

        try {
            for (int i = 0; i < THREADS; i++) {
                thr[i].join();

            }
        } catch (InterruptedException ignored) {
        }

        return notPrime.get();
    }

    public static Integer[] getArray() {
        return arr1;
    }

    public synchronized static void setIsNotPrime() {
        notPrime.set(true);
    }

}

class PrimeRun implements Runnable {

    final Integer[] array = ParallelThread.getArray();
    final int ID;

    public PrimeRun(int i) {
        ID = i;
    }

    public void run() {
        for (int i = ID; i < array.length; i += ParallelThread.THREADS) {
            if (PrimeNumbers.isNotPrime(array[i])) {
                System.out.println(i);
                ParallelThread.setIsNotPrime();
                break;
            }
            if (ParallelThread.notPrime.get()) {
                break;
            }
        }
    }
}

