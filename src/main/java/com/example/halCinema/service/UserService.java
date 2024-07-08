package com.example.halCinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.entity.UserInfo;
import com.example.halCinema.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional // トランザクション管理を行うために追加
    public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByMemberMailaddress(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    @Transactional // トランザクション管理を行うために追加
    public boolean authenticate(String memberMailaddress, String memberPassword) {
        UserInfo user = userRepository.findByMemberMailaddress(memberMailaddress);
        if (user != null && passwordEncoder.matches(memberPassword, user.getMemberPassword())) {
            return true;
        }
        return false;
    }
}
