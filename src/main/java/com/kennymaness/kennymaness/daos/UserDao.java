package com.kennymaness.kennymaness.daos;

import org.springframework.data.repository.CrudRepository;
import com.kennymaness.kennymaness.models.User;
import java.util.ArrayList;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserDao extends CrudRepository<User, Integer> {

    User findByFirstName(String first_name);
    User findByLastName(String last_name);
    User findByUsername(String username);
    User findByEmail(String email);

}