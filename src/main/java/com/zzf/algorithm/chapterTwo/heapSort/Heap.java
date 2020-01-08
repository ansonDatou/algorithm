package com.zzf.algorithm.chapterTwo.heapSort;

import com.zzf.algorithm.base.StdIn;
import com.zzf.algorithm.base.StdOut;

public class Heap {

    private Heap() {
    }

    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // 构建堆
        // k：是最后一个二叉堆的父节点，遍历所有的二叉堆进行比较并替换
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }
        show(pq, "[for->sink] --> ");
        System.out.println();

        while (n > 1) {

            // 替换堆首和堆尾
            exch(pq, 1, n--);
            show(pq, "[while->exch] --> ");

            // 1 与 n-1 的二叉堆中比较并替换
            sink(pq, 1, n);
            System.out.println();
        }
    }

    /**
     * 下沉
     * 二叉堆中比较并替换
     * @param pq
     * @param k
     * @param n
     */
    private static void sink(Comparable[] pq, int k, int n) {
        StdOut.println(String.format("[sink] k : %s, n : %s", k, n));

        // 父节点：k，子节点：j、j+1
        // k是父节点，要保证2个以上的元素，才能比较替换
        while (2 * k <= n) {

            // j是k的子节点
            int j = 2 * k;

            // k的两个子节点进行比较，取出子节点最大的那一个
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }

            // 父节点与最大的子节点比较，父节点 > 子节点，则跳出
            if (!less(pq, k, j)) {
                break;
            }

            // 父节点 < 子节点，则替换
            exch(pq, k, j);

            // j作为父节点，进行下次比较
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        StdOut.println(String.format("[exch] i : %s, j : %s", i, j));
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    private static void show(Comparable[] a, String desc) {
        StdOut.print(desc);
        for (int i = 0; i < a.length; i++) {
            StdOut.print(String.format("pq[%s] : %s |  ", i, a[i]));
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // 9 1 7 3 5 4 6 2 8 0
        String[] a = StdIn.readAllStrings();
        Heap.sort(a);
        show(a, "[main] --> ");
    }
}
