package com.zzf.algorithm.chapterOne;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Stack<Item> implements Iterable<Item> {

    private Node<Item> first;

    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Stack() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.item;
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
        // 100页
        Stack<String>[] a = (Stack<String>[]) new Stack[5];
        for (int i = 0; i < 5; i++) {
            Stack<String> stackStr = new Stack<>();
            stackStr.push("i = " + i);
            a[i] = stackStr;
        }
        System.out.println(a.length);
        for (Stack<String> strings : a) {
            System.out.println("strings = " + strings);
        }



//        Stack<String> stack = new Stack<>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-")) {
//                stack.push(item);
//            } else if (!stack.isEmpty()) {
//                StdOut.print(stack.pop() + " ");
//            }
//            StdOut.println("(" + stack.size() + " left on stack)");
//        }
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
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
