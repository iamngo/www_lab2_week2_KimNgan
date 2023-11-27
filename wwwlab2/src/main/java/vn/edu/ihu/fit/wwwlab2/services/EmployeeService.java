package vn.edu.ihu.fit.wwwlab2.services;


import vn.edu.ihu.fit.wwwlab2.enums.EmployeeStatus;
import vn.edu.ihu.fit.wwwlab2.models.Employee;
import vn.edu.ihu.fit.wwwlab2.repositories.CRUD;

import java.util.List;

public class EmployeeService {
    private final CRUD crud;

    public EmployeeService() {
        crud=new CRUD();
    }
    public <T> boolean create(T object) {
        return crud.create(object);
    }
    public <T> boolean update(T object) {
        return crud.update(object);
    }
    public boolean delete(long id) {
        return crud.delete(Employee.class, id);
    }
    public <T> T getByID(Class<T> clazz, long id) {
        return crud.getByID(clazz, id);
    }
    public <T> List<T> getAll(Class<T> clazz) {
        return crud.getAll(clazz);
    }
}
