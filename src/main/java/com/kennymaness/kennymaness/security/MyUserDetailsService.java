package com.kennymaness.kennymaness.security;

import com.kennymaness.kennymaness.daos.UserRepository;
import com.kennymaness.kennymaness.models.User;
import com.kennymaness.kennymaness.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(()-> new UsernameNotFoundException("Not Found: " + username));

        return user.map(MyUserDetails::new).get();
    }
}