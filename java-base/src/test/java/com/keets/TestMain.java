package com.keets;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestMain {

    //实例化一个MathProxy代理对象
    //通过getProxyObject方法获得被代理后的对象
    IMath math = (IMath) new DynamicProxy().getProxyObject(new Math());

    @Test
    public void test01() {
        math.getClass().getDeclaredAnnotations();
        int n1 = 100, n2 = 5;
        math.add(n1, n2);
        math.sub(n1, n2);
        math.mut(n1, n2);
        math.div(n1, n2);
    }

    @Test
    public void test02() {
        IMath rs = new Math();//这里指定被代理类
        InvocationHandler ds = new DynamicProxy(rs);
        Class<?> cls = rs.getClass();

        //以下是一次性生成代理

        IMath math = (IMath) Proxy.newProxyInstance(
                cls.getClassLoader(), cls.getInterfaces(), ds);

        //这里可以通过运行结果证明subject是Proxy的一个实例，这个实例实现了Subject接口
        System.out.println(math instanceof Proxy);

        //这里可以看出subject的Class类是$Proxy0,这个$Proxy0类继承了Proxy，实现了Subject接口
        System.out.println("subject的Class类是：" + math.getClass().toString());

        System.out.print("subject中的属性有：");

        Field[] field = math.getClass().getDeclaredFields();
        for (Field f : field) {
            System.out.print(f.getName() + ", ");
        }

        System.out.print("\n" + "subject中的方法有：");

        Method[] method = math.getClass().getDeclaredMethods();

        for (Method m : method) {
            System.out.print(m.getName() + ", ");
        }

        System.out.println("\n" + "subject的父类是：" + math.getClass().getSuperclass());

        System.out.print("\n" + "subject实现的接口是：");

        Class<?>[] interfaces = math.getClass().getInterfaces();

        for (Class<?> i : interfaces) {
            System.out.print(i.getName() + ", ");
        }

        System.out.println("\n\n" + "运行结果为：");
        math.add(1, 2);
    }


}
