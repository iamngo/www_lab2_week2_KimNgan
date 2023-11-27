package vn.edu.ihu.fit.wwwlab2.models;

import jakarta.persistence.*;
import vn.edu.ihu.fit.wwwlab2.enums.ProductStatus;

import java.io.Serializable;

@Entity
@Table(name="product")
public class Product implements Serializable {
    @Id
    @Column(name = "product_id", columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(columnDefinition = "VARCHAR(250)")
    private String description;
    @Column(columnDefinition = "VARCHAR(100)")
    private String manufacturer;
    @Column(columnDefinition = "VARCHAR(150)")
    private String name;
    @Column(columnDefinition = "INT(11)")
    private ProductStatus status;
    @Column(columnDefinition = "VARCHAR(25)")
    private String unit;

    public Product(long productId) {
        this.productId = productId;
    }

    public Product() {
    }

    public Product(long productId, String description, String manufacturer, String name, ProductStatus status, String unit) {
        this.productId = productId;
        this.description = description;
        this.manufacturer = manufacturer;
        this.name = name;
        this.status = status;
        this.unit = unit;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", unit='" + unit + '\'' +
                '}';
    }
}
