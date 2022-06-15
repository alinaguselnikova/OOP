package NSU.ComputerScience.SimpleNumbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class PrimeTest {

    @Test
    public void SequentialTest() throws IOException {
        Sequential sequential = new Sequential();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/Numbers.txt", StandardCharsets.UTF_8));
        String[] strArr = reader.readLine().split(" ");
        Integer[] arr = new Integer[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        Assertions.assertFalse(Sequential.SequentialSearch(arr));
    }

    @Test
    public void ParallelStreamTest() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/Numbers.txt", StandardCharsets.UTF_8));
        String[] strArr = reader.readLine().split(" ");
        Integer[] arr = new Integer[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        Assertions.assertFalse(ParallelStream.ParallelSearch(arr));
    }

    @Test
    public void ParallelThreadTest() throws Exception {
        Integer[] TestArr = {100003, 100019, 100043, 100049, 100057, 100069, 100103, 100109, 100129, 100151};
        }

    @Test
    public void  File8Thread() throws Exception {
            ParallelThread PT = new ParallelThread();
            BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/Numbers.txt", StandardCharsets.UTF_8));
            String[] strArr = reader.readLine().split(" ");
            Integer[] arr = new Integer[strArr.length];

            for (int i = 0; i < strArr.length; i++) {
                arr[i] = Integer.parseInt(strArr[i]);
            }
            Assertions.assertFalse(PT.ThreadSearch(arr,1 ));
        }


    }

