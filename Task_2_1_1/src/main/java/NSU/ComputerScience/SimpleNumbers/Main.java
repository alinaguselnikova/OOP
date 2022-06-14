package NSU.ComputerScience.SimpleNumbers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/Numbers.txt", StandardCharsets.UTF_8));
        String[] strArr = reader.readLine().split(" ");
        Integer[] arr = new Integer[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
            //System.out.println(arr[i]);
        }



        var seq = benchmarkRunnable(()->{Sequential.SequentialSearch(arr);}, 1000);
        var parallelStream = benchmarkRunnable(()->{ParallelStream.ParallelSearch(arr);}, 1000);
        var threads_2 = benchmarkRunnable(()->{ParallelThread.ThreadSearch(arr, 2);}, 1000);
        System.out.printf("Sequential %f%nParallelStream %f%nThreads %f%n", seq, parallelStream, threads_2);
        //System.out.println(threads_2);
    }


    private static final int warmupIterations = 1000;
    static double benchmarkRunnable(Runnable r, int n){
        var totalRuns = n + warmupIterations;
        var startTimes = new long[totalRuns];
        var stopTimes = new long[totalRuns];
        for(int i = 0; i < totalRuns; i++){
            var start = System.currentTimeMillis();
            r.run();
            var stop = System.currentTimeMillis();
            startTimes[i] = start;
            stopTimes[i] = stop;
        }
        long totalTime = 0;
        for(int i = warmupIterations; i < totalRuns; i++){
            totalTime += stopTimes[i] - startTimes[i];
        }
        return ((double) totalTime) / ((double) n);
    }
}
