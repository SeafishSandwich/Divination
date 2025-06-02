package com.example.demo.model;


import com.example.demo.JDBC.DAO.UserDAO;
import com.example.demo.JDBC.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    

    public void registerUser(User user) {
        userDAO.saveUser(user);
    }

    public boolean checkLogin(String email, String password) {
        return userDAO.findByEmail(email, password) != null;
    }

    public  User getNowUser(String email, String password){
        User nowUser = userDAO.findByEmail(email, password);
        return nowUser;
    }
}
