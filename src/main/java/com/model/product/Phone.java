package com.model.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Phone extends Product implements Comparable<Phone>{
    private final String model;
    private final Manufacturer manufacturer;
    private List<String> details = new ArrayList<>();
    private OperationSystem operationSystem;
    private LocalDateTime creatingDate;
    private String currency;

    public Phone(String title, int count, double price, String model, Manufacturer manufacturer) {
        super(title, count, price, ProductType.PHONE);
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public Phone(String title, int count, long price, String model, Manufacturer manufacturer, List<String> details) {
        super(title, count, price, ProductType.PHONE);
        this.model = model;
        this.manufacturer = manufacturer;
        this.details = details;
    }

    public Phone(String title, int count, long price, String model, Manufacturer manufacturer, LocalDateTime creatingDate, String currency, OperationSystem operationSystem) {
        super(title, count, price, ProductType.LAPTOP);
        this.model = model;
        this.manufacturer = manufacturer;
        this.creatingDate = creatingDate;
        this.currency = currency;
        this.operationSystem = operationSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "manufacturer=" + manufacturer +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", model=" + model +
                ", currency=" + currency +
                ", operationSystem=" + operationSystem +
                ", creatingDate=" + creatingDate +
                '}';
    }

    @Override
    public int compareTo(Phone o) {
        return getId().compareTo(o.getId());
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
