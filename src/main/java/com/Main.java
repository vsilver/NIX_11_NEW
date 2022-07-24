package com;

import com.controller.Controller;
import com.model.Order;
import com.model.ProductComparator;
import com.model.product.Phone;
import com.model.product.Product;
import com.model.product.TV;
import com.repository.*;
import com.service.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Main {
    private static final PhoneService PHONE_SERVICE = new PhoneService(new PhoneRepository());
    private static final LaptopService LAPTOP_SERVICE = new LaptopService(new LaptopRepository());
    private static final HeadphoneService HEADPHONE_SERVICE = new HeadphoneService(new HeadphoneRepository());
    private static final TVService TV_SERVICE = new TVService(new TVRepository());
    private static final Container container = new Container();
    private static final OrderService ORDER_SERVICE = new OrderService();
    private static final ProductVersionService PRODUCT_VERSION_SERVICE = new ProductVersionService<>();

    public static void main(String[] args) {

        Controller.run();

        /*PHONE_SERVICE.createAndSave(2);
        LAPTOP_SERVICE.createAndSave(2);
        HEADPHONE_SERVICE.createAndSave(2);
        TV_SERVICE.createAndSave(2);*/

        /*PRODUCT_VERSION_SERVICE.addFirst(TV_SERVICE.createProduct(), 1);
        PRODUCT_VERSION_SERVICE.addFirst(TV_SERVICE.createProduct(), 10);
        PRODUCT_VERSION_SERVICE.addFirst(TV_SERVICE.createProduct(), 100);
        PRODUCT_VERSION_SERVICE.addFirst(TV_SERVICE.createProduct(), 1000);
        PRODUCT_VERSION_SERVICE.addFirst(TV_SERVICE.createProduct(), 10000);
        PRODUCT_VERSION_SERVICE.addFirst(TV_SERVICE.createProduct(), 100000);

        System.out.println("ForEach: ");
        PRODUCT_VERSION_SERVICE.forEach(System.out::println);
        System.out.println("-----------------------------------");
        System.out.println("Find by version 100: \n" + PRODUCT_VERSION_SERVICE.findByVersion(100) + "\n");
        System.out.println("-----------------------------------");
        System.out.println("Delete by version 1000: \n" + PRODUCT_VERSION_SERVICE.findByVersion(1000) + "\n");
        PRODUCT_VERSION_SERVICE.deleteByVersion(1000);
        System.out.println("-----------------------------------");
        System.out.println("ForEach: ");
        PRODUCT_VERSION_SERVICE.forEach(System.out::println);
        System.out.println("-----------------------------------");
        System.out.println("Change by version 10000: \n" + PRODUCT_VERSION_SERVICE.findByVersion(10000) + "\n");
        TV tv = TV_SERVICE.createProduct();
        tv.setCount(2000);
        System.out.println("Set by version 10000 from " + PRODUCT_VERSION_SERVICE.findByVersion(10000) + " to: " + tv);
        PRODUCT_VERSION_SERVICE.setByVersion(10000, tv);
        System.out.println("-----------------------------------");
        System.out.println("Versions count: " + PRODUCT_VERSION_SERVICE.getVersionCount());
        System.out.println("-----------------------------------");
        System.out.println("FirstDateVersion: " + PRODUCT_VERSION_SERVICE.getFirstVersionDate());
        System.out.println("LastDateVersion: " + PRODUCT_VERSION_SERVICE.getLastVersionDate());
        System.out.println("-----------------------------------");
        System.out.println("Comparator: ");
        TV_SERVICE.createAndSave(15);
        Set<Product> tvSet = new TreeSet<>(new ProductComparator<>());
        tvSet.addAll(TV_SERVICE.findAll());
        tvSet.forEach(System.out::println);
        System.out.println("-----------------------------------");*/


        /*final List<TV> tvs = TV_SERVICE.findAll();
        final Order order = ORDER_SERVICE.creatOrder(tvs);
        System.out.println(order);
        System.out.println("-----------------------------------");
        final List<Phone> phones = PHONE_SERVICE.findAll();
        OrderService.addProducts(order, phones);
        System.out.println(order);
        System.out.println("-----------------------------------");
        final Product remove = ORDER_SERVICE.remove(order, 1);
        System.out.println(order);
        System.out.println("-----------------------------------");
        ORDER_SERVICE.addProduct(order, 0, remove);
        System.out.println(order);
        System.out.println("-----------------------------------");*/
        /*PHONE_SERVICE.printAll();
        LAPTOP_SERVICE.printAll();
        HEADPHONE_SERVICE.printAll();
        System.out.println("All types of products are created");
        System.out.println("-----------------------------------");
        Phone phone = PHONE_SERVICE.getAll().get(0);
        Laptop laptop = LAPTOP_SERVICE.getAll().get(0);
        container.getDiscount(phone);
        container.increaseCount(laptop, 10);
        container.showProduct(phone);
        container.showProduct(laptop);
        System.out.println("-----------------------------------");*/
    }
}
