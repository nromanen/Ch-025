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
import com.softserve.entity.Subject;
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
	public void testSearchCategoriesZero() {
		List<Category> listActual = searchService.getCategoriesByNamePart("Gfd");
		assertEquals(0, listActual.size());
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
	
	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjects() {
		List<Subject> listActual = searchService.getSubjectsByNamePart("ma", 1, 10, "name", true);
		assertEquals("Mathematics", listActual.get(0).getName());
		assertEquals(1, listActual.size());
	}
	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsPageSize() {
		List<Subject> listActual = searchService.getSubjectsByNamePart("ja", 1, 3, "name", true);
		assertEquals(3, listActual.size());
	}
	
	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsPages() {
		List<Subject> listActual = searchService.getSubjectsByNamePart("ja", 2, 10, "name", true);
		assertEquals(0, listActual.size());
	}
	
	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchManySubjects() {
		List<Subject> listActual = searchService.getSubjectsByNamePart("ja", 1, 10, "category", false);
		assertEquals("Java EE", listActual.get(0).getName());
		assertEquals(5, listActual.size());
	}
	
	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsCount() {
		Long size = searchService.getSubjectsQuantityByNamePart("ja");
		assertEquals(5l, size.longValue());
	}
	
	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsCountZero() {
		Long size = searchService.getSubjectsQuantityByNamePart("fd");
		assertEquals(0, size.longValue());
	}
	
}
