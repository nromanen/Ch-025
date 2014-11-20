package com.softserve.service;

import java.util.List;

import com.softserve.entity.Block;
/**
 * Specify block service funtionality
 * @author  Anatoliy
 * @author Roma Khomyshyn
 *
 */
public interface BlockService {
	/**
	 * Add new block into block list
	 * @param block new block
	 * @return added block
	 */
	Block addBlock(Block block);
	/**
	 * Update some block
	 * @param block updated block
	 * @return updated block
	 */
	Block updateBlock(Block block);
	/**
	 * Mark block as deleted
	 * @param block block which is marked
	 */
	void deleteBlock(Block block);
	/**
	 * Restore block which was marked as deleted
	 * @param block block which will be restored
	 */
	void restoreBlock(Block block);
	/**
	 * Return block by id
	 * @param id unique block identifier
	 * @return block
	 */
	Block getBlockById(int id);
	/**
	 * Return all blocks which is not marked as deleted
	 * @return list of blocks
	 */
	List<Block> getAllBlocks();
	/**
	 * Return blocks for subject
	 * @param id unique subject identifier
	 * @return list of blocks
	 */
	List<Block> getBlocksBySubjectId(int id);
	/**
	 * Return blocks which is marked as deleetd
	 * @return list of blocks
	 */
	List<Block> getDeletedBlocks();
}
