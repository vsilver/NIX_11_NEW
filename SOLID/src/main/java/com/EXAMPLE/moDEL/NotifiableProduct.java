package com.EXAMPLE.moDEL;

public class NotifiableProduct extends Product{

    protected String channel;

    public NotifiableProduct(long id, String title, double price, boolean available, String channel) {
        super(id, title, price, available);
        this.channel = channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String generateAddressForNotification() {
        return "somerandommail@gmail.com";
    }

    @Override
    public String getBasicInfo() {
        return "NotifiableProduct{" +
                "channel='" + channel + '\'' +
                ", id=" + id +
                ", available=" + available +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int getAmountInBundle() {
        throw new UnsupportedOperationException("Product is not a bundle");
    }
}
