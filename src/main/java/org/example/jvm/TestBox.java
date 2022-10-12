package org.example.jvm;

public class TestBox {


    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Integer f1 = 320;
        Long g = 3L;
        System.out.println(c == d); // true == 在不遇到运算的时候不会自动拆箱
        System.out.println(e == f); // false
        System.out.println(c == (a + b)); // true
        System.out.println(c.equals(a + b)); // true
        System.out.println(g == (a + b));  // true
        System.out.println(g.equals(a + b)); // equals 不处理数字转型
    }

}
