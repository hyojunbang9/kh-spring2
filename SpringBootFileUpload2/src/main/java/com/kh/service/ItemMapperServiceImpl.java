package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.domain.Item;
import com.kh.mapper.ItemMapper;

@Service
public class ItemMapperServiceImpl implements ItemMapperService {
	@Autowired
	private ItemMapper im;

	@Override
	@Transactional
	public void create(Item item) throws Exception {
		im.create(item);
	}

	@Override
	@Transactional(readOnly = true)
	public Item read(Integer itemId) throws Exception {
		return im.read(itemId);
	}

	@Override
	@Transactional
	public void update(Item item) throws Exception {
		im.update(item);
	}

	@Override
	@Transactional
	public void delete(Integer itemId) throws Exception {
		im.delete(itemId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Item> list() throws Exception {
		return im.list();
	}

	@Override
	public Item getPicture(Integer itemId) throws Exception {
		return im.getPicture(itemId);
	}

}
