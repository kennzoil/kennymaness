package com.kennymaness.kennymaness.daos;

import org.springframework.data.repository.CrudRepository;
import com.kennymaness.kennymaness.models.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserDao extends CrudRepository<User, Integer> {

}