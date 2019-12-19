package com.zzf.algorithm.chapterTwo;


import com.zzf.algorithm.base.In;

public class Selection {

    /**
     * 选择排序: 每次去数组中最小的值与数组中对应的值，如果小则交换位置
     * 1、找出数组中最小的值，与数组中第一个位置 交换
     * 2、找出未排序数组中最小的值，与数组中第二个位置 交换
     * 3、重复第二步
     *
     * 1、假设index=0是最小的，i = min = 0
     * 2、与index=1往后进行比较
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
                index++;
            }

            // 未排序数组中最小的 与 当前i，进行替换
            // 真正将i提换成了最小值
            // 谁和谁比较，就谁和谁替换，i 与 min
            Example.exch(a, i, min);
            System.out.println("<<<<< 第 " + i + " 次排序后 >>>>>");
            Example.show(a);
            System.out.println("==================================================");
        }
        System.out.println("【共循环"+ index +"次】");
    }

    public static void main(String[] args) {

        // s o r t e x a m p l e
        // 9 1 7 3 5 4 6 2 8 0
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a);
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
