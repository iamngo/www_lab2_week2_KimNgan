package vn.edu.ihu.fit.wwwlab2.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderDetailId;
    @Column(columnDefinition = "VARCHAR(255)")
    private String note;
    private double price;
    private double quantity;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderDetail(String note) {
        this.note = note;
    }

    public OrderDetail(long orderDetailId, String note, double price, double quantity, Order order, Product product) {
        this.orderDetailId = orderDetailId;
        this.note = note;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public OrderDetail(String note, double price, double quantity, Order order, Product product) {
        this.note = note;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public OrderDetail() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", note='" + note + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}
