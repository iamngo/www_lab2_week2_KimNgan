package vn.edu.ihu.fit.wwwlab2.services;


import vn.edu.ihu.fit.wwwlab2.entities.Login;
import vn.edu.ihu.fit.wwwlab2.models.Account;
import vn.edu.ihu.fit.wwwlab2.repositories.AccountRepository;
import vn.edu.ihu.fit.wwwlab2.repositories.CRUD;

import java.util.List;

public class AccountService {
    private final CRUD crud;
    private final AccountRepository accountRepository;

    public AccountService() {
        crud=new CRUD();
        accountRepository=new AccountRepository();

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
    public boolean checkAccountExist(String phoneNumber){ return accountRepository.checkAccountExist(phoneNumber);}
    public Account login(Login login){
        return accountRepository.login(login);
    }
    public Account getAccountByPhone(String phoneNumber){

        return accountRepository.getAccountByPhone(phoneNumber);
    }
}
