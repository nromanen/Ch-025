package com.softserve.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.ConfigurationPropertiesDao;
import com.softserve.dao.SubjectDao;
import com.softserve.entity.Category;
import com.softserve.entity.ConfigurationProperty;
import com.softserve.service.AdministratorService;
import com.softserve.service.CategoryService;
import com.softserve.service.SubjectService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	SubjectDao subjectDao;

	@Autowired
	ConfigurationPropertiesDao configurationPropertiesDao;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubjectService subjectService;

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
	@Transactional
	public String getSupportEmail() {
		return configurationPropertiesDao.getPropertyByKey("supportEmail").getValue();
	}

	@Override
	@Transactional
	public ConfigurationProperty setSupportEmail(String email) {
		ConfigurationProperty emailProperty = configurationPropertiesDao.getPropertyByKey("supportEmail");
		emailProperty.setValue(email);
		return configurationPropertiesDao.updateProperty(emailProperty);
	}
}
