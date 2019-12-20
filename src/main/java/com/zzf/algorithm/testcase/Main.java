package com.zzf.algorithm.testcase;

import com.zzf.algorithm.base.In;
import com.zzf.algorithm.chapterTwo.Example;

public class Main {

    public static void main(String[] args) {
        // 9 8 7 6 5 4 3 2 1 0
        String[] a = In.readStrings();
        insert(a);
        Example.show(a);
    }

    /**
     * 选择排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (Example.less(a[j], a[min])) {
                    min = j;
                }
                index++;
            }
            System.out.println("min = "+ min);
            Example.exch(a, i, min);
            System.out.println("Example.show(a); = " );
            Example.show(a);
        }
        System.out.println(index);
    }

    /**
     * 插入排序
     * 第一个元素看做有序序列，第二个到最后一个看做无序
     * 扫描未排序，将每个元素插入到适应位置
     * @param a
     */
    public static void insert(Comparable[] a) {

        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && Example.less(a[j], a[j-1]); j--) {
                Example.exch(a,j, j-1);
            }
        }
    }
}
