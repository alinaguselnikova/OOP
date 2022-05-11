/*У вас имеется массив целых чисел, необходимо определить есть ли в этом массиве
хотя бы одно не простое (делится без остатка только на себя и единицу). Необходимо
предоставить три решения задачи: последовательное, параллельное решением с
применением класса Thread с возможностью задания количества используемых потоков и
параллельным решением с применением ParallelStream.
После выполнения программной реализации, необходимо подготовить тестовый
пример с набором простых чисел, который продемонстрирует ускорение вычислений за счет
использования многоядерной архитектуры центрального процессора. Полученные времена
выполнения программ на созданном тестовом примере необходимо нанести на график (1
точка — последовательное исполнение, n точек — параллельное исполнение Thread для
разного количества используемых ядер, 1 точка — параллельное исполнение ParallelStream).
Преподавателю семинарских занятий объяснить выбор тестового набора данных и
полученный график, оценить долю времени последовательного исполнения программы.
     */


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
