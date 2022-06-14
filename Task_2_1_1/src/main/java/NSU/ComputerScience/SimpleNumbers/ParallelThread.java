package NSU.ComputerScience.SimpleNumbers;

import java.util.Arrays;


public class ParallelThread {
    static int THREADS = Runtime.getRuntime().availableProcessors();
    static boolean notPrime = false;
    public static Integer[] arr1;

    public static boolean ThreadSearch(Integer[] arr, int NumberOfThreads) {
//        System.out.print("Parallel Thread = ");
        long time_start = System.currentTimeMillis();
        if (NumberOfThreads > 0 && NumberOfThreads < THREADS) THREADS = NumberOfThreads;
        Thread[] thr = new Thread[THREADS];
        arr1 = arr;

        for (int i = 0; i < THREADS; i++) {
            thr[i] = new Thread(new PrimeRun(i));
            thr[i].start();
        }

        //one thread have to wait for another thread to finish
        try{
            for (int i = 0; i < THREADS; i++){
                thr[i].join();

            }
        }catch (InterruptedException ignored){}

//        System.out.println((System.currentTimeMillis() - time_start) + "ms");
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
        for(int i = ID; i < array.length; i+=ParallelThread.THREADS) {
            if (PrimeNumbers.isNotPrime(array[i])) {
                System.out.println(i);
                ParallelThread.setIsNotPrime();
                break;
            }
        }
        }
    }

