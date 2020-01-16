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
        heap(a);
        Example.show(a, "a");
    }

    private static void sortOfQuick(Comparable[] a) {
        StdRandom.shuffle(a);
        sortOfQuick(a, 0, a.length-1);
    }

    private static void sortOfQuick(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);
        sortOfQuick(a, lo, j-1);
        sortOfQuick(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // 左右指针
        int i = lo, j = hi+1;

        // 基准
        Comparable v = a[lo];

        while (true) {
            while (Example.less(a[++i], v)) {
                if (i==hi) {
                    break;
                }
            }

            while (Example.less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j){
                break;
            }

            Example.exch(a, i, j);
        }

        Example.exch(a, lo, j);
        return j;
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (Example.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    public static void select(Comparable[] a) {
        int index = 0;
        for (int i = 0; i<a.length;i++) {
            int min = i;
            for(int j = i+1;j<a.length;j++){
                if (Example.less(a[j], a[min])) {
                    min = j;
                }
                index++;
            }
            Example.exch(a, i, min);
        }
        System.out.println(index);
    }

    public static void insert(Comparable[] a){
        int index = 0;
        for (int i = 1;i<a.length;i++) {
            for(int j = i; j>0 && Example.less(a[j],a[j-1]);j--){
                Example.exch(a,j ,j-1);
                index++;
            }
        }
        System.out.println(index);
    }

    public static void shell(Comparable[] a){
        int index = 0;
        int n = a.length;
        int h = 1;

        while (h < n/3){
            h = h*3+1;
        }
        System.out.println("h = "+h);

        while (h>=1){
            for(int i = h;i<n;i++) {
                for(int j = i; j>=h&&Example.less(a[j], a[j-h]); j -=h) {
                    Example.exch(a, j,j-h);
                    index++;
                }
            }
            h = h/3;
        }
        System.out.println("index = "+index);
    }

    /**
     * 先构建树
     * 在二叉堆比较替换
     * @param a
     */
    public static void heap(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }

        while (n > 1) {

            exch(a, 1, n--);
            sink(a, 1, n);

        }
    }



    private static void sink(Comparable[] a, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j<n && less(a, j, j+1)) {
                j++;
            }

            if (!less(a, k, j)) {
                break;
            }

            exch(a, k, j);

            k = j;
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
