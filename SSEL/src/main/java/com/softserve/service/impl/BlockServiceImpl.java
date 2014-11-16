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
	/**
	 * @see com.softserve.service.BlockService#addBlock(Block)
	 */
	@Override
	@Transactional
	public Block addBlock(Block block) {
		return blockDao.addBlock(block);
	}
	/**
	 * @see com.softserve.service.BlockService#updateBlock(Block)
	 */
	@Override
	@Transactional
	public Block updateBlock(Block block) {
		return blockDao.updateBlock(block);
	}
	/**
	 * @see com.softserve.service.BlockService#deleteBlock(Block)
	 */
	@Override
	@Transactional
	public void deleteBlock(Block block) {
		blockDao.setBlockDeleted(block.getId(), true);
	}
	/**
	 * @see com.softserve.service.BlockService#getBlockById(int)
	 */
	@Override
	@Transactional
	public Block getBlockById(int id) {
		return blockDao.getBlockById(id);
	}
	/**
	 * @see com.softserve.service.BlockService#getAllBlocks()
	 */
	@Override
	@Transactional
	public List<Block> getAllBlocks() {
		return blockDao.getAllBlocks();
	}
	/**
	 * @see com.softserve.service.BlockService#getBlocksBySubjectId(int)
	 */
	@Override
	@Transactional
	public List<Block> getBlocksBySubjectId(int id) {
		return blockDao.getBlocksBySubjectId(id);
	}
	/**
	 * @see com.softserve.service.BlockService#restoreBlock(Block)
	 */
	@Transactional
	@Override
	public void restoreBlock(Block block) {
		blockDao.setBlockDeleted(block.getId(), false);
	}
	@Transactional
	@Override
	public List<Block> getDeletedBlocks() {
		return blockDao.getAllDeletedBlocks();
	}

}
