package com.tutorial.crud.service;

import com.tutorial.crud.entity.User;
import com.tutorial.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);

    }
    public boolean existsByUserName(String username){
        return  userRepository.existsByUserName(username);
    }
    public boolean existByEmail(String email){
        return  userRepository.existsByEmail(email);
    }
    public void save(User user){
        userRepository.save(user);
    }
}
