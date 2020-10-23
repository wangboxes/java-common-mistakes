package org.geekbang.time.commonmistakes._10_collection;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

/**
 * @describe：
 * @Date：2020/10/14 15:18
 * @author：wbx
 */
public class ObjectSizeCalculatorDemo {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(ObjectSizeCalculator.getObjectSize(a));

        B b = new B();
        System.out.println(ObjectSizeCalculator.getObjectSize(b));
    }


    // 对象A： 对象头12B + 内部对象s引用 4B + 内部对象i 基础类型int 4B + 对齐 4B = 24B
    // 内部对象s 对象头12B + 2个内部的int类型8B + 内部的char[]引用 4B + 对齐0B = 24B
    // 内部对象str的内部对象char数组 对象头12B + 数组长度4B + 对齐0B = 16B
    // 总： 对象A 24+ 内部对象s 24B + 内部对象s的内部对象char数组 16B =64B
    static class A {
        String s = new String();
        int i = 0;

    }

    // 对象B：对象头12B + 内部对象s引用 4B + 内部对象i 基础类型int 4B + 对齐 4B = 24B
    // s没有被分配堆内存空间
    // 总： 对象B 24B
    static class B {
        String s;
        int i = 0;

    }
}
