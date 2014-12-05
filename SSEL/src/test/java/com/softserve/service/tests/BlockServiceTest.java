package com.softserve.service.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.softserve.entity.Block;
import com.softserve.entity.Subject;
import com.softserve.service.BlockService;
import com.softserve.service.SubjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class BlockServiceTest {

	@Autowired
	private BlockService blockService;

	@Autowired
	private SubjectService subjectService;

	@Test
	@DatabaseSetup("classpath:block.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:block.xml")
	public void testAddBlock() {
		assertEquals(4, blockService.getAllBlocks().size());
		Block block = new Block();
		block.setDeleted(false);
		block.setEndTime(new Date());
		block.setStartTime(new Date());
		block.setName("Java");
		block.setOrder(8);
		block.setSubject(subjectService.getSubjectById(1));
		block = blockService.addBlock(block);
		assertEquals(5, blockService.getAllBlocks().size());
		assertNotNull(blockService.getBlockById(block.getId()));
	}

	@Test
	@DatabaseSetup("classpath:block.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:block.xml")
	public void testUpdateBlock() {
		assertEquals(4, blockService.getAllBlocks().size());
		Block block = blockService.getBlockById(1);
		block.setName("Java");
		blockService.updateBlock(block);
		assertEquals(4, blockService.getAllBlocks().size());
		block = blockService.getBlockById(1);
		assertTrue(block.getName().equals("Java"));
	}

	@Test
	@DatabaseSetup("classpath:block.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:block.xml")
	public void testGetBlocksBySubjectId() {
		Subject subject = subjectService.getSubjectById(1);
		List<Block> blocks = blockService.getBlocksBySubjectId(subject.getId());
		assertEquals(4, blocks.size());
	}

	@Test
	@DatabaseSetup("classpath:block.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:block.xml")
	public void testSetBlockDeleted() {
		Block block = blockService.getBlockById(1);
		assertFalse(block.isDeleted());
		blockService.deleteBlock(block);
		block = blockService.getBlockById(1);
		assertTrue(block.isDeleted());
	}

	@Test
	@DatabaseSetup("classpath:block.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:block.xml")
	public void testGetAllDeletedBlocks() {
		List<Block> blocks = blockService.getDeletedBlocks();
		assertEquals(1, blocks.size());
	}

	@Test
	@DatabaseSetup("classpath:block.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:block.xml")
	public void testGetNearestInactiveBlockBySubject() {
		Subject subject = subjectService.getSubjectById(1);
		Block block = blockService.getNearestInactiveBlockBySubject(subject
				.getId());
		assertTrue(block.getName().equals("Abstract class in java"));
		assertNull(blockService.getNearestInactiveBlockBySubject(0));
	}
}
