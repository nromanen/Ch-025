package com.softserve.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Subject;
import com.softserve.service.AdministratorService;
import com.softserve.service.CategoryService;
import com.softserve.service.SubjectService;

@Service
public class AdministratorServiceImpl implements AdministratorService {
	
	private List<CourseScheduler> subscribedCourses;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubjectService subjectService;
	
	/**
	 * Constructor by default
	 */
	public AdministratorServiceImpl() {

	}
	
	@Override
	public List<CourseScheduler> getActiveCourses() {
		Date currentDate = new Date();
		ArrayList<CourseScheduler> cs = new ArrayList<>();
		for (CourseScheduler item : subscribedCourses) {
			// TODO add end date course check
			if ((currentDate.after(item.getStart()) || currentDate.equals(item.getStart()))
					&& currentDate.before(item.getEnd())) { //check if course is started
				cs.add(item);
			}
		}
		return cs;
	}
	
	@Override
	public boolean addCategory(String name) {
		boolean exist = false;
		for (Category cat : categoryService.getAllCategories()) {
			if ((cat.getName()).equalsIgnoreCase(name)) {
				exist = true;
			}
		}
		if (!exist) {
		Category newCategory = new Category();
		newCategory.setName(name);
		categoryService.addCategory(newCategory);
		}
		
		return exist;
	}
	
	@Override
	public List<Subject> searchSubjectsByName(String searchText) {
		List<Subject> subjects = new ArrayList<Subject>();
		for (Subject subject : subjectService.getAllSubjects()) {
			if ((subject.getName()).equalsIgnoreCase(searchText)) {
				subjects.add(subject);
			}
		}
		
		return subjects;
	}
	
	@Override
	public List<Subject> searchSubjectsByCategory(String searchText) {
		List<Subject> subjects = new ArrayList<Subject>();
		for (Subject subject : subjectService.getAllSubjects()) {
			if ((subject.getCategory().getName()).equalsIgnoreCase(searchText)) {
				subjects.add(subject);
			}
		}
		
		return subjects;
	}
	
}
