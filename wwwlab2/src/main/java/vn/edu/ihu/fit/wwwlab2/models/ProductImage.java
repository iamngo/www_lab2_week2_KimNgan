package vn.edu.ihu.fit.wwwlab2.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_image")
public class ProductImage implements Serializable {
    @Id
    @Column(name = "image_id", columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long imageId;
    @Column(name = "alternative", columnDefinition = "VARCHAR(250)")
    private String alterNative;
    @Column(columnDefinition = "VARCHAR(250)")
    private String path;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(long imageId) {
        this.imageId = imageId;
    }

    public ProductImage(long imageId, String alterNative, String path, Product product) {
        this.imageId = imageId;
        this.alterNative = alterNative;
        this.path = path;
        this.product = product;
    }

    public ProductImage() {
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getAlterNative() {
        return alterNative;
    }

    public void setAlterNative(String alterNative) {
        this.alterNative = alterNative;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "imageId=" + imageId +
                ", alterNative='" + alterNative + '\'' +
                ", path='" + path + '\'' +
                ", product=" + product +
                '}';
    }
}
