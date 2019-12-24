package com.zzf.algorithm.chapterTwo;

import com.zzf.algorithm.base.In;

/**
 * 自底向上归并
 */
public class MergeBU {

    private static Comparable[] aux;

    private static int mergeIndex = 0;

    /**
     * 归并
     * 将元素复制到aux临时数组中
     * 比较：左边是否小于mid，右边是否小于hi，比较右值是否小于左值，else否则正常左值小于右值
     * @param a
     * @param lo
     * @param mid
     * @param hi
     * @param direction
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi, String direction) {
        mergeIndex++;
        System.out.println("【merge "+ direction +"】= lo : " + lo + ", mid : " + mid + ", hi : " + hi + ", mergeIndex : " + mergeIndex);

        // i = 低，j = min+1，k = 索引
        // 将元素复制到aux中
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        Example.show(aux, "aux");
        System.out.println();

        for (int k = lo; k <= hi; k++) {
            System.out.println("k = " + k + ", i = " + i + ", j = " + j);
            if (i > mid) {
                // i > mid : i < mid 说明左边比较完了
                // 左半边用尽（取右半边元素）
                System.out.println("(i > mid) : " + i +" > "+ mid + ", j+1 = "+ (j+1));
                a[k] = aux[j++];
                System.out.println("a[k] : a["+k+"] = " + a[k]);
                System.out.println();
            } else if (j > hi) {
                // j > hi : j=min+1 > hi 说明右边比较完了
                // 右半边用尽（取左半边元素）
                System.out.println("(j > hi) : " + j +" > "+ hi + ", i+1 = " + (i+1));
                a[k] = aux[i++];
                System.out.println("a[k] : a["+k+"] = " + a[k]);
                System.out.println();
            } else if (Example.less(aux[j], aux[i])) {
                // aux[j] < aux[i] : 右边值 < 左边值 则替换
                // 右半边的当前元素 <, 左半边的当前元素（取右半边元素）
                System.out.println("(Example.less) aux["+j+"] = " + aux[j] + ", aux["+i+"] = " + aux[i]);
                a[k] = aux[j++];
                System.out.println("a[k] : a["+k+"] = " + a[k]);
                System.out.println();
            } else {
                // 右边值 > 左边值 则不替换
                // 右半边的当前元素 >= 左半边的当前元素（取左半边元素）
                System.out.println("(else) aux[i] : aux["+i+"] = " + aux[i]);
                a[k] = aux[i++];
                System.out.println("(else) " + "a[k] : a["+k+"] = " + a[k]);
                System.out.println();
            }
        }

        System.out.println();
        Example.show(a, "a");
        System.out.println("merge==================================================merge");
        System.out.println();
        System.out.println();
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1), "MergeBU");
            }
        }
    }

    public static void main(String[] args) {
        // 9 8 7 6 5 4 3 2 1 0
        // 9 1 7 3 5 4 6 2 8 0
        String[] a = In.readStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a, "a");
    }
}
