package com.zzf.algorithm.chapterOne;

import com.zzf.algorithm.base.StdIn;

public class Reverse {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
        }

        for (Integer integer : stack) {
            System.out.println("integer = " + integer);
        }

        for (int i = 0,j = 0 ; i < 5 || j < 7; i++, j++) {
            System.out.println(i);
            System.out.println(j);
            System.out.println("----------");
        }
    }
}
