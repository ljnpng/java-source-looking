package org.example.pkg;

public class SamePackageClass {
    public static void main(String[] args) {
        SomeClass someClass = new SomeClass();
        someClass.publicMethod();
        someClass.defaultMethod();
        someClass.protectedMethod();
        someClass.publicField = 1;
        someClass.defaultField = 1;
        someClass.protectedField = 1;
    }
}
