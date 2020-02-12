package com.zzf.algorithm.testcase;

import com.zzf.algorithm.base.In;
import com.zzf.algorithm.base.StdRandom;
import com.zzf.algorithm.chapterTwo.Example;

/**
 * 测验
 */
public class Main {

    public static Comparable[] aux;

    public static void main(String[] args) {
        // 9 8 7 6 5 4 3 2 1 0
        String[] a = In.readStrings();
        shell(a);
        Example.show(a, "a");
    }

    private static void shell(Comparable[] a) {
        int n = a.length;
        int h = 1;

        while (h < n/3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && Example.less(a[j], a[j-h]); j -= h) {
                    Example.exch(a, j, j-h);
                }
            }

            h = h/3;
        }
    }

    /**
     * 插入排序
     * @param a
     */
    private static void insertion(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && Example.less(a[j], a[j-1]); j--) {
                Example.exch(a, j, j-1);
            }
        }
    }

    /**
     * 选择排序
     * @param a
     */
    private static void selectionSort(Comparable[] a) {
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if ( Example.less(a[j], a[min])) {
                    min = j;
                }
            }
            Example.exch(a, i, min);
        }
    }


    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
    }

}
