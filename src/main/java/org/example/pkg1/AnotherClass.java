package org.example.pkg1;

import org.example.pkg.SomeClass;

public class AnotherClass {
    public static void main(String[] args) {
        SomeClass someClass = new SomeClass();
        someClass.publicField = 1;
        someClass.publicMethod();
    }
}
