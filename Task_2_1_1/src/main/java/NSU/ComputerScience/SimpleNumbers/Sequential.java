package NSU.ComputerScience.SimpleNumbers;

public class Sequential {


    public static boolean SequentialSearch(Integer[] arr) {
        long time_start = System.currentTimeMillis();
        boolean notPrime = false;

        for (int integer : arr) {
            if (PrimeNumbers.isNotPrime(integer)) {
                notPrime = true;
                break;
            }
        }
        return notPrime;
    }


}
