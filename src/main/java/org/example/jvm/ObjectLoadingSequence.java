package org.example.jvm;

/*

Sport static //父类先加载

FootBall static //然后在加载自己类

Sport init //调用构造函数的时候也是先调用父类构造函数

FootBall init //然后再是自己的构造函数

Number= 11 //这个是继承，多态没啥好说的

BasketBall static //父类已经加载过了，只要再加载自己就行了

Sport init //构造函数同样先调用父类构造函数

BasketBall init //自己的构造函数
Number= 5*/
public class ObjectLoadingSequence {

    static class Sport {
        int number;

        static {
            System.out.println("Sport static");
        }

        Sport() {
            System.out.println("Sport init");
        }

        void printNum() {
            System.out.println("Number= " + number);
        }
    }

    static class FootBall extends Sport {
        static {
            System.out.println("FootBall static");
        }

        FootBall() {
            System.out.println("FootBall init");
            number = 11;
        }
    }

    static class BasketBall extends Sport {
        static {
            System.out.println("BasketBall static");
        }

        BasketBall() {
            System.out.println("BasketBall init");
            number = 5;
        }
    }

    public static void main(String[] args) {
        Sport s = new FootBall();
        s.printNum();
        s = new BasketBall();
        s.printNum();
    }
}
