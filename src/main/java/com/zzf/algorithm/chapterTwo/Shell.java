package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;

public class Shell {

    /**
     * 希尔排序
     * 1、先取增量h1，分别对h1个组进行排序
     * 2、在取增量h2，分别对h2个组进行排序
     * 3、重复
     *
     * 外：趟数+方向
     * 内：比较+方向
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        int index = 0;
        int n = a.length;
        int h = 1;
        System.out.println("n = a.length = " + a.length);

        // 增量序列h，如果这个常数是5会变快
        while (h < n/3) {
            h = 3 * h + 1;
        }

        System.out.println("init h = " + h);

        while (h >= 1) {
            // 进行h趟排序, i到n个子序列
            for (int i = h; i < n; i++) {
                System.out.println("n:"+ n +" - i:"+ i +" = " + (n-i) + "个子序列");
                // 用于debug
                if (i == 9) {
                    System.out.println("i = " + i);
                }
                // j >= h 为true的话，说明有下一个序列
                for (int j = i; j >= h && Example.less(a[j], a[j-h]); j -= h) {
                    System.out.println("h = " + h);
                    System.out.println("a[j], a["+ j +"] = " + a[j]);
                    System.out.println("a[j-h], a["+ (j-h) +"] = " + a[j-h]);
                    System.out.println("--------------------");

                    Example.exch(a, j, j-h);
                    System.out.println("<<<<< 第"+ index +"排序之后 >>>>>");
                    Example.show(a, "a");
                    System.out.println("==============================");
                    index++;
                }
            }
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
            h = h/3;
            System.out.println("h = h/3 = " + h);
        }
        System.out.println("【共循环"+ index +"次】");
    }

    public static void main(String[] args) {
        int x = 5;
        int y = 3;
        System.out.println("x -= y : " + (x-=y));

        // 9 1 7 3 5 4 6 2 8 0
        // 【循环13次】9 8 7 6 5 4 3 2 1 0
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a, "a");
    }
}
