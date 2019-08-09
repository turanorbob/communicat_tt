package com.example.test;

public class PreTest {
    public static void main(String args[]){
        Integer a = 1;
        Object b = null;
        Integer c = (Integer)b;
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
    }
}
