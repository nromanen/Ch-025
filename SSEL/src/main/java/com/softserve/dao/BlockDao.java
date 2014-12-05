package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Block;

/**
 * Specify block data access object functionality
 * 
 * @author Anatoliy
 * @author Roma Khomyshyn
 *
 */
public interface BlockDao {
	/**
	 * Add new block into block list
	 * 
	 * @param block
	 *            new block
	 * @return added block
	 */
	Block addBlock(Block block);

	/**
	 * Update block if exists
	 * 
	 * @param block
	 *            updated block
	 * @return updated block
	 */
	Block updateBlock(Block block);

	/**
	 * Manage delete
	 * 
	 * @param blockId
	 *            unique block identifier
	 * @param deleted
	 *            true - set deleter, false - restore
	 */
	void setBlockDeleted(int blockId, boolean deleted);

	/**
	 * Return block by id if exists
	 * 
	 * @param id
	 *            unique block identifier
	 * @return block if exist
	 */
	Block getBlockById(int id);

	/**
	 * Return all block which is not deleted
	 * 
	 * @return list of blocks
	 */
	List<Block> getAllBlocks();

	/**
	 * Return all deleted blocks
	 * 
	 * @return list of blocks
	 */
	List<Block> getAllDeletedBlocks();

	/**
	 * Return blocks by subject id
	 * 
	 * @param id
	 *            unique block identifier
	 * @return list of blocks
	 */
	List<Block> getBlocksBySubjectId(int id);

	/**
	 * Return nearest inactive block
	 * 
	 * @return
	 */
	Block getNearestInactiveBlockBySubject(int subjectId);

}
