package com.kh.service;

import java.util.List;

import com.kh.domain.Item;

public interface ItemMapperService {
	// 파일 생성
	public void create(Item item) throws Exception;

	// 파일 출력(한 개)
	public Item read(Integer itemId) throws Exception;

	// 파일 수정
	public void update(Item item) throws Exception;

	// 파일 삭제
	public void delete(Integer itemId) throws Exception;

	// 파일 출력
	public List<Item> list() throws Exception;

	// 파일 경로
	public Item getPicture(Integer itemId) throws Exception;
}
