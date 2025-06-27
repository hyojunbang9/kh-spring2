package com.kh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.kh.common.security.CustomAccessDeniedHandler;
import com.kh.common.security.CustomLoginSuccessHandler;
import com.kh.common.security.CustomNoOpPasswordEncoder;
import com.kh.common.security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
//인가 정책을 각 개발자에게 위임한다.
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)

public class SecurityConfig {
	@Autowired
	DataSource dataSource;

	@Bean
	// @EnableWebSecurity랑 하단 명령어 입력 시 Seurity 기능을 수동으로 가능함!
	// DB에서 여러 값으로 보안 설정을 넣어주기 위함(?)
	SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
		log.info("security 환경 설정 시작");
		// CSRF 토큰 비활성화
		http.csrf().disable();

		// 로그인 인가 정책
		//@EnableMethodSecurity 설정으로 주석처리 해도 됨
//		http.authorizeRequests().requestMatchers("/board/list").permitAll();
//		http.authorizeRequests().requestMatchers("/board/register").hasRole("MEMBER");
//		http.authorizeRequests().requestMatchers("/notice/list").permitAll();
//		http.authorizeRequests().requestMatchers("/notice/register").hasRole("ADMIN");

		// 로그인 처리 자동 설정화면 사용 => 내가 만든 로그인 화면으로 대체
		// 접근 거부 처리자에 대한 페이지 이동 URI를 지정

		// 내가 만든 로그인 실패 화면으로 대체
//		http.exceptionHandling().accessDeniedPage("/accessError");

		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

		// 인증 정책
//		http.formLogin();
		// 개발자가 정의한 로그인 페이지의 URI를 지정한다.
		http.formLogin().loginPage("/login");

		// 개발자가 정의한 로그인 페이지의 URI를 지정한다.
		// 로그인 성공 후 처리를 담당하는 처리자로 지정한다.
		// 사용자 정보 세팅 완료 후, 인증/인가 설정 : VO => User 처리를 담당하는 처리자로..
		http.formLogin().loginPage("/login").successHandler(createAuthenticationSuccessHandler());

		// 로그아웃 처리를 위한 URI를 지정하고, 로그아웃한 후에 세션을 무효화 한다.
//		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");

		// 데이터 소스를 지정하고 테이블을 이용해서 기존 로그인 정보를 기록
		// 쿠키의 유효 시간을 지정한다(24시간).
		http.rememberMe().key("bhj").tokenRepository(createJDBCRepository()).tokenValiditySeconds(60 * 60 * 24);

		return http.build();
	}

	private PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	// DB 연동을 위한 주석 처리
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
//		auth.inMemoryAuthentication().withUser("member").password("{noop}123456").roles("MEMBER");
//		auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN", "MEMBER");
//	}
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(createUserDetailsService()).passwordEncoder(createPasswordEncoder());

	}

	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new CustomNoOpPasswordEncoder();
	}

	// CustomAccessDeniedHandler를 빈으로 등록한다.
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	// CustomLoginSuccessHandler를 빈으로 등록한다.
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}

}
