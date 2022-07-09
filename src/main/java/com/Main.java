package com;

import com.model.Laptop;
import com.repository.HeadphoneRepository;
import com.repository.LaptopRepository;
import com.repository.PhoneRepository;
import com.service.HeadphoneService;
import com.service.LaptopService;
import com.service.PhoneService;
import com.model.Phone;


public class Main {
    private static final PhoneService PHONE_SERVICE = new PhoneService(new PhoneRepository());
    private static final LaptopService LAPTOP_SERVICE = new LaptopService(new LaptopRepository());
    private static final HeadphoneService HEADPHONE_SERVICE = new HeadphoneService(new HeadphoneRepository());


    public static void main(String[] args) {
        PHONE_SERVICE.createAndSavePhones(2);
        LAPTOP_SERVICE.createAndSaveLaptops(1);
        HEADPHONE_SERVICE.createAndSaveHeadphones(1);
        System.out.println("-----------------------------------");
        Phone phone = (Phone) PHONE_SERVICE.getAll().get(1);
        Laptop laptop = (Laptop) LAPTOP_SERVICE.getAll().get(0);
        PHONE_SERVICE.printAll();
        LAPTOP_SERVICE.printAll();
        HEADPHONE_SERVICE.printAll();
        System.out.println("All types of products are created");
        System.out.println("-----------------------------------");
        laptop.setPrice(2000);
        PHONE_SERVICE.update(laptop);
        LAPTOP_SERVICE.printAll();
        System.out.println("The price of laptop was changed to 2000");
        System.out.println("-----------------------------------");
        PHONE_SERVICE.delete(phone.getId());
        PHONE_SERVICE.printAll();
        System.out.println("One of the two phones was deleted");
        System.out.println("-----------------------------------");
        // TODO: 02/07/22  add tests
    }
}
