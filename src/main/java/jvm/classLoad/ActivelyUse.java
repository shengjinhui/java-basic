package jvm.classLoad;

import org.aspectj.weaver.ast.Var;

import javax.sound.midi.Soundbank;
import java.util.UUID;

/**
 * @Author: yichuan
 * @Date: 2020/11/3 10:36 上午
 * @Description: 记得添加JVM参数: -XX: +TraceClassLoading
 */
public class ActivelyUse {
    public static void main(String[] args) {
        MyParent[] myParents = new MyParent[1];
        System.out.println(myParents.getClass());

    }
}

class MyParent {
    public static final String str = "Hello Parent";
    public static final String str2 = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent static block");
    }
}

class MyChild extends MyParent {
    public static String st2 = "Hello Child";

    static {
        System.out.println("MyChild static block");
    }
}
