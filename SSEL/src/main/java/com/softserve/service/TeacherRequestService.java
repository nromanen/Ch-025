package com.softserve.service;

import java.util.List;

import com.softserve.entity.TeacherRequest;

public interface TeacherRequestService {

	TeacherRequest addTeacherRequest(TeacherRequest request);

	TeacherRequest updateTeacherRequest(TeacherRequest request);

	void deleteTeacherRequest(TeacherRequest request);

	TeacherRequest getTeacherRequestById(int id);

	List<TeacherRequest> getAllTeacherRequests();

	TeacherRequest getTeacherRequestByUserId(int userId);

	List<TeacherRequest> getAllActiveTeacherRequests();

	long getAllActiveTeacherRequestsCount();
}
