package ru.nsu.fit;

//Создаём единственный публичный класс Heapsort
public class HeapSort {
    int[] heap;
    //Создаём конструктор Heapsort
    public HeapSort(int n) {
        this.heap = new int[n];
    }
    //Создаём публичный метод sort, не возвращающий ничего и работающий с массивом
     public void sort(int[] arr) {
        int n = arr.length;
        //Строим кучу из исходного массива(max heap)
        for (int i = n/2 - 1; i >= 0; i--)
            heap(arr, n, i);
        //Создаём min heap
        for (int i = n - 1; i >= 0; i--) {
            int c = arr[0];
            arr[0] = arr[i];
            arr[i] = c;
            heap(arr, i, 0);
        }
    }
    //Пирамидальная сортировка
    void heap(int[] arr, int n, int i) {
        int root = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[root])
            root = l;

        if (r < n && arr[r] > arr[root])
            root = r;

        if (root != i) {
            int swap = arr[i];
            arr[i] = arr[root];
            arr[root] = swap;

            heap(arr, n, root);
        }
    }
}




