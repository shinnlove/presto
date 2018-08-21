/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.controller;

import java.io.IOException;
import java.io.InputStream;

import com.shinnlove.presto.model.Student;

/**
 * 测试类加载器。
 *
 * @author shinnlove.jinsheng
 * @version $Id: ClassLoaderTest.java, v 0.1 2018-08-20 下午6:37 shinnlove.jinsheng Exp $$
 */
public class ClassLoaderTest {

    /**
     * 使用自定义的classLoader加载类。
     *
     * @param args
     * @throws ClassNotFoundException       使用自定义类加载器加载的时候
     * @throws IllegalAccessException       调用`newInstance`构造器实例化的时候，可能无法通路错误
     * @throws InstantiationException       调用`newInstance`构造器实例化的时候，可能实例化错误
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
                                          InstantiationException {
        // 使用当前classLoader(AppClassLoader)先加载2个Class对象
        Student s1 = new Student();
        com.shinnlove.presto.service.aspect.Student s2 = new com.shinnlove.presto.service.aspect.Student();

        // 定义自己的classLoader
        ClassLoader myClassLoader1 = getDIYClassLoader();
        ClassLoader myClassLoader2 = getDIYClassLoader();

        // 同一个classLoader加载相同Class的哈希地址是一样的
        oneClassLoaderHashCode(myClassLoader1);

        // 同一个classLoader加载不同package的相同类(主要是类名一样)
        oneClassLoaderPackageDiff(myClassLoader1);

        // 不同classLoader加载相同Class的哈希地址不一样
        twoClassLoaderHashCode(myClassLoader1, myClassLoader2);

        // 实例化一个类会调用类的`Clinit`方法
        instanceCallUpClinit(myClassLoader1);
    }

    /**
     * 定义自己的ClassLoader。
     *
     * @return
     */
    public static ClassLoader getDIYClassLoader() throws ClassNotFoundException,
                                                 IllegalAccessException, InstantiationException {
        // 定义自己的classLoader
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object obj = classLoader.loadClass("com.shinnlove.presto.controller.ClassLoaderTest")
            .newInstance();
        System.out.println(obj instanceof ClassLoaderTest);

        System.out.println(obj.getClass().getClassLoader());

        return classLoader;
    }

    /**
     * 使用同一个ClassLoader加载一个全类名相同的类，hashCode出来的地址相同。
     *
     * @param myClassLoader
     * @throws ClassNotFoundException
     */
    public static void oneClassLoaderHashCode(ClassLoader myClassLoader)
                                                                        throws ClassNotFoundException {
        System.out.println();
        System.out.println("=============使用同一个ClassLoader加载一个全类名相同的类============");
        // 加载两个Student
        Class oneClass = myClassLoader.loadClass("com.shinnlove.presto.model.Student");
        Class twoClass = myClassLoader.loadClass("com.shinnlove.presto.model.Student");

        // 同一个类加载器，加载出来的Class是一样的
        System.out.println("oneClassLoader.oneClass.hashCode=" + oneClass.hashCode());
        System.out.println("oneClassLoader.twoClass.hashCode=" + twoClass.hashCode());
        System.out.println("=============使用同一个ClassLoader加载一个全类名相同的类============");
        System.out.println();
    }

    /**
     * 同一个ClassLoader加载不同包名下的相同类。
     *
     * @param classLoader
     * @throws ClassNotFoundException
     */
    public static void oneClassLoaderPackageDiff(ClassLoader classLoader)
                                                                         throws ClassNotFoundException {
        System.out.println();
        System.out.println("=============同一个ClassLoader加载不同包名下的相同类============");
        // 加载两个package不一样、类名和内容完全一样的Student
        Class onePackageClass = classLoader.loadClass("com.shinnlove.presto.model.Student");
        Class twoPackageClass = classLoader
            .loadClass("com.shinnlove.presto.service.aspect.Student");

        System.out.println("oneClassLoader.onePackageClass.hashCode=" + onePackageClass.hashCode());
        System.out.println("oneClassLoader.twoPackageClass.hashCode=" + twoPackageClass.hashCode());
        System.out.println("=============同一个ClassLoader加载不同包名下的相同类============");
        System.out.println();
    }

    /**
     * 使用不同ClassLoader加载一个全类名相同的类，hashCode地址相同，但是instanceof是不同的。
     *
     * @param classLoaderOne
     * @param classLoaderTwo
     * @throws ClassNotFoundException
     */
    public static void twoClassLoaderHashCode(ClassLoader classLoaderOne, ClassLoader classLoaderTwo)
                                                                                                     throws ClassNotFoundException,
                                                                                                     IllegalAccessException,
                                                                                                     InstantiationException {
        System.out.println();
        System.out.println("=============使用不同ClassLoader加载一个全类名相同的类============");
        // 不同classLoader加载同一个全类名class
        Class oneClass = classLoaderOne.loadClass("com.shinnlove.presto.model.Student");
        Class twoClass = classLoaderTwo.loadClass("com.shinnlove.presto.model.Student");

        // 使用反射加载一个类
        Class threeClass = Class.forName("com.shinnlove.presto.model.Student");

        // 不同类加载器，加载出来的Class的HashCode不一样
        System.out.println("twoClassLoader.oneClass.hashCode=" + oneClass.hashCode());
        System.out.println("twoClassLoader.twoClass.hashCode=" + twoClass.hashCode());
        System.out.println("commonClassLoader.threeClass.hashCode=" + threeClass.hashCode());

        Object one = oneClass.newInstance();
        Object two = twoClass.newInstance();
        Object three = threeClass.newInstance();

        System.out.println(one.getClass().getClassLoader());

        boolean oneTrue = one instanceof com.shinnlove.presto.model.Student;
        boolean twoTrue = two instanceof com.shinnlove.presto.model.Student;
        boolean threeTrue = three instanceof com.shinnlove.presto.model.Student;

        System.out.println("instanceof com.shinnlove.presto.model.Student: oneTrue=" + oneTrue
                           + ", twoTrue=" + twoTrue + ", threeTure=" + threeTrue);

        System.out.println("============使用不同ClassLoader加载一个全类名相同的类=============");
        System.out.println();
    }

    /**
     * 实例化一个类JVM会调用<clinit>方法。
     *
     * @param classLoader
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void instanceCallUpClinit(ClassLoader classLoader) throws ClassNotFoundException,
                                                                    IllegalAccessException,
                                                                    InstantiationException {
        System.out.println();
        System.out.println("============实例化一个类JVM会调用<clinit>方法=============");
        // 加载两个Student
        Class modelStudentClass = classLoader.loadClass("com.shinnlove.presto.model.Student");

        // 有了类字面变量，就可以实例化或者反射做这个事情了
        // 调用构造函数的之前，才会运行`<clinit>`方法，输出static的内容
        Student modelStudent = (Student) modelStudentClass.newInstance();

        // 输出两个Student
        System.out.println("modelStudentClass=" + modelStudentClass);
        System.out.println("modelStudent=" + modelStudent);
        System.out.println("============实例化一个类JVM会调用<clinit>方法=============");
        System.out.println();
    }

}