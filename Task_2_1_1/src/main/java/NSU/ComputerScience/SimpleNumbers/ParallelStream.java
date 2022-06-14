package NSU.ComputerScience.SimpleNumbers;
import NSU.ComputerScience.SimpleNumbers.PrimeNumbers;

import java.util.*;

public class ParallelStream {

    static boolean notPrime = false;

    public static boolean ParallelSearch(Integer[] arr) {
//        System.out.print("Parallel Stream = ");
        long time_start = System.currentTimeMillis();
        //List<Integer> list = Arrays.stream(arr);

        //        System.out.println((System.currentTimeMillis() - time_start) + "ms");
        return Arrays.stream(arr)
                   .parallel()
                   .noneMatch(PrimeNumbers::isNotPrime);
    }
}
