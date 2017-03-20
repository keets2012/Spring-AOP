package com.keets;

/**
 * Created by keets on 2017/3/20.
 */
public class CGDynamicTest {

    //实例化一个CGDynamicTest代理对象
    //通过getProxyObject方法获得被代理后的对象
    Math math=(Math)new CGDynamicProxy().getProxyObject(new Math());
    @org.junit.Test
    public void test01()
    {
        int n1=100,n2=5;
        math.add(n1, n2);
        math.sub(n1, n2);
        math.mut(n1, n2);
        math.div(n1, n2);
    }
}
