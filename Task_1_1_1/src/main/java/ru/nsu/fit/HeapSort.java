    package ru.nsu.fit;


    public  class HeapSort {

        /**  Сортировка массива
         *
         * @param arr входной массив
         */
         public static void sort(int[] arr) {
             if (arr == null){
                 throw new IllegalArgumentException("Нулевой указатель");
             }
            int n = arr.length;
            for (int i = n/2 - 1; i >= 0; i--)
                heap(arr, n, i);
            for (int i = n - 1; i >= 0; i--) {
                int c = arr[0];
                arr[0] = arr[i];
                arr[i] = c;
                heap(arr, i, 0);
            }
        }

        /** Пирамидальная сортировка
         * @param arr входной массив
         * @param n размер массива
         * @param i номер элемента, от которого происходит сортировка
         */
        private static void heap(int[] arr, int n, int i) {
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




