package com.zzf.algorithm.chapterOne;

import com.zzf.algorithm.base.StdIn;
import com.zzf.algorithm.base.StdOut;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FixedCapacityStackOfStrings implements Iterable<String> {

    private String[] a; // a.length 是数组总共的个数

    private int n; // n 是实际数组元素的个数

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
        n = 0;
    }

    public void push(String item) {
        a[n++] = item;
    }

    public String pop() {
        return a[n--];
    }

    public String peek() {
        return a[n-1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == a.length;
    }

    public int size() {
        return n;
    }

    // ******************************

    public static void main(String[] args) {
        FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);
            } else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }

            StdOut.println("(" + s.size() + " left on stack)");
        }
    }

    // ******************************

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super String> action) {

    }

    @Override
    public Spliterator<String> spliterator() {
        return null;
    }
}
