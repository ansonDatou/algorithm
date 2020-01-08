package com.zzf.algorithm.chapterTwo.heapSort;

import com.zzf.algorithm.base.StdIn;
import com.zzf.algorithm.base.StdOut;
import com.zzf.algorithm.chapterOne.Stack;

public class TopM {

    public static void main(String[] args) {

        // 打印输入流中最大的m行
        int m = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<>(m + 1);
        while (StdIn.hasNextLine()) {

            // 为下一行输入，创建一个元素并放入优先队列中
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > m) {

                // 如果优先队列中存在m+1个元素，则删除其中最小的元素
                // 最大的m个元素都在优先队列中
                pq.delMin();
            }
        }

        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMin());
        }

        for (Transaction t : stack) {
            StdOut.println(t);
        }
    }
}
