package vn.edu.ihu.fit.wwwlab2.db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionDB {
    private static ConnectionDB instance=null;
    private final EntityManagerFactory entityManagerFactory;

    public ConnectionDB() {
        entityManagerFactory= Persistence.createEntityManagerFactory("kimnganlab2");
    }
    public static ConnectionDB getInstance(){
        if (instance==null)
            instance=new ConnectionDB();
        return instance;
    }
    public EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }
}
