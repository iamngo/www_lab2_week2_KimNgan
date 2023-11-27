package vn.edu.ihu.fit.wwwlab2.services;


import vn.edu.ihu.fit.wwwlab2.repositories.CRUD;

import java.util.List;

public class OrderDetailService {
    private final CRUD crud;

    public OrderDetailService() {
        crud=new CRUD();
    }
    public <T> boolean create(T object) {
        return crud.create(object);
    }
    public <T> boolean update(T object) {
        return crud.update(object);
    }
    public <T> boolean delete(Class<T> clazz, long id) {
        return crud.delete(clazz, id);
    }
    public <T> T getByID(Class<T> clazz, long id) {
        return crud.getByID(clazz, id);
    }
    public <T> List<T> getAll(Class<T> clazz) {
        return crud.getAll(clazz);
    }
}
