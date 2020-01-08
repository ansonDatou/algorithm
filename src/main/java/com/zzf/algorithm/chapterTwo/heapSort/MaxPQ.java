package com.zzf.algorithm.chapterTwo.heapSort;


import com.zzf.algorithm.base.StdIn;
import com.zzf.algorithm.base.StdOut;
import com.zzf.algorithm.chapterTwo.Example;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code MaxPQ} class represents a priority queue of generic keys.
 * It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 * operations, along with methods for peeking at the maximum key,
 * testing if the priority queue is empty, and iterating through
 * the keys.
 * <p>
 * This implementation uses a <em>binary heap</em>.
 * The <em>insert</em> and <em>delete-the-maximum</em> operations take
 * &Theta;(log <em>n</em>) amortized time, where <em>n</em> is the number
 * of elements in the priority queue. This is an amortized bound
 * (and not a worst-case bound) because of array resizing operations.
 * The <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take
 * &Theta;(1) time in the worst case.
 * Construction takes time proportional to the specified capacity or the
 * number of items used to initialize the data structure.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @param <Key> the generic type of key on this priority queue
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

public class MaxPQ<Key> implements Iterable<Key> {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private Comparator<Key> comparator;  // optional comparator

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator   the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order in which to compare the keys
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }


    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }


    /**
     * Adds a new key to this priority queue.
     *
     * @param x the new key to add to this priority queue
     */
    public void insert(Key x) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;

        StdOut.print("【swim之前】");
        for (int i = 0; i < pq.length; i++) {
            StdOut.print(String.format("pq[%s] : %s |  ", i, pq[i]));
        }
        StdOut.println();

        StdOut.println(String.format("insert --> 数组长度 n : %s", n));
        swim(n);
        assert isMaxHeap();

        StdOut.print("【swim之后】");
        for (int i = 0; i < pq.length; i++) {
            StdOut.print(String.format("pq[%s] : %s |  ", i, pq[i]));
        }
        StdOut.println();
        StdOut.println();
    }

    /**
     * Removes and returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");

        show("【delMax之前】");

        // 取出最大
        Key max = pq[1];
        StdOut.println(String.format("delMax --> max : %s", max));

        // 最后一个 与 根节点 替换，并将数组长度-1
        exch(1, n--);

        show("【exch -- sink】");

        sink(1);
        pq[n + 1] = null;     // to avoid loitering and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMaxHeap();

        show("【delMax之后】");

        return max;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    /**
     * 上浮
     * @param k
     */
    private void swim(int k) {
        System.out.println(String.format("swim --> 数组长度 k : %s", k));

        // k：数组长度，长度k/2 和 数组最后一个 比较
        // k/2 是 当前节点的父节点
        while (k > 1 && less(k / 2, k)) {

            // exch(k, k / 2); 这里与 exch(k / 2, k); 是一样的
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * 下沉
     * @param k
     */
    private void sink(int k) {

        // 要保证2个以上的元素，才能比较替换
        while (2 * k <= n) {

            // j是k的子节点
            int j = 2 * k;

            // k的两个子节点进行比较，取出子节点最大的那一个
            if (j < n && less(j, j + 1)) j++;

            // 父节点与最大的子节点比较，父节点 > 子节点，则跳出
            if (!less(k, j)) break;

            // 父节点 < 子节点，则替换
            exch(k, j);

            // j作为父节点，进行下次比较
            k = j;
        }
    }

    private void show(String desc) {
        StdOut.print(desc);
        for (int i = 0; i < pq.length; i++) {
            StdOut.print(String.format("pq[%s] : %s |  ", i, pq[i]));
        }
        StdOut.println();
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    private boolean less(int i, int j) {
        System.out.println(String.format("less --> i : %s, j : %s", i, j));

        if (comparator == null) {
            System.out.println(String.format("((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0 ==> %b", ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0));
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            System.out.println(String.format("comparator.compare(pq[i], pq[j]) < 0 ==> %b", comparator.compare(pq[i], pq[j]) < 0));
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        System.out.println(String.format("exch --> i : %s, j : %s", i, j));
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..n] a max heap?
    private boolean isMaxHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && less(k, left)) return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }


    /***************************************************************************
     * Iterator.
     ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in descending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in descending order
     */
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private MaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MaxPQ<Key>(size());
            else copy = new MaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    /**
     * Unit tests the {@code MaxPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }

        while (!pq.isEmpty()) {
            StdOut.print(pq.delMax() + " | ");
            StdOut.println();
        }

        StdOut.println("(" + pq.size() + " left on pq)");
    }

}
