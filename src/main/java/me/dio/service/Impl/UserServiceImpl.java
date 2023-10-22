package me.dio.service.Impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import me.dio.service.exception.BusinessException;
import me.dio.service.exception.NotFoundException;

import java.util.List;

public class UserServiceImpl implements UserService {


    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return this.repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(User userCreate) {
        if(userCreate.getId().equals(repository.findById(userCreate.getId()))){
            throw new BusinessException("This account number already exists.");
        }
        return this.repository.save(userCreate);
    }

    @Override
    public User update(Long aLong, User user) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
