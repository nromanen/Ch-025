package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.softserve.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	@DatabaseSetup("classpath:category.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:category.xml")
	public void testGetCategoryById() {
		assertNotNull(categoryService.getCategoryById(1));
		assertNull(categoryService.getCategoryById(100500));
	}

	@Test
	@DatabaseSetup("classpath:category.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:category.xml")
	public void testGetAllCategories() {
		assertEquals(3, categoryService.getAllCategories().size());
	}

	@Test
	@DatabaseSetup("classpath:category.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:category.xml")
	public void testDeleteCategory() {
		assertEquals(3, categoryService.getAllCategories().size());
		categoryService.deleteCategory(categoryService.getCategoryById(1));
		assertEquals(2, categoryService.getAllCategories().size());
		assertTrue(categoryService.getCategoryById(1).isDeleted());

	}

	@Test
	@DatabaseSetup("classpath:category.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:category.xml")
	public void testRestoreCategory() {
		assertEquals(3, categoryService.getAllCategories().size());
		categoryService.deleteCategory(categoryService.getCategoryById(1));
		assertEquals(2, categoryService.getAllCategories().size());
		assertTrue(categoryService.getCategoryById(1).isDeleted());
		categoryService.restoreCategory(categoryService.getCategoryById(1));
		assertFalse(categoryService.getCategoryById(1).isDeleted());
		assertEquals(3, categoryService.getAllCategories().size());
	}

	@Test
	@DatabaseSetup("classpath:category.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:category.xml")
	public void testGetDeletedCategories() {
		assertEquals(1, categoryService.getDeletedCategories().size());
	}

	@Test
	@DatabaseSetup("classpath:category.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:category.xml")
	public void testAddCategory() {
		assertEquals(3, categoryService.getAllCategories().size());
		Category category = new Category();
		// category.setId(5);
		category.setName("Category5");
		category.setDeleted(false);
		categoryService.addCategory(category);
		assertEquals(4, categoryService.getAllCategories().size());
	}

	@Test
	@DatabaseSetup("classpath:category.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:category.xml")
	public void testUpdateCategory() {
		assertEquals("Category1", categoryService.getCategoryById(1).getName());
		Category category = categoryService.getCategoryById(1);
		category.setName("Category999");
		categoryService.updateCategory(category);
		assertEquals("Category999", categoryService.getCategoryById(1)
				.getName());

	}

}
