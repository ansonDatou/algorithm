package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;

public class Bubble {

    public static void main(String[] args) {

        // s o r t e x a m p l e
        // 9 1 7 3 5 4 6 2 8 0
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a, "a");
    }

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            boolean flag = false;
            for (int j = 0; j < a.length - i; j++) {
                if (Example.less(a[j+1], a[j])) {
                    Example.exch(a,j+1, j);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
