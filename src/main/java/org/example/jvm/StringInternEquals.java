package org.example.jvm;

public class StringInternEquals {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = new String("hello");
        String s3 = s2.intern();
        assert s1 == s3;
        assert s2 != s3;
    }
}
