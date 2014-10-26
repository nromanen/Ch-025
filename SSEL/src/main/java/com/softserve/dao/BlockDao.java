package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Block;

public interface BlockDao {

	public Block addBlock(Block block);

	public Block updateBlock(Block block);

	public void deleteBlock(Block block);

	public Block getBlockById(int id);

	public List<Block> getAllBlocks();

	public List<Block> getBlocksBySubjectId(int id);
}
