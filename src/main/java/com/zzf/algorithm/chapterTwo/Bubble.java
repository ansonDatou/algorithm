package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;

/**
 * 冒泡排序
 */
public class Bubble {

    public static void main(String[] args) {

        // s o r t e x a m p l e
        // 9 1 7 3 5 4 6 2 8 0
        String[] a = In.readStrings();
        sort1(a);
        assert Example.isSorted(a);
        Example.show(a, "a");
    }

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            boolean flag = true;
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

    public static void sort1(Comparable[] a) {
        for (int x = 0; x < a.length - 1; x++) {
            for (int y = 0; y < a.length - 1 - x; y++) {
                if (Example.less(a[y+1], a[y])) {
                    Example.exch(a, y+1, y);
                }
            }
        }
    }
}
