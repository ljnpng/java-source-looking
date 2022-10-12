package org.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BuyServiceProxy implements InvocationHandler {
    private Object target;

    public BuyServiceProxy(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args);
        Object invoke = method.invoke(target, args);
        after(args);
        return invoke;
    }

    private void after(Object[] args) {
        System.out.println("after");
    }

    private void before(Object[] args) {
        System.out.println("before");
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
