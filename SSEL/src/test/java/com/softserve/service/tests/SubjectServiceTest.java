package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import com.softserve.entity.Subject;
import com.softserve.service.CategoryService;
import com.softserve.service.SubjectService;
import com.softserve.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class SubjectServiceTest {

	@Autowired
	SubjectService subjectService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testGetAllSubjects() {
		List<Subject> listActual = subjectService.getAllSubjects();
		assertEquals("Mathematics", listActual.get(0).getName());
		assertEquals(9, listActual.size());
	}
	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testGetSubjectsByCategoryId() {
		List<Subject> listActual = subjectService.getSubjectsByCategoryId(4);
		assertEquals("Quality control", listActual.get(0).getName());
		assertEquals(1, listActual.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testGetSubjectById() {
		Subject subject = subjectService.getSubjectById(2);
		assertEquals("Java EE", subject.getName());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testGetSubjectByUserId() {
		List<Subject> list = subjectService.getSubjectsByUserId(1);
		assertEquals(9l, list.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testGetSubjectByIdNull() {
		Subject subject = subjectService.getSubjectById(100500);
		assertNull(subject);
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testAddSubject() {
		Subject subject = new Subject();
		subject.setName("Subject");
		subject.setCategory(categoryService.getCategoryById(1));
		subject.setDeleted(false);
		subject.setDescription("Subject");
		subject.setUser(userService.getUserById(1));
		subjectService.addSubject(subject);
		List<Subject> listActual = subjectService.getAllSubjects();
		assertEquals(10, listActual.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testDeleteSubject() {
		Subject subject = subjectService.getSubjectById(9);
		subjectService.deleteSubject(subject);
		List<Subject> listActual = subjectService.getAllSubjects();
		assertEquals(8, listActual.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testRestoreSubject() {
		Subject subject = subjectService.getSubjectById(9);
		subjectService.restoreSubject(subject);
		List<Subject> listActual = subjectService.getAllSubjects();
		assertEquals(9, listActual.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testUpdateSubject() {
		Subject subject = subjectService.getSubjectById(9);
		subject.setName("German");
		subjectService.updateSubject(subject);
		List<Subject> listActual = subjectService.getAllSubjects();
		assertEquals("German", listActual.get(8).getName());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsByCategoryCount() {
		Long size = subjectService.getCountOfSubjectsByCategory("Programming");
		assertEquals(5l, size.longValue());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsByNameCount() {
		Long size = subjectService.getCountOfSubjectsByName("Java");
		assertEquals(5l, size.longValue());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testGetSubjectCount() {
		Long size = subjectService.getCountOfSubjects();
		assertEquals(9l, size.longValue());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testGetSubjectByTextCount() {
		Long size = subjectService.getCountOfSubjectsByText("ja");
		assertEquals(5l, size.longValue());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjects() {
		List<Subject> listActual =
				subjectService.getSubjectsByNameByPage("ja", 0, 10, "name", "asc");
		assertEquals(5, listActual.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsWithPageSize() {
		List<Subject> listActual =
				subjectService.getSubjectsByNameByPage("ja", 1, 3, "name", "asc");
		assertEquals(3, listActual.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsByTextWithPage() {
		List<Subject> listActual =
				subjectService.getSubjectsByTextByPage("a", 20, 10, "name", "asc");
		assertEquals(0, listActual.size());
	}

	@Test
	@DatabaseSetup("classpath:subjects.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:subjects.xml")
	public void testSearchSubjectsByTextWithPageSize() {
		List<Subject> listActual =
				subjectService.getSubjectsByTextByPage("a", 1, 2, "name", "asc");
		assertEquals(2, listActual.size());
	}

}
