package com.kh.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//디생
@NoArgsConstructor
//매생
@AllArgsConstructor
@Data

//테이블 기능
@Entity
public class Board {
	// 필수
	@Id
	@GeneratedValue
	private int no;
	private String title;
	private String content;
	private String writer;
	// 시간 분 초 까지 들어갈 거야! 하는 어노테이션
	@CreationTimestamp
	@Column(name = "reg_date")
	private Date regDate;
}
