package vn.edu.ihu.fit.wwwlab2.entities;

import java.math.BigDecimal;

public class ResponseOrderByDateBetween {
    private long orderID;
    private String name;
    private String orderDate;
    private double prices;
    private double quantity;

    public ResponseOrderByDateBetween(long orderID, String name, String orderDate, double prices, double quantity) {
        this.orderID = orderID;
        this.name = name;
        this.orderDate = orderDate;
        this.prices = prices;
        this.quantity = quantity;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ResponseOrderByDateBetween{" +
                "orderID=" + orderID +
                ", name='" + name + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", prices=" + prices +
                ", quantity=" + quantity +
                '}';
    }
}
