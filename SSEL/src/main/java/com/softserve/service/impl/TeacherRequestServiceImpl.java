package com.softserve.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.dao.TeacherRequestDao;
import com.softserve.entity.TeacherRequest;
import com.softserve.service.TeacherRequestService;

@Service
public class TeacherRequestServiceImpl implements TeacherRequestService {

	@Autowired
	private TeacherRequestDao teacherRequestDao;

	@Override
	@Transactional
	public TeacherRequest addTeacherRequest(TeacherRequest request) {
		return teacherRequestDao.addTeacherRequest(request);
	}

	@Override
	@Transactional
	public TeacherRequest updateTeacherRequest(TeacherRequest request) {
		return teacherRequestDao.updateTeacherRequest(request);
	}

	@Override
	@Transactional
	public void deleteTeacherRequest(TeacherRequest request) {
		teacherRequestDao.deleteTeacherRequest(request);
	}

	@Override
	@Transactional
	public TeacherRequest getTeacherRequestById(int id) {
		return teacherRequestDao.getTeacherRequestById(id);
	}

	@Override
	@Transactional
	public List<TeacherRequest> getAllTeacherRequests() {
		return teacherRequestDao.getAllTeacherRequests();
	}

	@Override
	@Transactional
	public TeacherRequest getTeacherRequestByUserId(int userId) {
		return teacherRequestDao.getTeacherRequestByUserId(userId);
	}

	@Override
	@Transactional
	public List<TeacherRequest> getAllActiveTeacherRequests() {
		return teacherRequestDao.getAllActiveTeacherRequests();
	}

	@Override
	@Transactional
	public long getAllActiveTeacherRequestsCount() {
		return teacherRequestDao.getAllActiveTeacherRequestsCount();
	}

}
