package vn.edu.ihu.fit.wwwlab2.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import vn.edu.ihu.fit.wwwlab2.db.ConnectionDB;
import vn.edu.ihu.fit.wwwlab2.entities.RequestOrderDate;
import vn.edu.ihu.fit.wwwlab2.entities.ResponseOrderByDateBetween;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final EntityManagerFactory entityManagerFactory;

    public OrderRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }
    public List<ResponseOrderByDateBetween> getOrderByDateBetWeen(RequestOrderDate requestOrderDate){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ResponseOrderByDateBetween> responseOrderByDateBetweens = new ArrayList<>();
        List<Object[]> objects = null;
        try {
            if(requestOrderDate.getEmpID() == 0){
                Query query = entityManager.createNativeQuery("SELECT od.orderDetailId, p.name, o.order_date, SUM(od.price) AS prices, SUM(od.quantity) AS quantity FROM orders AS o \n" +
                        "INNER JOIN order_detail AS od ON o.order_id = od.order_id\n" +
                        "INNER JOIN product AS p ON p.product_id = od.product_id\n" +
                        "WHERE order_date BETWEEN ? AND ?\n" +
                        "GROUP BY p.name, o.order_date ORDER BY o.order_date");
                query.setParameter(1, requestOrderDate.getFromDate());
                query.setParameter(2, requestOrderDate.getToDate());
                objects = query.getResultList();
                for (Object[] o : objects){
                    ResponseOrderByDateBetween responseOrderByDateBetween = new ResponseOrderByDateBetween((long)o[0],o[1]+"",o[2]+"",(double)o[3],(double) o[4]);
                    responseOrderByDateBetweens.add(responseOrderByDateBetween);
                }
            }
            else{
                Query query = entityManager.createNativeQuery("SELECT od.orderDetailId, p.name, o.order_date, SUM(od.price) AS prices, SUM(od.quantity) AS quantity, e.emp_id FROM orders AS o \n" +
                        "INNER JOIN order_detail AS od ON o.order_id = od.order_id\n" +
                        "INNER JOIN product AS p ON p.product_id = od.product_id\n" +
                        "INNER JOIN employee AS e ON o.emp_id = e.emp_id\n" +
                        "WHERE e.emp_id = ? AND order_date BETWEEN ? AND ?\n" +
                        "GROUP BY p.name, o.order_date\n" +
                        "ORDER BY o.order_date");
                query.setParameter(1, requestOrderDate.getEmpID());
                query.setParameter(2, requestOrderDate.getFromDate());
                query.setParameter(3, requestOrderDate.getToDate());
                objects = query.getResultList();

                for (Object[] o : objects){
                    ResponseOrderByDateBetween responseOrderByDateBetween = new ResponseOrderByDateBetween((long)o[0],o[1]+"",o[2]+"",(double)o[3],(double) o[4]);
                    responseOrderByDateBetweens.add(responseOrderByDateBetween);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return responseOrderByDateBetweens;
    }
}
