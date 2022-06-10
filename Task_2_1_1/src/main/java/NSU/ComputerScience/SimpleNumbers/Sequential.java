package NSU.ComputerScience.SimpleNumbers;

public class Sequential {


    public static boolean SequentialSearch(Integer [] arr) {
        System.out.print("Sequential = ");
        long time_start = System.currentTimeMillis();
        boolean notPrime = false;

        for (int integer : arr) {
            //System.out.println(integer);
            if (PrimeNumbers.isNotPrime(integer)) {
                notPrime = true;
                break;
            }
        }
        System.out.println((System.currentTimeMillis() - time_start)  + "ms");
        return notPrime;
    }


}
