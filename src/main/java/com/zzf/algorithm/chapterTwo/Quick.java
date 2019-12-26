package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;
import com.zzf.algorithm.base.StdRandom;

public class Quick {

    public static void sort(Comparable[] a) {
        // 消除对输入的依赖，打乱排序
        Example.show(a, "打乱前 a");
        StdRandom.shuffle(a);
        Example.show(a, "打乱后 a");
        sort(a, 0, a.length - 1, "all");
    }

    private static void sort(Comparable[] a, int lo, int hi, String direction) {
        Example.show(a, "【sort top】a");

        // 递归退出条件
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);

        System.out.println("【sort "+ direction +"】" + "j = " + j + ", lo = " + lo + ", hi = " + hi);
        Example.show(a, "【sort "+ direction +"】a");

        sort(a, lo, j - 1, "left");
        sort(a, j + 1, hi, "right");
    }

    /**
     * 找基准位置
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        Example.show(a, "partition a");

        // i=左指针=0，j=右指针=a.length+1
        int i = lo, j = hi + 1;

        // 切分元素，选择第一个元素为基准
        Comparable v = a[lo];
        System.out.println("【partition】基准 = a[lo] = v = " + "a["+lo+"] = " + v + "。 lo = " + lo + ", hi = " + hi + ", i = " + i + ", j = " + j);
        System.out.println();

        while (true) {

            System.out.println();
            System.out.println("【partition left】i+1 = " + (i+1));

            // 扫描左，检查扫描是否结束并交换元素
            // 因为从i是基准，所以从++i开始比较
            // false 退出，说明 a[++i] > v，则需要把a[++i]放到v的右边（左指针扫描到一个值，应该在v的右边）
            while (Example.less(a[++i], v)) {

                // 左指针==高，则退出，说明左指针已经从低到高扫描完了
                System.out.println("i:"+ i +" == hi:"+ hi +" = " + (i == hi));
                if (i == hi) {
                    break;
                }
            }

            System.out.println();
            System.out.println("【partition right】j-1 = " + (j-1));

            // 扫描右，检查扫描是否结束并交换元素
            // false 退出，说明 v > a[--j]，则需要把a[--j]放到v的左边（右指针扫描到一个值，应该在v的左边）
            while (Example.less(v, a[--j])) {

                // 右指针==低，则退出，说明右指针已经从高到底扫描完了
                // j == lo 是冗余的条件，因为切分元素就是a[lo]，它不可能比自己小
                System.out.println("j:"+ j +" == lo:"+ lo +" = " + (j == lo));
                if (j == lo) {
                    break;
                }
            }

            // i >= j == true，说明左右指针重叠了
            if (i >= j) {
                break;
            }

            // i需要交换，基于上面的：false 退出，说明 a[++i] > v
            // j需要交换，基于上面的：false 退出，说明 v > a[--j]
            Example.exch(a, i, j);

            Example.show(a, "while 内 a");
            System.out.println("【partition exch】i = " + i + ", j = " + j);
            System.out.println();
        }

        // a[lo] == v == 基准，基准与中间位置交换，因为基准应该在中间，所以交换
        // a[lo]是基准元素，j是基准坐标，所以lo和j进行交换
        Example.exch(a, lo, j);

        System.out.println();
        System.out.println("【partition】lo = " + lo + ", j = " + j);
        Example.show(a, "while 外 a");
        return j;
    }

    public static void main(String[] args) {
        // 9 8 7 6 5 4 3 2 1 0
        // 9 1 7 3 5 4 6 2 8 0
        // 5 2 3 4 1
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a, "a");
    }
}
