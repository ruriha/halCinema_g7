package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.halCinema.model.Member;

public interface MemberRepositoty extends JpaRepository<Member, Integer>{
	
	//  座席予約システム(seat)の会員情報取得
	@Query("select m.memberName, m.memberNameKana, m.memberTel, m.memberMailaddress " +
	           "from member m " +
	           "where m.memberId = ?1")
	List<Object[]> findReservationMember(Integer memberId);
	
	
	//  会員メールアドレス取得
	@Query("select m.memberMailaddress " +
	           "from member m " +
	           "where m.memberId = ?1")
	List<Object[]> findMailaddress(Integer memberId);
		

}
