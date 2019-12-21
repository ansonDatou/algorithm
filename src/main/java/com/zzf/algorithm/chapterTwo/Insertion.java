package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;

public class Insertion {

    /**
     *  插入排序：
     *  假设i=0为有序序列，i=1到a.length未排序序列
     *  循环未排序序列，将每个元素插入到适当位置
     *
     *  1、将index=1的元素与index=0的元素进行比较并替换
     *  2、将index=2的元素与index=1,0的元素进行比较并替换
     *  3、重复第二步
     *
     *  外：趟数+方向
     *  内：比较+方向
     *
     * @param a
     */
    public static void sort(Comparable[] a) {

        // 因为假设把第一个元素当做有序序列，所以从i=1开始
        int index = 0;
        for (int i = 1; i < a.length; i++) {
            // j > 0 : 防止数组越界
            for (int j = i; j > 0 && Example.less(a[j], a[j-1]); j--) {
                System.out.println("j:" + j + " value:" + a[j]);
                System.out.println("j - 1 :" + (j - 1) + " value:" + a[j-1]);

                // 谁和谁比较，就谁和谁替换，j 与 j-1
                Example.exch(a, j, j - 1);
                System.out.println("<<<<< 第 " + (i-1) + " 次排序后 >>>>>");
                Example.show(a);
                index++;
            }
            System.out.println("================================================== i = " + i);
        }
        System.out.println("【共循环"+ index +"次】");
    }

    public static void main(String[] args) {

        // 【循环27次】9 1 7 3 5 4 6 2 8 0
        // 【循环45次】9 8 7 6 5 4 3 2 1 0
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a);
    }

    /**
     * 9 1 7 3 5 4 6 2 8 0
     *
     * <<<<< 第 0 次排序后 >>>>>
     * 1 9 7 3 5 4 6 2 8 0
     *
     * <<<<< 第 1 次排序后 >>>>>
     * 1 7 9 3 5 4 6 2 8 0
     *
     * <<<<< 第 2 次排序后 >>>>>
     * 1 7 3 9 5 4 6 2 8 0
     *
     * <<<<< 第 2 次排序后 >>>>>
     * 1 3 7 9 5 4 6 2 8 0
     *
     * <<<<< 第 3 次排序后 >>>>>
     * 1 3 7 5 9 4 6 2 8 0
     *
     * <<<<< 第 3 次排序后 >>>>>
     * 1 3 5 7 9 4 6 2 8 0
     *
     * <<<<< 第 4 次排序后 >>>>>
     * 1 3 5 7 4 9 6 2 8 0
     *
     * <<<<< 第 4 次排序后 >>>>>
     * 1 3 5 4 7 9 6 2 8 0
     *
     * <<<<< 第 4 次排序后 >>>>>
     * 1 3 4 5 7 9 6 2 8 0
     *
     */
}
