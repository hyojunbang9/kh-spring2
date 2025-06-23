package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.domain.Member;
import com.kh.persistence.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberDAOServiceimpl implements MemberDAOService {
	@Autowired
	private MemberRepository mr;

	@Override
	@Transactional
	public void insert(Member member) throws Exception {
		log.info("start Log insert");
		mr.save(member);
	}

	@Override
	@Transactional(readOnly = true)
	public Member select(Member member) throws Exception {
		log.info("select Log insert");
		return mr.getOne(member.getNo());
	}

	@Override
	@Transactional
	public void update(Member member) throws Exception {
		log.info("update Log insert");
		mr.save(member);
	}

	@Override
	@Transactional
	public void delete(Member member) throws Exception {
		log.info("delete Log insert");
		mr.deleteById(member.getNo());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Member> selectAll() throws Exception {
		log.info("selectAll Log insert");
		return mr.findAll(Sort.by(Direction.DESC, "no"));
	}

}
