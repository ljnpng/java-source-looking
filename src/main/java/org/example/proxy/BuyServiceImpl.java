package org.example.proxy;

public class BuyServiceImpl implements IBuyService{
    @Override
    public void buy(int userId) {
        System.out.println("just buy");
    }

    @Override
    public void refund(int nums) {
        System.out.println("just refund");
    }
}
