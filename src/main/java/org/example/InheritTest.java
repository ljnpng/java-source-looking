package org.example;

public class InheritTest {
    static class Parent {
        String family;
        private void name() {
            System.out.println("parent");
        }

        static int up() {
            return 1;
        }
    }

    static class Child extends Parent{
        public Child() {
        }
    }

    interface Human {
        default void test() {

        }
    }

    public static void main(String[] args) {
        System.out.println(Child.up());
        String family = new Child().family;

    }

    public String testString() {
        String s = "hh";
        return "s" + "t" + s;
    }
}


