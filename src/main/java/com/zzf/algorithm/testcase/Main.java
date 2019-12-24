package com.zzf.algorithm.testcase;

import com.zzf.algorithm.base.In;
import com.zzf.algorithm.chapterTwo.Example;

public class Main {

    public static Comparable[] aux;

    public static void main(String[] args) {
        // 9 8 7 6 5 4 3 2 1 0
        String[] a = In.readStrings();
        sort(a);
        Example.show(a, "a");
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
}
