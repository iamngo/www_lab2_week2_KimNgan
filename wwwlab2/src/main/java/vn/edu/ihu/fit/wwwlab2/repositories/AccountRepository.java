package vn.edu.ihu.fit.wwwlab2.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.edu.ihu.fit.wwwlab2.db.ConnectionDB;
import vn.edu.ihu.fit.wwwlab2.entities.Login;
import vn.edu.ihu.fit.wwwlab2.models.Account;

public class AccountRepository {
    private final EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;


    public AccountRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }
    public Account login(Login login){
        Account account=null;
        entityManager=entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT account_id, phone_number,`password`, role FROM accounts WHERE phone_number = ? AND `password` = ?");
            query.setParameter(1, login.getPhoneNumber());
            query.setParameter(2, login.getPassword());
            Object[] object = (Object[]) query.getSingleResult();
            account = new Account((Long) object[0], object[1]+"", object[2]+"", object[3]+"");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return account;
    }

    public boolean checkAccountExist(String phoneNumber) {
        entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM accounts WHERE phone_Number = ?", Account.class);
            query.setParameter(1, phoneNumber);
            Account account = (Account) query.getSingleResult();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public Account getAccountByPhone(String phoneNumber) {
        entityManager = entityManagerFactory.createEntityManager();
        transaction=null;
        try {
            transaction=entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE phoneNumber LIKE :phone_Number", Account.class);
            query.setParameter("phone_Number", "%"+ phoneNumber +"%");
            Account account = (Account) query.getSingleResult();
            transaction.commit();
            return account;
        } catch (Exception e) {
            if(transaction!=null)
                transaction.rollback();
           e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return null;
    }

}
