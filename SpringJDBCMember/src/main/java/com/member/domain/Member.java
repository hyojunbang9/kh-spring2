package com.member.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private int no;
	private String name;
	private String id;
	private String pwd;
}
