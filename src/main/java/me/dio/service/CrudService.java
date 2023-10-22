package me.dio.service;

import me.dio.domain.model.User;

import java.util.List;

public interface CrudService <ID, T>{
    List<T> findAll();
    T findById(ID id);
    T create(T t);
    T update(ID id, T t);
    void delete(ID id);

}
