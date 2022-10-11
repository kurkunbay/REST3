package com.example.rest.service;


import com.example.rest.dao.RoleDao;
import com.example.rest.dao.UserDao;
import com.example.rest.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user) {
        if (userDao.findByEmail(user.getEmail()) == null) {
            user.setPassword(user.getPassword());
            userDao.save(user);
        } else try {
            throw new Exception("Duplicate email!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        if (user.getId() == null) {
            try {
                throw new Exception("User not have ID!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        User oldUser = getUserById(user.getId());
        if (user.getPassword().equals("") || user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(user.getPassword());
        }
        userDao.save(user);

    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        Optional<User> optional = userDao.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
userDao.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}