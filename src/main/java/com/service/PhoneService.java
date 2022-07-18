package com.service;

import com.model.Manufacturer;
import com.model.Phone;
import com.model.Product;
import com.repository.CrudRepository;
import com.repository.LaptopRepository;
import com.repository.PhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class PhoneService extends ProductService<Phone> {
    //private static final Random RANDOM = new Random();
    //private final PhoneRepository repository;
   // private final Logger logger = LoggerFactory.getLogger(LaptopService.class);

    public PhoneService(CrudRepository<Phone> repository){
        super(repository);
        //this.repository = repository;
    }

    @Override
    protected Phone createProduct() {
        return new Phone(
                Phone.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
        );
    }

    /*public void createAndSavePhones(int count) {
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
    }*/

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public int getTotalPriceFor(final String id) {
        final AtomicInteger totalPrice = new AtomicInteger(0);
        repository.findById(id).ifPresentOrElse(
                phone -> totalPrice.set((int) (phone.getCount() * phone.getPrice())),
                () -> totalPrice.set(-1)
        );
        return totalPrice.get();
    }

    public Phone getOrCreat(final String id) {
        return repository.findById(id).orElseGet(() -> {
            final Phone phone = createProduct();
            repository.save(phone);
            return phone;
        });
    }

    public void printAll() {
        for (Phone phone : repository.getAll()) {
            System.out.println(phone);
        }
    }

    public void updateIfPresent(Product phone) {
        repository.findById(phone.getId()).ifPresent(updatedPhone -> repository.update(updatedPhone));
    }

    public Product findByIdOrCreateDefault(String id) {
        return repository.findById(id).orElse(new Phone("", 0, 0, "Model", Manufacturer.APPLE));
    }

    public Product findByIdOrCreateRandom(String id) {
        Optional<Product> optionalPhone = repository.findById(id);
        return optionalPhone.orElseGet(() -> createAndSavePhone());
    }

    public String mapPhoneToString(String id) {
        return repository.findById(id).map(p -> p.toString()).orElse("Not Found");
    }

    public void deleteIfPresentOrSave(Product phone) {
        repository.findById(phone.getId())
                .ifPresentOrElse(foundedBall -> repository.delete(foundedBall.getId()),
                        () -> repository.save(phone));
    }

    public void deletePhoneMoreThen(String id, int count) {
        repository.findById(id)
                .filter(phone -> phone.getCount() >= count)
                .ifPresent(foundedBall -> repository.delete(foundedBall.getId()));
    }

    public Phone findById(String id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Phone not found"));
    }

    public Optional<Product> findByIdOrCreateRandomOptional(String id) {
        return repository.findById(id).or(() -> Optional.of(createAndSavePhone()));
    }

    public void update(Phone phone) {
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

    Phone createAndSavePhone() {
        return new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
    }
}
