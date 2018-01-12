package com.zb.project.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tangshiwen on 2017/7/25.
 */
public class ClassValueTools {
    //通过对象和具体的字段名字获得字段的值

    public static void method(Object obj,String filed) {

        try {

            Class clazz = obj.getClass();

            PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);

            Method getMethod = pd.getReadMethod();//获得get方法

            Object o = getMethod.invoke(obj);//执行get方法返回一个Object

            System.out.println(o);

        } catch (SecurityException e) {

            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            e.printStackTrace();

        } catch (IntrospectionException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (InvocationTargetException e) {

            e.printStackTrace();

        }

    }
}
