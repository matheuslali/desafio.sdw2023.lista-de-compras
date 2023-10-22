package me.dio.service.Impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import me.dio.service.exception.BusinessException;
import me.dio.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(User userCreate) {
        if(userCreate.getId().equals(userRepository.findById(userCreate.getId()))){
            throw new BusinessException("This id number already exists.");
        }
        return this.userRepository.save(userCreate);
    }

    @Override
    public User update(Long id, User userUpdate) {
        User dbUser = this.findById(id);
        if(!dbUser.getId().equals(userUpdate.getId())){
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(userUpdate.getName());
        dbUser.setEmail(userUpdate.getEmail());

        return this.userRepository.save(dbUser);
    }

    @Override
    public void delete(Long id) {
    User dbUser = findById(id);
    this.userRepository.delete(dbUser);
    }
}
