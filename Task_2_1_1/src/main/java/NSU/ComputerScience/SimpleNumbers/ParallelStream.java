package NSU.ComputerScience.SimpleNumbers;

import java.util.*;

public class ParallelStream {

    static boolean notPrime = false;

    public static boolean ParallelSearch(Integer[] arr) {
        System.out.print("Parallel Stream = ");
        long time_start = System.currentTimeMillis();
        //List<Integer> list = Arrays.stream(arr);

        Optional <Integer> OpList = Arrays.stream(arr)
                .parallel()
                .filter(PrimeNumbers::isNotPrime)
                .findFirst();

        if(OpList.isPresent()){
            notPrime = true;
        }
        System.out.println((System.currentTimeMillis() - time_start) + "ms");
        return notPrime;
    }
}
