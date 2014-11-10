package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.BlockDao;
import com.softserve.entity.Block;
import com.softserve.entity.Category;
import com.softserve.entity.Topic;

@Repository
public class BlockDaoImpl implements BlockDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(BlockDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public Block addBlock(Block block) {
		LOG.debug("Add block");
		entityManager.persist(block);
		return block;
	}

	@Override
	public Block updateBlock(Block block) {
		LOG.debug("Update block (id = {})", block.getId());
		entityManager.merge(block);
		return block;
	}

	@Override
	public void deleteBlock(Block block) {
		Query query = entityManager
				.createQuery("DELETE FROM Block b WHERE b.id = :id");
		query.setParameter("id", block.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted block(id = {})", block.getId());
		} else {
			LOG.warn("Tried to delete block(id = {})", block.getId());
		}
	}

	@Override
	public Block getBlockById(int id) {
		LOG.debug("Get block with id = {}", id);
		return entityManager.find(Block.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getAllBlocks() {
		LOG.debug("Get all blocks");
		List<Block> blocks = new ArrayList<>();
		blocks.addAll(entityManager.createQuery("FROM Block")
				.getResultList());
		return blocks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlocksBySubjectId(int id) {
		LOG.debug("Get all topics by block id = {}", id);
		Query query = entityManager.createQuery("FROM Block b "
				+ "WHERE b.subject.id = :id " + "ORDER BY b.order");
		query.setParameter("id", id);
		return query.getResultList();
	}

}
