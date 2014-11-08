package com.softserve.service;

import java.util.List;

import com.softserve.entity.Category;
import com.softserve.entity.Subject;

public interface SearchService {
	List<Category> getCategoriesByNamePart(String namePart);
	
	List<Subject> getSubjectsByNamePart(String namePart);
}
