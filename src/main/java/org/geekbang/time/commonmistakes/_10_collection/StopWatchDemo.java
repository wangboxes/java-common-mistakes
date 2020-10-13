package org.geekbang.time.commonmistakes._10_collection;

import org.springframework.util.StopWatch;

/**
 * 秒表: Stop watch
 * @describe： https://blog.csdn.net/gxs1688/article/details/87185030
 * @Date：2020/10/13 17:07
 * @author：wbx
 */
public class StopWatchDemo {

    public static void main(String[] args) throws InterruptedException {
        /*
        1.记录开始时间点
        2.记录结束时间点
        3.输出执行时间及各个时间段的占比
        使用：start开始记录，stop停止记录，然后通过StopWatch的prettyPrint方法
        原理：通过start与stop方法分别记录开始时间与结束时间，其中在记录结束时间时，会维护一个链表类型的tasklist属性，从而使该类可记录多个任务，最后的输出也仅仅是对之前记录的信息做了一个统一的归纳输出，从而使结果更加直观的展示出来。

        注意点：一个StopWatch实例一次只能开启一个task，不能同时start多个task，并且在该task未stop之前不能start一个新的task，必须在该task stop之后才能开启新的task，若要一次开启多个，需要new不同的StopWatch实例
         */
        StopWatch sw = new StopWatch("test");
        sw.start("task1");
        // do something
        Thread.sleep(100);
        sw.stop();
        System.out.println(sw.getTotalTimeMillis());

        sw.start("task2");
        // do something
        Thread.sleep(200);
        sw.stop();
        System.out.println("~~~~~~~~~~~~~~~~~sw.prettyPrint()~~~~~~~~~~~~~~~~~");
        System.out.println(sw.prettyPrint());

        System.out.println("~~~~~~~~~~~~~~~~~sw.shortSummary()~~~~~~~~~~~~~~~~~");
        System.out.println(sw.shortSummary());
        System.out.println("~~~~~~~~~~~~~~~~~sw.getTotalTimeMillis() 常用~~~~~~~~~~~~~~~~~");
        System.out.println(sw.getTotalTimeMillis());
    }

}
