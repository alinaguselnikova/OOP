


package NSU.ComputerScience.SimpleNumbers;

import java.util.*;
import java.lang.*;



public class PrimeNumbers {

    public static boolean isPrime(int n){
        if (n == 2)
            return true;

        else if (n <= 1 || n % 2 == 0)
            return false;

        //check odd numbers

        for(int i = 3; i <= Math.sqrt(n); i += 2)
        {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static boolean isNotPrime(int n) {
        return !isPrime(n);
    }


}
