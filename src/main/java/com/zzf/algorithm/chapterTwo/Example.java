package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;
import com.zzf.algorithm.base.StdOut;

public class Example {

    /**
     * 插入排序
     * 1、将index=1的元素与index=0的元素进行比较并替换
     * 2、将index=2的元素与index=1,0的元素进行比较并替换
     * 3、重复第二步
     * @param a
     */
    public static void sortOfInsert(Comparable[] a) {
        // 每次都是外循环的i是待插入的元素
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                System.out.println("j:" + j + " value:" + a[j]);
                System.out.println("j - 1 :" + (j - 1) + " value:" + a[j-1]);
                System.out.println("-------------交换之后-----------------");
                exch(a, j, j - 1);
                show(a);
            }
            System.out.println("================================================== i = " + i);
        }
    }

    /**
     * 选择排序
     * 1、找出数组中最小的值，与数组中第一个位置 交换
     * 2、找出未排序数组中最小的值，与数组中第二个位置 交换
     * 3、重复第二步
     * @param a
     */
    public static void sortOfSelect(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                System.out.println("外循环 = i:" + i + " value:" + a[i]);
                System.out.println("内循环 = j:" + j + " value:" + a[j]);
                System.out.println("内循环 = min:" + min + " value:" + a[min]);
                System.out.println("------------------------------");

                // 外循环i与内循环i+1，进行比较
                if (less(a[j], a[min])) {
                    min = j;
                }
            }

            // 内循环执行完一次，进行值替换
            exch(a, i, min);
            System.out.println("==================================================");
        }
    }

    /**
     * 选择排序
     * @param array
     */
    public static int[] sortOfSelect1(int[] array) {

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }

        return array;
    }

    /**
     * 比较
     * v == w, 0
     * v < w, -
     * v > w, +
     * @param v
     * @param w
     * @return
     */
    public static boolean less(Comparable v, Comparable w) {
        System.out.println("less = " + v + ".compareTo(" + w + ")" + " < 0 = " + (v.compareTo(w) < 0));
        return v.compareTo(w) < 0;
    }

    /**
     * 交换
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * 测试数组元素是否有序
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        // s o r t e x a m p l e

        // 选择排序v1 = 书本
//        String[] a = In.readStrings();
//        sortOfSelect(a);
//        assert isSorted(a);
//        show(a);


        // 选择排序v2 = 网络
//        int[] arr = new int[]{3, 7, 2, 9, 5};
//        arr = sortOfSelect1(arr);
//        for (int i : arr) {
//            System.out.print(i);
//        }

        // 插入排序v1 = 书本
        String[] a = In.readStrings();
        sortOfInsert(a);
        assert isSorted(a);
        show(a);

    }
}
