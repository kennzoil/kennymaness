package com.kennymaness.kennymaness.service;
import com.kennymaness.kennymaness.daos.RoleRepository;
import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.models.Role;
import com.kennymaness.kennymaness.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service("userService")
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // findByUsername - isolate a User by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // saveUser - save a User to the database
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setAssignedRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    // listAll - list each User
    public List<User> listAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    // createUser - create a new User
    public static User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}