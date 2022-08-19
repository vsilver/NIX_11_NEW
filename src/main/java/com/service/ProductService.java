package com.service;

import com.model.product.*;
import com.model.product.laptop.Laptop;
import com.model.product.laptop.LaptopType;
import com.repository.CrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ProductService<T extends Product>  {

    protected static final Random RANDOM = new Random();
    protected final CrudRepository<T> repository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private BiPredicate<T, Long> checkPriceExpensive = (product, currentPrice) -> product.getPrice() > currentPrice;

    protected ProductService(CrudRepository<T> repository){
        this.repository =  repository;
    }

    public void createAndSave(int count) {
        List<T> products = new LinkedList<>();
        if(count < 1){
            throw new IllegalArgumentException("count must be bigger than 0");
        }
        for (int i = 0; i < count; i++) {
            final T phone = createProduct();
            logger.info("Product {} Was Created", phone);
            products.add(phone);
        }
        repository.saveAll(products);
    }

    public abstract T createProduct();

    public void printAll() {
        for (T phone : repository.getAll()) {
            System.out.println(phone);
        }
    }

    public T findById(String id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void update(T product) {
        repository.update(product);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<T> findAll() {
        return repository.getAll();
    }

    public void save(T product) {
        if(product.getCount() == 0){
            product.setCount(-1);
        }
        repository.save(product);
    }

    public void getProductWithExpensivePrice(long currentPrice){
        repository.getAll()
                .stream()
                .filter(product -> checkPriceExpensive.test(product, currentPrice))
                .forEach(product -> System.out.println(product.getTitle() + " price: " + product.getPrice()));
    }
    public int countSumProducts() {
        return repository.getAll()
                .stream()
                .map(Product::getCount)
                .reduce(0, Integer::sum);
    }

    public Map<String, String> sortDistinctProduct(){
        return repository.getAll()
                .stream()
                .sorted(Comparator.comparing(Product::getTitle))
                .distinct()
                .collect(Collectors.toMap(Product::getId, product -> product.getClass().getSimpleName(), (o1, o2) -> o2));
    }

    /*public LongSummaryStatistics getPriceStatistic() {
        return repository.getAll()
                .stream()
                .mapToLong(Product::getPrice)
                .summaryStatistics();
    }*/

    public Product mapProduct(Map<String, Object> fields) {
        Function<Map<String, Object>, Product> mapToProduct = (map) -> {
            Object productType = map.get("productType");
            if (productType instanceof ProductType type) {
                return switch (type) {
                    case PHONE -> new Phone(map.getOrDefault("title", "Default").toString(),
                            (Integer) map.getOrDefault("count", 0),
                            (Long) map.getOrDefault("price", 0L),
                            map.getOrDefault("model", "Default").toString(),
                            Manufacturer.valueOf(map.getOrDefault("manufacturer", Manufacturer.SAMSUNG).toString()));
                    case LAPTOP -> new Laptop.Builder((Long) map.getOrDefault("price", 0L),
                            LaptopType.valueOf(map.getOrDefault("LaptopType", LaptopType.AMD).toString()))
                            .setTittle(map.getOrDefault("title", "Default").toString())
                            .setCount((Integer) map.getOrDefault("count", 0))
                            .build();
                    case HEADPHONE -> new Headphone(map.getOrDefault("title", "Default").toString(),
                            (Integer) map.getOrDefault("count", 0),
                            (Long) map.getOrDefault("price", 0L),
                            map.getOrDefault("model", "Default").toString(),
                            Manufacturer.valueOf(map.getOrDefault("manufacturer", Manufacturer.SAMSUNG).toString()));
                    case TV -> new TV(map.getOrDefault("title", "Default").toString(),
                            (Integer) map.getOrDefault("count", 0),
                            (Long) map.getOrDefault("price", 0L),
                            map.getOrDefault("model", "Default").toString(),
                            Manufacturer.valueOf(map.getOrDefault("manufacturer", Manufacturer.SAMSUNG).toString()));
                };
            }
            throw new IllegalArgumentException();
        };
        return mapToProduct.apply(fields);
    }
}
