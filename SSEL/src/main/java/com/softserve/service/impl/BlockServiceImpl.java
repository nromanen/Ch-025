package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.BlockDao;
import com.softserve.entity.Block;
import com.softserve.service.BlockService;

@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
	private BlockDao blockDao;

	@Override
	@Transactional
	public void addBlock(Block block) {
		blockDao.addBlock(block);
	}

	@Override
	@Transactional
	public void updateBlock(Block block) {
		blockDao.updateBlock(block);
	}

	@Override
	@Transactional
	public void deleteBlock(Block block) {
		blockDao.deleteBlock(block);
	}

	@Override
	@Transactional
	public Block getBlockById(int id) {
		return blockDao.getBlockById(id);
	}

	@Override
	@Transactional
	public List<Block> getAllBlocks() {
		return blockDao.getAllBlocks();
	}

	@Override
	@Transactional
	public List<Block> getBlocksBySubjectId(int id) {
		return blockDao.getBlocksBySubjectId(id);
	}

}
