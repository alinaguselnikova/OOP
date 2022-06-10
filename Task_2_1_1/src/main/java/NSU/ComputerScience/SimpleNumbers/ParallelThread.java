package NSU.ComputerScience.SimpleNumbers;

import java.util.Arrays;

public class ParallelThread {
    static int THREADS = Runtime.getRuntime().availableProcessors();
    static boolean notPrime = false;
    public static Integer[] arr1;

    public static boolean ThreadSearch(Integer[] arr, int NumberOfThreads) throws Exception {
        System.out.print("Parallel Thread = ");
        long time_start = System.currentTimeMillis();
        if (NumberOfThreads > 0 && NumberOfThreads < THREADS) THREADS = NumberOfThreads;
        Thread[] thr = new Thread[THREADS];
        arr1 = Arrays.copyOf(arr, arr.length);

        for (int i = 0; i < THREADS; i++) {
            thr[i] = new Thread(new PrimeRun(i));
            thr[i].start();
        }

        //one thread have to wait for another thread to finish
        for (int i = 0; i < THREADS; i++)
            thr[i].join();

        System.out.println((System.currentTimeMillis() - time_start) + "ms");
        return notPrime;
    }
        public static Integer[] getArray() {
        return arr1;
    }
        public synchronized static void setIsNotPrime() {
        notPrime = true;
        }

}

class PrimeRun implements Runnable {

    final Integer[] array = ParallelThread.getArray();
    final int ID;

    public PrimeRun(int i) {
        ID = i;
    }

    public void run() {
        for(int i = 0; i < array.length; i++) {
            if (i % ParallelThread.THREADS == ID && PrimeNumbers.isNotPrime(array[i])) {
                System.out.println(i);
                ParallelThread.setIsNotPrime();
                break;
            }
        }
        }
    }

