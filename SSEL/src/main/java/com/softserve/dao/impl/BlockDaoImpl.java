package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.BlockDao;
import com.softserve.entity.Block;

@Repository
public class BlockDaoImpl implements BlockDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(BlockDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public void addBlock(Block block) {
		entityManager.persist(block);
		LOG.debug("Add block");
	}

	@Override
	public void updateBlock(Block block) {
		entityManager.merge(block);
		LOG.debug("Update block (id = {})", block.getId());
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
		return entityManager.createNativeQuery("FROM Block").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlocksBySubjectId(int id) {
		Query query = entityManager.createQuery("FROM Block b "
				+ "WHERE b.subject.id = :id " + "ORDER BY b.order");
		query.setParameter("id", id);

		LOG.debug("Get all topics by block id = {}", id);
		return query.getResultList();
	}

}
