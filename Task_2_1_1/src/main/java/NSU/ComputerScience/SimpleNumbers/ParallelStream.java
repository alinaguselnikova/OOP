package NSU.ComputerScience.SimpleNumbers;

import java.util.Arrays;

public class ParallelStream {

    static boolean notPrime = false;

    public static boolean ParallelSearch(Integer[] arr) {

        long time_start = System.currentTimeMillis();

        return Arrays.stream(arr)
                .parallel()
                .noneMatch(PrimeNumbers::isNotPrime);
    }
}
