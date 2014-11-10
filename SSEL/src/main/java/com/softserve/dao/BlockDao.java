package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Block;

public interface BlockDao {

	Block addBlock(Block block);

	Block updateBlock(Block block);

	void deleteBlock(Block block);

	Block getBlockById(int id);
	
	List<Block> getAllBlocks();

	List<Block> getBlocksBySubjectId(int id);
}
