package com.softserve.service;

import java.util.Map;

import com.softserve.entity.ConfigurationProperty;

public interface AdministratorService {

	public boolean addCategory(String name);

	public String getSupportEmail();

	public ConfigurationProperty setSupportEmail(String email);

	public Map<String, Long> getCountRegistredUsersByLastDays(int lastDays);
}
