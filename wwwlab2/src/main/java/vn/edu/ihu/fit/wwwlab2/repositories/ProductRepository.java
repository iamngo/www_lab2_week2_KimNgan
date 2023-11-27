package vn.edu.ihu.fit.wwwlab2.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import vn.edu.ihu.fit.wwwlab2.db.ConnectionDB;
import vn.edu.ihu.fit.wwwlab2.entities.InforProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final EntityManagerFactory entityManagerFactory;

    public ProductRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public List<InforProduct> getInfoProduct() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String queryString = "SELECT p.product_id, p.name, pm.path, pr.price " +
                    "FROM product AS p " +
                    "INNER JOIN product_image AS pm ON p.product_id = pm.product_id " +
                    "INNER JOIN product_price AS pr ON p.product_id = pr.product_id";

            Query query = entityManager.createNativeQuery(queryString);
            List<Object[]> results = query.getResultList();

            List<InforProduct> informationProducts = new ArrayList<>();
            for (Object[] result : results) {
                long productId = (long) result[0];
                String productName = (String) result[1];
                String imagePath = (String) result[2];
                double price = (double) result[3];

                InforProduct informationProduct = new InforProduct(productId, productName, imagePath, price);
                informationProducts.add(informationProduct);
            }

            return informationProducts;
        } finally {
            entityManager.close();
        }
    }
}
