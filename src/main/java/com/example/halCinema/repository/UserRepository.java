package com.example.halCinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.halCinema.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByMemberMailaddress(String memberMailaddress);
}
