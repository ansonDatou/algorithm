package com.zzf.algorithm.chapterOne;

import com.zzf.algorithm.base.In;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first;

    private Node<Item> last;

    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return first.item;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;
        n--;

        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        Queue<String> queue = new Queue<>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) {
//                queue.enqueue(item);
//            } else if (!queue.isEmpty()) {
//                StdOut.print(queue.dequeue() + " ");
//            }
//            StdOut.println("(" + queue.size() + " left on queue)");
//        }

        int[] ints = readInts("/Users/jeffery/Downloads/a.txt");
        for (int a : ints) {
            System.out.println(a);
        }
    }

    public static int[] readInts(String name) {
        In in = new In(name);
        Queue<Integer> q = new Queue<>();
        while (!in.isEmpty()) {
            q.enqueue(in.readInt());
        }

        int n = q.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = q.dequeue();
            System.out.println("q.dequeue() == " + a[i]);
        }

        return a;
    }



    // ******************************

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    @Override
    public void forEach(Consumer<? super Item> action) {

    }

    @Override
    public Spliterator<Item> spliterator() {
        return null;
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super Item> action) {

        }
    }

}
