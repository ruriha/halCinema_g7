package com.example.halCinema.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="member")
@Data
public class UserInfo {
	
	@Id
	@Column(name="memberMailaddress")
	private String memberMailaddress;
	
	@Column(nullable = false)
	private String memberPassword;
	
	@Column(nullable = false)
	private String memberName;

	@Column(nullable = false)
	private String memberNameKana;

	@Column(nullable = false)
	private LocalDate memberBirthday;

	@Column(nullable = false)
	private String memberAddress;

	@Column(nullable = false)
	private String memberTel;

	@Column(nullable = false)
	private LocalDate memberJoinday;

	@Column(nullable = false)
	private LocalDate memberUpdateday;

	@PrePersist
	public void prePersist() {
		if (this.memberName == null) {
			this.memberName = "default_name";
		}
		if (this.memberNameKana == null) {
			this.memberNameKana = "default_name_kana";
		}
		if (this.memberBirthday == null) {
			this.memberBirthday = LocalDate.of(2000, 1, 1); // 適切なデフォルト日付を設定
		}
		if (this.memberAddress == null) {
			this.memberAddress = "default_address";
		}
		if (this.memberTel == null) {
			this.memberTel = "000-0000-0000"; // 適切なデフォルト電話番号を設定
		}
		if (this.memberJoinday == null) {
			this.memberJoinday = LocalDate.now(); // 現在の日付をデフォルトとして設定
		}
		if (this.memberUpdateday == null) {
			this.memberUpdateday = LocalDate.now(); // 現在の日付をデフォルトとして設定
		}
	}

	@PreUpdate
	public void preUpdate() {
		this.memberUpdateday = LocalDate.now(); // 更新日を現在の日付に設定
	}
}
