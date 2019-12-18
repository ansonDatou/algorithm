package com.zzf.algorithm.chapterOne;

import com.zzf.algorithm.base.StdIn;
import com.zzf.algorithm.base.StdOut;

public class Stats {

    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            StdOut.println("value = " + value);
            numbers.add(value);
        }

        int n = numbers.size();
        double sum = 0.0;
        for (Double x : numbers) {
            sum += x;
        }

        double mean = sum / n;

        sum = 0.0;
        for (Double x : numbers) {
            StdOut.printf("x - mean : %.2f \n", x - mean);
            StdOut.printf("sum : %f", sum);
            sum += (x - mean) * (x - mean);
        }

        StdOut.println("\n");

        double std = Math.sqrt(sum / (n - 1));

        StdOut.printf("mean %.2f\n", mean);
        StdOut.printf("std dev : %.2f\n", std);
    }
}
