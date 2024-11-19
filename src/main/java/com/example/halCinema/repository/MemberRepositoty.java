package com.example.halCinema.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Member;

@Repository
public interface MemberRepositoty extends JpaRepository<Member, UUID>{
	
	//  座席予約システム(seat)の会員情報取得
	@Query("select m.memberName, m.memberNameKana, m.memberTel, m.memberMailaddress " +
	           "from member m " +
	           "where m.memberId = ?1")
	List<Object[]> findReservationMember(UUID memberId);
	
	
	//  会員メールアドレス取得
	@Query("select m.memberMailaddress " +
	           "from member m " +
	           "where m.memberId = ?1")
	List<Object[]> findMailaddress(UUID memberId);
	
	
	//  ログイン
	@Query("select m.memberId " +
	           "from member m " +
	           "where m.memberMailaddress = ?1 " +
	           "and m.memberPassword = ?2")
	List<Object[]> loginEntry(String memberMailaddress, String memberPassword);
		

}
