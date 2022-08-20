package com.service;

import com.annotation.Autowired;
import com.annotation.Singleton;
import com.model.product.Manufacturer;
import com.model.product.Phone;
import com.model.product.Product;
import com.repository.PhoneRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Singleton
public class PhoneService extends ProductService<Phone> {

    private final PhoneRepository repository;

    private static PhoneService instance;

    @Autowired
    public PhoneService(final PhoneRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public static PhoneService getInstance() {
        if (instance == null) {
            instance = new PhoneService(PhoneRepository.getInstance());
        }
        return instance;
    }

    public static PhoneService getInstance(final PhoneRepository repository) {
        if (instance == null) {
            instance = new PhoneService(repository);
        }
        return instance;
    }

    @Override
    public Phone createProduct() {
        return new Phone(
                Phone.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
                );
    }

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

    public int getTotalPriceForModel(final String model) {
        double totalPrice = 0;
        for (Phone phone : repository.findByModel(model)) {
            totalPrice += phone.getPrice();
        }
        return (int) totalPrice;
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
        Optional<Phone> optionalPhone = repository.findById(id);
        return optionalPhone.orElseGet(() -> createAndSavePhone());
    }

    public String mapPhoneToString(String id) {
        return repository.findById(id).map(p -> p.toString()).orElse("Not Found");
    }

    public void deleteIfPresentOrSave(Phone phone) {
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

    public Optional<Phone> findByIdOrCreateRandomOptional(String id) {
        return repository.findById(id).or(() -> Optional.of(createAndSavePhone()));
    }

    public void update(Phone phone) {
        repository.update(phone);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<Phone> getAll() {
        return repository.getAll();
    }

    public void savePhone(Phone phone){
        if(phone.getCount() == 0){
            phone.setCount(-1);
        }
        repository.save(phone);
    }

    Phone createAndSavePhone() {
        return new Phone("Title", 0, 0.0, "Model", Manufacturer.APPLE);
    }

    public boolean checkDetailExists(String detailToCheck) {
        return findAll()
                .stream()
                .flatMap(phone -> phone.getDetails().stream())
                .anyMatch(detail -> detail.equals(detailToCheck));
    }
}
