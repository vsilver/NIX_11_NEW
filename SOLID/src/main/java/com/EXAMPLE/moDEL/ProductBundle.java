package com.EXAMPLE.moDEL;

import lombok.Data;
import lombok.Setter;

public class ProductBundle extends NotifiableProduct{

    protected int amount;

    public ProductBundle(long id, String title, double price, boolean available, String channel, int amount) {
        super(id, title, price, available, channel);
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String generateAddressForNotification() {
        throw new UnsupportedOperationException("Bundle can't be notified");
    }

    @Override
    public int getAmountInBundle() {
        return amount;
    }

    @Override
    public String getBasicInfo() {
        return "ProductBundle{" +
                "channel='" + channel + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", amountInBundle=" + amount +
                '}';
    }
}
