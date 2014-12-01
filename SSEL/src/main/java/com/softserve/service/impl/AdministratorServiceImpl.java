package com.softserve.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.dao.SubjectDao;
import com.softserve.entity.Category;
import com.softserve.service.AdministratorService;
import com.softserve.service.CategoryService;
import com.softserve.service.SubjectService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	SubjectDao subjectDao;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubjectService subjectService;

	@Override
	public boolean addCategory(String name) {
		boolean exist = checkCategory(name);
		if (!exist) {
		Category newCategory = new Category();
		newCategory.setName(name);
		categoryService.addCategory(newCategory);
		}
		return exist;
	}

	public boolean checkCategory(String name) {
		boolean exist = false;
		for (Category cat : categoryService.getAllCategories()) {
			if ((cat.getName()).equalsIgnoreCase(name)) {
				exist = true;
			}
		}
		return exist;
	}

}
