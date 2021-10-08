package nsu.fit;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import ru.nsu.fit.HeapSort;


import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    static public ArrayList<int[]> arrays() {
        var list = new ArrayList<int[]>();

        list.add(new int[]{6, 3, 0, 2, 7});
        list.add(new int[]{-198, -100, -6, -2, -1});
        list.add(new int[]{-100, 100, 256, -887, 0});
        list.add(new int[]{-256, 0, 3, 96, 500});
        list.add(new int[]{55, -2, 97, 55, -88});
        list.add(new int[]{88, 88, 88, 88, 88});
        list.add(new int[]{});


        return list;

    }

    @ParameterizedTest
    @MethodSource("arrays")
    void comp(int[] array) {
        int[] res = array.clone();
        sort(array);
        Arrays.sort(res, 0, res.length);
        assertArrayEquals(array, res);
    }

    @ParameterizedTest
    @NullSource
    void TestNull(int[] input) {
        assertThrows(IllegalArgumentException.class, () -> HeapSort.sort(input));
    }
}


