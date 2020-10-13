package org.geekbang.time.commonmistakes._10_collection._03_listvsmap;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListVsMapApplication {

    public static void main(String[] args) throws InterruptedException {

        int elementCount = 100_0000;
        int loopCount = 1000;

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("listSearch");
        Object list = listSearch(elementCount, loopCount);
        //查看java对象大小
        System.out.println("list对象大小：" + ObjectSizeCalculator.getObjectSize(list));
        stopWatch.stop();

        System.out.println("==================");

        stopWatch.start("mapSearch");
        Object map = mapSearch(elementCount, loopCount);
        System.out.println("map对象大小：" +ObjectSizeCalculator.getObjectSize(map));
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());


        //TimeUnit.HOURS.sleep(1);
    }

    /**
     * 使用 MAT 工具分析堆可以证明，ArrayList 在内存占用上性价比很高，77% 是实际的数据（如第 1 个图所示，16000000/20861992），
     * 而 HashMap 的“含金量”只有 22%（如第 2 个图所示，16000000/72386640）。
     *
     * 所以，在应用内存吃紧的情况下，我们需要考虑是否值得使用更多的内存消耗来换取更高的性能。这里我们看到的是平衡的艺术，空间换时间，还是时间换空间，只考虑任何一个方面都是不对的。
     *
     * @param elementCount
     * @param loopCount
     * @return
     */
    private static Object listSearch(int elementCount, int loopCount) {
        List<Order> list = IntStream.rangeClosed(1, elementCount).mapToObj(i -> new Order(i)).collect(Collectors.toList());

        IntStream.rangeClosed(1, loopCount).forEach(i -> {
            int search = ThreadLocalRandom.current().nextInt(elementCount);
            Order result = list.stream().filter(order -> order.getOrderId() == search).findFirst().orElse(null);
            Assert.assertTrue(result != null && result.getOrderId() == search);
        });
        return list;
    }

    /**
     * 我们知道，搜索 ArrayList 的时间复杂度是 O(n)，而 HashMap 的 get 操作的时间复杂度是 O(1)。
     * 所以，要对大 List 进行单值搜索的话，可以考虑使用 HashMap，其中 Key 是要搜索的值，Value 是原始对象，会比使用 ArrayList 有非常明显的性能优势。
     *
     *
     * 如果要对大 ArrayList 进行去重操作，也不建议使用 contains 方法，而是可以考虑使用 HashSet 进行去重。
     * @param elementCount
     * @param loopCount
     * @return
     */
    private static Object mapSearch(int elementCount, int loopCount) {
        //Map 的 Key 是订单号，Value 是订单对象
        Map<Integer, Order> map = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toMap(Function.identity(), i -> new Order(i)));
        IntStream.rangeClosed(1, loopCount).forEach(i -> {
            int search = ThreadLocalRandom.current().nextInt(elementCount);
            Order result = map.get(search);
            Assert.assertTrue(result != null && result.getOrderId() == search);
        });
        return map;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Order {
        private int orderId;
    }

    /**
     * 总结：
     * 第三，想当然认为，内存中任何集合的搜索都是很快的，结果在搜索超大 ArrayList 的时候遇到性能问题。
     * 我们考虑利用 HashMap 哈希表随机查找的时间复杂度为 O(1) 这个特性来优化性能，不过也要考虑 HashMap 存储空间上的代价，要平衡时间和空间。
     */
}

