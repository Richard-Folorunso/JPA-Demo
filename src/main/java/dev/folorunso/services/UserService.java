package dev.folorunso.services;

import dev.folorunso.entity.User;
import dev.folorunso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Unable to find user with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        userRepository.update(user.getId(), user.getData());
        return userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Unable to find user with ID: " + user.getId()));
    }

    @Transactional
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Unable to find user with ID: " + id));
        userRepository.delete(user);
    }
}
