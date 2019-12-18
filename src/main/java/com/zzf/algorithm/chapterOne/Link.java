package com.zzf.algorithm.chapterOne;

import com.zzf.algorithm.base.StdOut;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 假想的，这是一个不合理的链表
 */
@Deprecated
public class Link implements Iterable {

    private Node first;

    private int n;

    private static class Node {
        private Node item;
        private Node next;
    }

    public Link() {
        first = null;
        n = 0;
    }

    public void add(Node item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        Link link = new Link();

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        link.add(node1);
        link.add(node2);
        link.add(node3);

        StdOut.println("size of link = " + link.size());
        for (Object o : link) {
            System.out.println(o);
        }
    }

    // ******************************

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

}
