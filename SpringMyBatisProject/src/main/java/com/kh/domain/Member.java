package com.kh.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
//이퀄스, 겟터/셋터, 해시코드, 투스트링이랑
//requireArgsConstructor이 기본인데(이건 nonNull을 썻을 경우!)

@Getter
@Setter
@NoArgsConstructor // 디폴트 생성자
@AllArgsConstructor
@ToString
public class Member {
	// 멤버변수

	private int no;
	private String id;
	private String pw;
	private String name;
	private int coin;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date regDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updDate;
	private String enabled;
	//1:N 방식! 중 요
	private List<MemberAuth> authList;
}