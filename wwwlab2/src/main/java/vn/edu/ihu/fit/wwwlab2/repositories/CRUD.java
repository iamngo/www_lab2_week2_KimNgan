package vn.edu.ihu.fit.wwwlab2.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import vn.edu.ihu.fit.wwwlab2.db.ConnectionDB;

import java.util.List;

public class CRUD {
    private final EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public CRUD() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public <T> boolean create(T object) {
        entityManager=entityManagerFactory.createEntityManager();
        transaction=null;
        try {
            transaction=entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return false;
    }

    public <T> boolean update(T object) {
        entityManager=entityManagerFactory.createEntityManager();
        transaction=null;
        try {
            transaction=entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return false;
    }

    public <T> boolean delete(Class<T> clazz, long id) {
        entityManager=entityManagerFactory.createEntityManager();
        transaction=null;
        try {
            transaction=entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(clazz, id));
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return false;
    }

    public <T> T getByID(Class<T> clazz, long id) {
        entityManager=entityManagerFactory.createEntityManager();
        transaction=null;
        try {
            transaction=entityManager.getTransaction();
            transaction.begin();
            T object = entityManager.find(clazz, id);
            transaction.commit();
            return object;
        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return null;
    }

    public <T> List<T> getAll(Class<T> clazz) {
        entityManager=entityManagerFactory.createEntityManager();
        transaction=null;
        try {
            transaction=entityManager.getTransaction();
            transaction.begin();
            List<T> list = entityManager.createQuery("From " + clazz.getName(), clazz).getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return null;
    }
}
