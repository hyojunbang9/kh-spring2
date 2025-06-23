package com.kh.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
