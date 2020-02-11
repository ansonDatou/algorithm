package com.zzf.algorithm.chapterTwo;


import com.zzf.algorithm.base.In;

public class Selection {

    /**
     * 选择排序: 每次将未排序数组中最小值，放入已排序队尾
     * 0、第一次循环，假设i=0是最小的
     * 1、找未排序数组中最小值，放入index=0位置
     * 2、找未排序数组中最小值，放入index=1位置
     * 3、重复
     *
     * 1、假设index=0是最小的，i = min = 0
     * 2、与index=1往后进行比较
     *
     * 看初始值、看方向(++/--)、看假设值
     *
     * 外循环：行
     * 内循环：列
     * 谁与谁比较
     *
     * 假设index=0是min
     * 外：从头到尾
     * 内：比较当前和min
     * 内循环之后进行替换
     *
     * 外：趟数+方向
     * 内：比较+方向
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        int index = 0;
        for (int i = 0; i < a.length; i++) {

            // 每次外循环，第一次假设i是最小的
            int min = i;

            // for循环未排序的数组；
            // 从（j = i + 1）开始；因为i假设是最小的，所以从（j = i + 1）开始；所以j与min进行比较
            for (int j = i + 1; j < a.length; j++) {
                System.out.println("i:" + i + ", value:" + a[i]);
                System.out.println("j:" + j + ", value:" + a[j]);
                System.out.println("min:" + min + ", value:" + a[min]);
                System.out.println("------------------------------");

                // 在未排序数组中找最小的，未排序元素与最小值比较
                if (Example.less(a[j], a[min])) {

                    // 真正最小的，更新min值
                    min = j;
                }

                // index 没有实际意义，只是作为标记，输出循环几次
                index++;
            }

            // 未排序数组中最小的 与 当前i，进行替换
            // 真正将i提换成了最小值
            // 谁和谁比较，就谁和谁替换，i 与 min
            Example.exch(a, i, min);
            System.out.println("<<<<< 第 " + i + " 次排序后 >>>>>");
            Example.show(a, "a");
            System.out.println("==================================================");
        }
        System.out.println("【共循环"+ index +"次】");
    }

    public static void main(String[] args) {

        // s o r t e x a m p l e
        // 【循环45次】9 1 7 3 5 4 6 2 8 0
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a, "a");
    }

    /**
     * 9 1 7 3 5 4 6 2 8 0
     *
     * <<<<< 第 0 次排序后 >>>>>
     * 0 1 7 3 5 4 6 2 8 9
     * <<<<< 第 1 次排序后 >>>>>
     * 0 1 7 3 5 4 6 2 8 9
     * <<<<< 第 2 次排序后 >>>>>
     * 0 1 2 3 5 4 6 7 8 9
     * <<<<< 第 3 次排序后 >>>>>
     * 0 1 2 3 5 4 6 7 8 9
     * <<<<< 第 4 次排序后 >>>>>
     * 0 1 2 3 4 5 6 7 8 9
     * <<<<< 第 5 次排序后 >>>>>
     * 0 1 2 3 4 5 6 7 8 9
     * <<<<< 第 6 次排序后 >>>>>
     * 0 1 2 3 4 5 6 7 8 9
     * <<<<< 第 7 次排序后 >>>>>
     * 0 1 2 3 4 5 6 7 8 9
     * <<<<< 第 8 次排序后 >>>>>
     * 0 1 2 3 4 5 6 7 8 9
     * <<<<< 第 9 次排序后 >>>>>
     * 0 1 2 3 4 5 6 7 8 9
     */

}
