package org.example.proxy;
/*
* jdk 代理，
*
* 主要用法：
* 1. 代理类实现 InvocationHandler#invoke
* 2. Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this) 生成被代理的实例
* */

public class JdkProxyTest {

    public static void main(String[] args) {
        IBuyService proxy = new BuyServiceProxy(new BuyServiceImpl()).getProxy();
        proxy.buy(11);
    }
}
