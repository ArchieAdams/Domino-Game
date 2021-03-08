package com.archie;


public class Domino {
    public int leftNumber;
    public int rightNumber;


    public Domino(int leftNumber, int rightNumber) {
        this.leftNumber = leftNumber;
        this.rightNumber = rightNumber;
    }



    @Override
    public String toString() {
        return "|" +
                + leftNumber +
                ":" + rightNumber +
                '|';
    }
}
