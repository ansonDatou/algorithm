package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;

public class Merge {

    private static Comparable[] aux;

    private static int sortIndex = 0;

    private static int mergeIndex = 0;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        mergeIndex++;
        System.out.println("【merge】= lo : " + lo + ", mid : " + mid + ", hi : " + hi + ", mergeIndex : " + mergeIndex);

        // i = 低，j = 中，k = 低
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        Example.show(aux, "aux");

        for (int k = lo; k <= hi; k++) {
            System.out.println("k = " + k + ", i = " + i + ", j = " + j);
            if (i > mid) {
                // i > mid : 低 大于 中
                System.out.println("【i > mid】 : " + i +" > "+ mid);
                a[k] = aux[j++];
                System.out.println("a["+k+"]=" + a[k]);
            } else if (j > hi) {
                // j > hi : 中 大于 高
                System.out.println("【j > hi】 : " + j +" > "+ hi);
                a[k] = aux[i++];
                System.out.println("a["+k+"]=" + a[k]);
            } else if (Example.less(aux[j], aux[i])) {
                // aux[j] < aux[i] : 中值 小于 低值
                System.out.println("【less】aux["+j+"] = " + aux[j] + ", aux["+i+"] = " + aux[i]);
                a[k] = aux[j++];
                System.out.println("a["+k+"] = " + a[k]);
            } else {
                a[k] = aux[i++];
                System.out.println("【else】" + "a["+k+"]=" + a[k]);
            }
        }

        Example.show(a, "a");
        System.out.println("merge==================================================merge");
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        sortIndex++;

        // 递归退出条件
        if (hi <= lo) {
            return;
        }

        // 取mid值
        int mid = lo + (hi - lo) / 2;
        System.out.println("【sort】= lo : " + lo+ ", mid : " + mid + ", hi : " + hi + ", sortIndex : " + sortIndex);

        // 分割左侧
        sort(a, lo, mid);

        // 分割右侧
        sort(a, mid + 1, hi);

        // 归并
        merge(a, lo, mid, hi);
        System.out.println("sort==================================================sort");
    }

    public static void main(String[] args) {
        // 【循环次】9 8 7 6 5 4 3 2 1 0
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a, "a");
    }
}
