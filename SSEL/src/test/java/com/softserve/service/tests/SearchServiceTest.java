package com.softserve.service.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import com.softserve.entity.Category;
import com.softserve.service.SearchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class SearchServiceTest {
	
	@Autowired
	private SearchService searchService;
	
	@Test
	@DatabaseSetup("classpath:categories.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:categories.xml")
	public void testSearchCategories() {
		List<Category> listActual = searchService.getCategoriesByNamePart("pro");
		assertEquals("Programming", listActual.get(0).getName());
		assertEquals(1, listActual.size());
	}
	
	@Test
	@DatabaseSetup("classpath:categories.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:categories.xml")
	public void testSearchCategoriesCount() {
		Long listSize = searchService.getCategoriesQuantityByNamePart("a");
		assertEquals(4l, listSize.longValue());
	}
	
	@Test
	@DatabaseSetup("classpath:categories.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:categories.xml")
	public void testSearchCategoriesCountZero() {
		Long listSize = searchService.getCategoriesQuantityByNamePart("gsfs");
		assertEquals(0l, listSize.longValue());
	}

}
