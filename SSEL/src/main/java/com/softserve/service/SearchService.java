package com.softserve.service;

import java.util.List;

import com.softserve.entity.Category;
import com.softserve.entity.Subject;

public interface SearchService {
	List<Category> getCategoriesByNamePart(String namePart);
	
	List<Subject> getSubjectsByNamePart(String namePart, int pageNumber, int pageSize, 
			String sortBy, boolean isReverse);
	
	Long getSubjectsQuantityByNamePart(String namePart);
	
	Long getCategoriesQuantityByNamePart(String namePart);
	
	List<Subject> getSubjectsByCategoryIdWithLimit(int categoryId, int pageNumber, int pageSize, 
			String sortBy, boolean isReverse);
}
