package com;

import com.model.Headphone;
import com.model.Laptop;
import com.repository.HeadphoneRepository;
import com.repository.LaptopRepository;
import com.repository.PhoneRepository;
import com.service.HeadphoneService;
import com.service.LaptopService;
import com.service.PhoneService;
import com.model.Phone;
import com.service.Container;


public class Main {
    private static final PhoneService PHONE_SERVICE = new PhoneService(new PhoneRepository());
    private static final LaptopService LAPTOP_SERVICE = new LaptopService(new LaptopRepository());
    private static final HeadphoneService HEADPHONE_SERVICE = new HeadphoneService(new HeadphoneRepository());
    public static Container container = new Container();


    public static void main(String[] args) {

        PHONE_SERVICE.createAndSave(2);
        LAPTOP_SERVICE.createAndSave(2);
        HEADPHONE_SERVICE.createAndSave(2);
        System.out.println("-----------------------------------");
        PHONE_SERVICE.printAll();
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
        System.out.println("-----------------------------------");
    }
}
