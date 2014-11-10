package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.StudentGroupDao;
import com.softserve.entity.StudentGroup;
import com.softserve.service.StudentGroupService;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

	@Autowired
	private StudentGroupDao studentGroupDao;

	@Override
	@Transactional
	public StudentGroup addStudentGroup(StudentGroup studentGroup) {
		return studentGroupDao.addStudentGroup(studentGroup);
	}

	@Override
	@Transactional
	public StudentGroup updateStudentGroup(StudentGroup studentGroup) {
		return studentGroupDao.updateStudentGroup(studentGroup);
	}

	@Override
	@Transactional
	public void deleteStudentGroup(StudentGroup studentGroup) {
		studentGroupDao.deleteStudentGroup(studentGroup);
	}

	@Override
	@Transactional
	public StudentGroup getStudentGroupById(int id) {
		return studentGroupDao.getStudentGroupById(id);
	}

	@Override
	@Transactional
	public List<StudentGroup> getStudentGroupsByGroupNumber(int number) {
		return studentGroupDao.getStudentGroupsByGroupNumber(number);
	}

	@Override
	@Transactional
	public List<StudentGroup> getAllStudentGroups() {
		return studentGroupDao.getAllStudentGroups();
	}

	@Transactional
	@Override
	public StudentGroup getStudentGroupByUserAndGroupId(int userId,
			int groupId) {
		return studentGroupDao.getStudentGroupByGroupAndUser(groupId, userId);
	}
}
