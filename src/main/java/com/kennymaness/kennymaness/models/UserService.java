package com.kennymaness.kennymaness.models;
import com.kennymaness.kennymaness.daos.RoleRepository;
import com.kennymaness.kennymaness.daos.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // findByUsername - isolate a User by username
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // saveUser - save a User to the database
    public User saveUser(User user) {
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    // listAll - list each User
    public List<User> listAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    // createUser - create a new User
    public static User createUser(String username, String password) {
        return new User(username, password);
    }

}