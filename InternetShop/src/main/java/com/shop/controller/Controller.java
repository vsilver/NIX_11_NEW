package com.shop.controller;

import com.shop.model.invoice.Invoice;
import com.shop.model.invoice.InvoiceType;
import com.shop.model.product.Product;
import com.shop.model.product.ProductType;
import com.shop.service.PersonService;
import com.shop.service.ShopService;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;

public class Controller {
    private LongPredicate longPredicate;
    private final BufferedReader reader;
    private final ShopService shopService;
    private final PersonService personService;

    private final List<Invoice> invoices;

    public Controller() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        shopService = new ShopService(new Random());
        personService = new PersonService();
        invoices = new ArrayList<>();
    }

    public void run() throws IOException {
        String line = "-".repeat(30);
        System.out.println("Day report");
        setPredicate();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("shop.csv"))))) {
            shopService.createProducts(bufferedReader);
            for (int i = 0; i < 15; i++) {
                invoices.add(shopService.generateRandomInvoice(longPredicate, personService.generateRandomCustomer()));
            }
        }
        System.out.println(line);
        System.out.println("Quantity of sell Telephones");
        displayCountOfSoldProducts(ProductType.TELEPHONE);
        System.out.println(line);
        System.out.println("Quantity of sell Televisions");
        displayCountOfSoldProducts(ProductType.TELEVISION);
        System.out.println(line);

        displayLowestSumInvoice();
        System.out.println(line);

        displayAllInvoicesSum();
        System.out.println(line);

        displayQuantityInvoiceType(InvoiceType.RETAIL);
        System.out.println(line);

        displayInvoicesWithOnlyOneProductType();
        System.out.println(line);

        displayFirstThreeInvoices();
        System.out.println(line);

        displayInvoicesUnderTeenAge();
        System.out.println(line);

        displaySortedInvoices();
        System.out.println(line);
    }

    private void setPredicate() throws IOException {
        String line;
        while (true) {
            System.out.println("Set invoice limit which configure invoice type");
            line = reader.readLine();
            if (StringUtils.isNumeric(line)) {
                long limit = Long.parseLong(line);
                longPredicate = sum -> sum > limit;
                return;
            }
            System.out.println("Wrong input");
        }
    }

    private void displayCountOfSoldProducts(ProductType productType) {
        //System.out.print(productType.getName() + "s sold: ");
        long count = invoices.stream()
                .flatMap(invoice -> invoice.getProductList().stream())
                .filter(product -> product.getProductType().equals(productType))
                .count();
        System.out.println(count);
    }

    private void displayLowestSumInvoice() {
        System.out.print("Lowest invoice: ");
        invoices.stream()
                .collect(Collectors.toMap(k -> k.getProductList()
                                .stream()
                                .mapToLong(Product::getPrice)
                                .sum(),
                        Invoice::getCustomer,
                        (p1, p2) -> p1,
                        TreeMap::new))
                .entrySet()
                .stream()
                .findFirst()
                .ifPresent(o -> System.out.println(o.getValue() + " sum: " + o.getKey()));
    }

    private void displayFirstThreeInvoices() {
        System.out.println("First 3 invoices: ");
        invoices.stream()
                .sorted(Comparator.comparing(Invoice::getCreatingTime))
                .limit(3)
                .forEach(System.out::println);
    }

    private void displayAllInvoicesSum() {
        long invoicesSum = shopService.getInvoiceSum(invoices.stream()
                .flatMap(invoice -> invoice.getProductList().stream())
                .toList());
        System.out.println("Sum of all invoices = " + invoicesSum);

    }

    private void displayQuantityInvoiceType(InvoiceType type) {
        long count = invoices.stream()
                .filter(invoice -> invoice.getType().equals(type))
                .count();
        System.out.println(type.toString() + " invoices = " + count);
    }

    private void displayInvoicesWithOnlyOneProductType() {
        System.out.println("Invoices with same product type:");
        List<Invoice> onlyOneType = new ArrayList<>();
        invoices.forEach(invoice -> {
            if (checkContainsOnlyOneProductType(invoice.getProductList(), ProductType.TELEVISION)) {
                onlyOneType.add(invoice);
            }
            if (checkContainsOnlyOneProductType(invoice.getProductList(), ProductType.TELEPHONE)) {
                onlyOneType.add(invoice);
            }
        });

        if (onlyOneType.isEmpty()) {
            System.out.println("No one invoice have products of same type");
        } else {
            onlyOneType.forEach(System.out::println);
        }
    }

    private boolean checkContainsOnlyOneProductType(List<Product> products, ProductType productType) {
        return products.stream()
                .allMatch(product -> product.getProductType().equals(productType));
    }

    private void displayInvoicesUnderTeenAge() {
        System.out.println("Invoices of persons under 18 age:");
        List<Invoice> invoicesUnder18Age = invoices.stream()
                .filter(invoice -> invoice.getCustomer().getAge() < 18)
                .toList();
        if (!invoicesUnder18Age.isEmpty()) {
            invoicesUnder18Age.forEach(invoice -> {
                invoice.setType(InvoiceType.LOW_AGE);
                System.out.println(invoice);
            });
        } else {
            System.out.println("0 invoices of persons under 18 age found");
        }
    }

    private void displaySortedInvoices() {
        System.out.println("Sorted invoices");
        Comparator<Invoice> compareByAge = Comparator.comparing(invoice -> invoice.getCustomer().getAge(), Comparator.reverseOrder());
        Comparator<Invoice> compareByProductsListSize = Comparator.comparing(invoice -> invoice.getProductList().size());
        Comparator<Invoice> compareByInvoiceSum = Comparator.comparing(invoice -> shopService.getInvoiceSum(invoice.getProductList()));

        invoices.stream()
                .sorted(compareByAge
                        .thenComparing(compareByProductsListSize)
                        .thenComparing(compareByInvoiceSum))
                .forEach(System.out::println);

    }

}
