package org.example.pkg1;

import org.example.pkg.SomeClass;

public class SubSomeClass extends SomeClass {

    public void someMethod() {
        super.publicField = 1;
        super.protectedField = 1;
        super.publicMethod();
        super.protectedMethod();
    }

    public static void main(String[] args) {
        SubSomeClass subSomeClass = new SubSomeClass();
        subSomeClass.publicField = 1;
        subSomeClass.protectedField = 1;
        subSomeClass.publicMethod();
        subSomeClass.protectedMethod();
    }
}
