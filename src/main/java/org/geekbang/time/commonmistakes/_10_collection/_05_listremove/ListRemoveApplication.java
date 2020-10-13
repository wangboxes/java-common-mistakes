package org.geekbang.time.commonmistakes._10_collection._05_listremove;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 思考题：
 * 问题1：调用类型是 Integer 的 ArrayList 的 remove 方法删除元素，传入一个 Integer 包装类的数字和传入一个 int 基本类型的数字，结果一样吗？
 *  答：基本类型：是根据index来删除；包装类型：是根据元素来删除
 *
 *
 * 问题2：循环遍历 List，调用 remove 方法删除元素，往往会遇到 ConcurrentModificationException 异常，原因是什么，修复方式又是什么呢？
 */
public class ListRemoveApplication {

    public static void main(String[] args) {
//        removeByIndex(4);
//        removeByValue(Integer.valueOf(4));

        forEachRemoveWrong();
        forEachRemoveRight();
        forEachRemoveRight2();
    }

    private static void removeByIndex(int index) {
        List<Integer> list =
                IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list.remove(index));
        System.out.println(list);
    }


    private static void removeByValue(Integer value) {
        List<Integer> list =
                IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list.remove(value));
        System.out.println(list);
    }

    private static void forEachRemoveWrong() {
        List<String> list =
                IntStream.rangeClosed(1, 10).mapToObj(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
        for (String i : list) {
            if ("2".equals(i)) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    private static void forEachRemoveRight() {
        List<String> list =
                IntStream.rangeClosed(1, 10).mapToObj(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            if ("2".equals(next)) {
                iterator.remove();
            }
        }
        System.out.println(list);

    }

    private static void forEachRemoveRight2() {
        List<String> list =
                IntStream.rangeClosed(1, 10).mapToObj(String::valueOf).collect(Collectors.toCollection(ArrayList::new));
        //推荐
        list.removeIf(item -> item.equals("2"));
        System.out.println(list);
    }
}

