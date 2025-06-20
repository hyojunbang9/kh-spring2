package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.domain.MybatisMember;
import com.kh.mapper.MemberDAO;

@Service
public class MemberDAOServiceimpl implements MemberDAOService {
	@Autowired
	private MemberDAO bd;

	@Override
	public void insert(MybatisMember member) throws Exception {
		bd.insert(member);
	}

	@Override
	public MybatisMember select(MybatisMember member) throws Exception {
		return bd.select(member);
	}

	@Override
	public void update(MybatisMember member) throws Exception {
		bd.update(member);
	}

	@Override
	public void delete(MybatisMember member) throws Exception {
		bd.delete(member);
	}

	@Override
	public List<MybatisMember> selectAll() throws Exception {
		return bd.selectAll();
	}

}
