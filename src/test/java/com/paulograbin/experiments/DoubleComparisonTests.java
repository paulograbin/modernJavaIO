package com.paulograbin.experiments;

import org.junit.jupiter.api.Test;

public class DoubleComparisonTests {

    @Test
    void name() {
        Double v = Double.valueOf("15.45");

        double d = 15.45;

        int compare = Double.compare(v, d);
        System.out.println("Compare = " + compare);


        System.out.println("Equals...");
        System.out.println(v == d);
        System.out.println(d == v);
    }
}
