package com.kh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Member {
	private String userId;
	private String userPw;
	private String userName;
	private String email;
}
