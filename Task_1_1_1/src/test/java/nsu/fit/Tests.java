package nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.nsu.fit.HeapSort;

public class Tests {
    @Test
    void TestPositive() {
        int n = 5;
        HeapSort a = new HeapSort(n);
        int[] input = {6, 3, 0, 2, 7};
        int[] sorted = {0, 2, 3, 6, 7};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
    @Test
    void TestNegative(){
        int n = 5;
        HeapSort a = new HeapSort(n);
        int[] input = {-2, -100, -6, -198, -1};
        int[] sorted = {-198,-100, -6, -2, -1};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
    @Test
    void TestMixed(){
        int n = 5;
        HeapSort a = new HeapSort(n);
        int[] input = {-100, 100, 256, -887, 0} ;
        int[] sorted = {-887, -100, 0, 100, 256};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
    @Test
    void TestSorted(){
        int n = 5;
        HeapSort a = new HeapSort(n);
        int[] input = {-256, 0, 3, 96, 500} ;
        int[] sorted = {-256, 0, 3, 96, 500};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
    @Test
    void TestSame2(){
        int n = 5;
        HeapSort a = new HeapSort(n);
        int[] input = {55, -2, 97, 55, -88} ;
        int[] sorted = {-88, -2, 55, 55, 97};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
    @Test
    void TestSameAll(){
        int n = 5;
        HeapSort a = new HeapSort(n);
        int[] input = {88, 88, 88, 88, 88} ;
        int[] sorted = {88, 88, 88, 88, 88};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
    @Test
    void TestLength0(){
        int n = 0;
        HeapSort a = new HeapSort(n);
        int[] input = {} ;
        int[] sorted = {};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
    @Test
    void TestLength1(){
        int n = 1;
        HeapSort a = new HeapSort(n);
        int[] input = {45} ;
        int[] sorted = {45};
        a.sort(input);
        Assertions.assertArrayEquals(input, sorted);
    }
}

