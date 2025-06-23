package com.kh.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//디생
@NoArgsConstructor
//매생
@AllArgsConstructor
@Data
@SequenceGenerator(name = "MEMBER_SEQ_GEN", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)

//테이블 기능
@Entity
public class Member {
	// 필수
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")
	private int no;
	private String name;
	private String id;
	private String pw;
	// 시간 분 초 까지 들어갈 거야! 하는 어노테이션
	@CreationTimestamp
	@Column(name = "reg_date")
	private Date regDate;
}
