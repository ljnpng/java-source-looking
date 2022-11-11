package org.example;

// 双重检验锁
public class SingletonUseVolatile {

    // volatile 为了防止 执行 instance = new SingletonUseVolatile(); 是指令重排
    // instance = new SingletonUseVolatile(); 有三步，可能出现 a-> c -> b。
    // a. new 分配内存空间
    // b. SingletonUseVolatile() 初始化
    // c. instance 指向 分配的内存空间

    // 如果a -> c 时，另一个线程getInstance，到第一个if时 instance != null 此时 返回的instance 还没有初始化
    private static volatile SingletonUseVolatile instance;

    private SingletonUseVolatile() {

    }

    // 演化顺序应该是从3-2-1 一步步优化的
    public static SingletonUseVolatile getInstance() {
        //1. 先判断是否已经实例化，有实例化就不用去判断锁了，减少资源消耗
        if (instance == null) {
            //2. 锁也是必要的，没有锁 可能出现多个线程同时进行new
            synchronized (SingletonUseVolatile.class) {

                //3. 这个判断也是必要的， 假设线程a进来同步块，线程b等待锁。线程a解锁后，b进入同步块，如果没有这个判断，b只能再次new
                if (instance == null) {
                    instance = new SingletonUseVolatile();
                }
            }
        }
        return instance;
    }

}
