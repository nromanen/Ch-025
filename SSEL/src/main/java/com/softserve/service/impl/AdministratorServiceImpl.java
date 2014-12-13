package com.softserve.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.ConfigurationPropertiesDao;
import com.softserve.dao.SubjectDao;
import com.softserve.entity.Category;
import com.softserve.entity.ConfigurationProperty;
import com.softserve.service.AdministratorService;
import com.softserve.service.CategoryService;
import com.softserve.service.UserService;

/**
 * Implements QuestionDao
 * @author Ivan
 *
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	SubjectDao subjectDao;

	@Autowired
	ConfigurationPropertiesDao configurationPropertiesDao;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	/**
	 * @see com.softserve.service.AdministratorService#addCategory(java.lang.String)
	 */
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

	/**
	 * @see com.softserve.service.AdministratorService#getSupportEmail()
	 */
	@Override
	@Transactional
	public String getSupportEmail() {
		return configurationPropertiesDao.getPropertyByKey("supportEmail").getValue();
	}

	/**
	 * @see com.softserve.service.AdministratorService#setSupportEmail(java.lang.String)
	 */
	@Override
	@Transactional
	public ConfigurationProperty setSupportEmail(String email) {
		ConfigurationProperty emailProperty = configurationPropertiesDao.getPropertyByKey("supportEmail");
		emailProperty.setValue(email);
		return configurationPropertiesDao.updateProperty(emailProperty);
	}

	/**
	 * @see com.softserve.service.AdministratorService#getCountRegistredUsersByLastDays(int)
	 */
	public Map<String, Long> getCountRegistredUsersByLastDays(int lastDays) {
		Map<String, Long> list = new LinkedHashMap<String, Long>();
		Date startDate;
		Date endDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);

		for (int i = 1; i <= lastDays; i++) {
			endDate = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		startDate = calendar.getTime();
		list.put(sdf.format(startDate), userService.getCountOfUsersByRegistrationDate(startDate, endDate));
		}
		return list;
	}
}
