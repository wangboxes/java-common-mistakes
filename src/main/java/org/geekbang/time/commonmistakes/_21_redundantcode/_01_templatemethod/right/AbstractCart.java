package org.geekbang.time.commonmistakes._21_redundantcode._01_templatemethod.right;

import org.geekbang.time.commonmistakes._21_redundantcode._01_templatemethod.Cart;
import org.geekbang.time.commonmistakes._21_redundantcode._01_templatemethod.Db;
import org.geekbang.time.commonmistakes._21_redundantcode._01_templatemethod.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractCart {

    public Cart process(long userId, Map<Long, Integer> items) {

        Cart cart = new Cart();

        List<Item> itemList = new ArrayList<>();
        items.entrySet().stream().forEach(entry -> {
            Item item = new Item();
            item.setId(entry.getKey());
            item.setPrice(Db.getItemPrice(entry.getKey()));
            item.setQuantity(entry.getValue());
            itemList.add(item);
        });
        cart.setItems(itemList);

        itemList.stream().forEach(item -> {
            processCouponPrice(userId, item);
            processDeliveryPrice(userId, item);
        });

        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

    /**
     * 处理商品优惠的逻辑留给子类实现
     * @param userId
     * @param item
     */
    protected abstract void processCouponPrice(long userId, Item item);

    /**
     * 处理配送费的逻辑留给子类实现
     * @param userId
     * @param item
     */
    protected abstract void processDeliveryPrice(long userId, Item item);


    /*
涉及到设计模式类名可以以下单词作为前后缀 以体现类作用或者模式
Locker
Iterator
Extractor
Accessor
Validator
Formatter
Converter
Replacer
Comparer
Manager
Combiner
Parser
Encoder
Decoder
Importer
Exporter
Editor
Modifier
Evaluator
Locator
Creator
Initializer
Reader
Writer
Activator
Finder
Builder
Selector
Visitor
Loader
Descriptor
Generator
Adapter
Listener
Wrapper
Mapper
Binder
Invoker
Executor
Detector
Tracer
Decorator
Mapper
Resolver
Processor
Advisor
Dispatcher
Consumer
Producer
Publisher
Subscriber
Handler
Filter
Interceptor
Provider
Container

     */
}
