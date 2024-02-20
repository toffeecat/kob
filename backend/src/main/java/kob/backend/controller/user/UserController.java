package kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import kob.backend.mapper.UserMapper;
import kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    /**
     * Retrieve all users
     * @return List of all users
     */
    @GetMapping("/user/all/")
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    /**
     * Retrieve a single user by user ID
     * @param userId User ID to retrieve
     * @return User object with the specified ID
     */
    @GetMapping("/user/{userId}/")
    public User getUser(@PathVariable int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);

        return userMapper.selectOne(queryWrapper);

        // Range traversal
        // public List<User> getUser(int userId)
        // queryWrapper.ge("id", 2).le("id", 3);
        // return userMapper.selectList(queryWrapper);
    }

    /**
     * Add a new user with the specified ID, username, and password
     * @param userId User ID
     * @param username Username
     * @param password Password
     * @return Success message after adding the user
     */
    @GetMapping("/user/add/{userId}/{username}/{password}/")
    public String addUser(@PathVariable int userId,
                          @PathVariable String username,
                          @PathVariable String password) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(userId, username, encodedPassword);
        userMapper.insert(user);
        return "Add User Successfully";
    }

    /**
     * Delete a user with the specified ID
     * @param userId User ID to delete
     * @return Success message after deleting the user
     */
    @GetMapping("/user/delete/{userId}/")
    public String deleteUser(@PathVariable int userId) {
        userMapper.deleteById(userId);
        return "Delete User Successfully";
    }
}