package com.example.halCinema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.halCinema.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findByMemberMailaddress(String memberMailaddress);
}
