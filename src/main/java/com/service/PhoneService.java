package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Product;
import com.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PhoneService {
    private static final Random RANDOM = new Random();
    private final PhoneRepository repository;
    private final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    public PhoneService(PhoneRepository repository){
        this.repository = repository;
    }

    public void createAndSavePhones(int count) {
        List<Product> phones = new LinkedList<>();
        if(count < 1){
            throw new IllegalArgumentException("count must be bigger than 0");
        }
        for (int i = 0; i < count; i++) {
            final Phone phone = new Phone(
                    "Title-" + RANDOM.nextInt(1000),
                    RANDOM.nextInt(500),
                    RANDOM.nextDouble(1000.0),
                    "Model-" + RANDOM.nextInt(10),
                    getRandomManufacturer()

            );
            logger.info("{} Was Created", phone);
            phones.add(phone);
        }
        repository.saveAll(phones);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void printAll() {
        for (Product phone : repository.getAll()) {
            System.out.println(phone);
        }
    }

    public void printIfPresent(String id) {
        final Optional<Product> phoneOptional = repository.findById(id);
        phoneOptional.ifPresent(phone -> {
            System.out.println(phone);
        });
    }

    public void printOrCreatDefault(String id) {
        final Phone phone1 = (Phone) repository.findById(id)
                .orElseGet(() -> createAndSavePhone());
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final Phone phone2 = (Phone) repository.findById("123")
                .orElseGet(() -> createAndSavePhone());
        System.out.println(phone2);
    }

    public void printOrGetDefault(String id) {
        final Phone phone1 = (Phone) repository.findById(id)
                .orElse(createAndSavePhone());
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final Phone phone2 = (Phone) repository.findById("123")
                .orElse(createAndSavePhone());
        System.out.println(phone2);
    }

    public void mapPhoneToString(String id) {
        final String phone1 = repository.findById(id)
                .map(p -> p.toString())
                .orElse("Phone not found");
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final String phone2 = repository.findById("112")
                .map(p -> p.toString())
                .orElse("Phone not found");
        System.out.println(phone2);
    }

    public void printOrPrintDefault(String id) {
        repository.findById(id).ifPresentOrElse(
                phone -> {
                    System.out.println(phone);
                },
                () -> {
                    System.out.println(createAndSavePhone());
                }
        );

        System.out.println("~".repeat(5));

        repository.findById("112").ifPresentOrElse(
                phone -> {
                    System.out.println(phone);
                },
                () -> {
                    System.out.println(createAndSavePhone());
                }
        );
    }

    public void checksPhoneLessThen(String id, int count) {
        repository.findById(id)
                .filter(phone -> phone.getCount() <= count)
                .ifPresentOrElse(
                        phone -> {
                            System.out.println(phone);
                        },
                        () -> {
                            System.out.println("Phone with count " + count + " not found");
                        }
                );
    }

    public void printPhoneOrElseThrowException(String id) {
        final Phone phone1 = (Phone) repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phone with id " + id + " not found"));
        System.out.println(phone1);

        System.out.println("~".repeat(5));

        final Phone phone2 = (Phone) repository.findById("123")
                .orElseThrow(() -> new IllegalArgumentException("Phone with id " + id + " not found"));
        System.out.println(phone2);
    }

    public void printPhone(String id) {
        repository.findById(id).or(() -> Optional.of(createAndSavePhone()))
                .ifPresent(phone -> System.out.println(phone));

        System.out.println("~".repeat(5));

        repository.findById("123").or(() -> Optional.of(createAndSavePhone()))
                .ifPresent(phone -> System.out.println(phone));
    }

    public void update(Product phone) {
        repository.update(phone);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    public void savePhone(Product phone){
        if(phone.getCount() == 0){
            phone.setCount(-1);
        }
        repository.save(phone);
    }

    public Optional<Product> findById(String id) {
        return repository.findById(id);
    }

    private Phone createAndSavePhone() {
        return new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
    }
}
