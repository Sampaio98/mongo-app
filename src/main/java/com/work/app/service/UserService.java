package com.work.app.service;

import com.work.app.domain.User;
import com.work.app.exception.ObjectNotFoundException;
import com.work.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CityService cityService;

    public User saveUser(User user) {
        cityService.findByName(user.getCity());
        LocalDate date = LocalDate.now();
        Integer age = date.getYear() - user.getBirthday().getYear();
        user.setAge(age);
        return repository.save(user);
    }

    public List<User> findByName(String name) {
        return repository.findByName(name);
    }

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("user"));
    }

    public void updateName(String id, String name) {
        User user = findById(id);
        user.setName(name);
        repository.save(user);
    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}
