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
	public Block getBlockById(int id) {
		LOG.debug("Get block with id = {}", id);
		return entityManager.find(Block.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getAllBlocks() {
		LOG.debug("Get all blocks");
		List<Block> blocks = new ArrayList<>();
		blocks.addAll(entityManager.createQuery("FROM Block b WHERE b.isDeleted = :val")
				.setParameter("val",false)
				.getResultList());
		return blocks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getBlocksBySubjectId(int id) {
		LOG.debug("Get all topics by block id = {}", id);
		Query query = entityManager.createQuery("FROM Block b "
				+ "WHERE b.subject.id = :id AND b.isDeleted = :val " + "ORDER BY b.order");
		query.setParameter("id", id);
		query.setParameter("val",false);
		return query.getResultList();
	}


	@Override
	public void setBlockDeleted(int blockId, boolean deleted) {
		Query query = entityManager
				.createQuery("UPDATE Block b SET b.isDeleted = :del WHERE b.id = :id");
		query.setParameter("id", blockId);
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted block(id = {})", blockId);
		} else {
			LOG.warn("Tried to delete block(id = {})", blockId);
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Block> getAllDeletedBlocks() {
		LOG.debug("Get all deleted blocks");
		Query query = entityManager.createQuery("SELECT b FROM Block b WHERE b.deleted = :val");
		query.setParameter("val",true);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Block getNearestInactiveBlockBySubject(int subjectId) {
		Query query = entityManager.createQuery("SELECT b FROM Block b WHERE b.startTime = "
				+ "(SELECT min(b.startTime) FROM Block b where b.subject.id = :sid and b.startTime > current_timestamp()"
				+ " and b.isDeleted = :val)")
				.setParameter("sid", subjectId)
				.setParameter("val", false);
		List<Block> list = query.getResultList();
		return (list.size() > 0) ? list.get(0): null;
	}


}
