package com.example.halCinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Member;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberMailaddress(String memberMailaddress);
}
