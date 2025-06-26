package com.kh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.kh.common.security.CustomAccessDeniedHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	// @EnableWebSecurity랑 하단 명령어 입력 시 Seurity 기능을 수동으로 가능함!
	// DB에서 여러 값으로 보안 설정을 넣어주기 위함(?)
	SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
		log.info("security 환경 설정 시작");
		// CSRF 토큰 비활성화
		http.csrf().disable();

		// 로그인 인가 정책
		http.authorizeRequests().requestMatchers("/board/list").permitAll();
		http.authorizeRequests().requestMatchers("/board/register").hasRole("MEMBER");
		http.authorizeRequests().requestMatchers("/notice/list").permitAll();
		http.authorizeRequests().requestMatchers("/notice/register").hasRole("ADMIN");

		// 로그인 처리 자동 설정화면 사용 => 내가 만든 로그인 화면으로 대체
		// 접근 거부 처리자에 대한 페이지 이동 URI를 지정

		// 내가 만든 로그인 실패 화면으로 대체
//		http.exceptionHandling().accessDeniedPage("/accessError");

		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

		// 인증 정책
		http.formLogin();

		return http.build();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
		auth.inMemoryAuthentication().withUser("member").password("{noop}123456").roles("MEMBER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN", "MEMBER");
	}

	// CustomAccessDeniedHandler를 빈으로 등록한다.
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
}
