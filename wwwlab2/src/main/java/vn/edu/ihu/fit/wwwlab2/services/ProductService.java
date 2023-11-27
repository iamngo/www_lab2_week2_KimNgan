package vn.edu.ihu.fit.wwwlab2.services;


import vn.edu.ihu.fit.wwwlab2.entities.InforProduct;
import vn.edu.ihu.fit.wwwlab2.repositories.CRUD;
import vn.edu.ihu.fit.wwwlab2.repositories.ProductRepository;

import java.util.List;

public class ProductService {
    private final CRUD crud;
    private final ProductRepository productRepository;

    public ProductService() {
        crud=new CRUD();
        productRepository=new ProductRepository();
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
    public List<InforProduct> getInfoProduct(){ return  productRepository.getInfoProduct();}
}
