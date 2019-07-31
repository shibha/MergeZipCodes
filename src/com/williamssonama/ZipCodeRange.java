package com.williamssonama;

/**
 * A class to represent a ZipCodeRange which will have a lower and upper bound
 */

public class ZipCodeRange {

    public ZipCodeRange(int lower, int upper) {
        this.upper = upper;
        this.lower = lower;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    private int upper;

    public int getUpper() {
        return upper;
    }

    private int lower;

    public int getLower() {
        return lower;
    }

    public String toString() {
        return "[" + lower + "," + upper + "]";
    }

}