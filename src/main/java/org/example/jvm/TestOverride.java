package org.example.jvm;

/**
 *
 * 结果：
 I'm Child, I have 0  //先调用父类的构造函数，父类构造函数中的showTheMoney又被重写，执行的时子类的方法，这个时候child.money为零值，还没有=2
 I'm Child, I have 4 //调用子类的构造函数, money =4  没啥好说的
 I'm a guy who has 2 // 直接用 f.money 访问字段，要参考静态调用 所以是Father的money 如果父子类有各自的getMoney方法 那么f.getMoney 返回的就是子类的money
 */
public class TestOverride {
    static class Father{
        public int money = 1;
        Father() {
            money = 2;
            showTheMoney();
        }

        void showTheMoney() {
            System.out.println("I'm Father, I have " + money);
        }

        public int getMoney() {
            return money;
        }

    }

    static class Child extends Father{
        public int money = 3;

        Child() {
            money = 4;
            showTheMoney();
        }

        @Override
        void showTheMoney() {
            System.out.println("I'm Child, I have " + money);
        }


        public int getMoney() {
            return money;
        }
    }

    public static void main(String[] args) {
        Father f = new Child();
        System.out.println("I'm a guy who has " + f.money);
        System.out.println("I'm a guy who has " + f.getMoney());
    }

}
