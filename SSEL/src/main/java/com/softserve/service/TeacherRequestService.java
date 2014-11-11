package com.softserve.service;

import java.util.List;

import com.softserve.entity.TeacherRequest;

public interface TeacherRequestService {

	public TeacherRequest addTeacherRequest(TeacherRequest request);

	public TeacherRequest updateTeacherRequest(TeacherRequest request);

	public void deleteTeacherRequest(TeacherRequest request);

	public TeacherRequest getTeacherRequestById(int id);

	public List<TeacherRequest> getAllTeacherRequests();

	public TeacherRequest getTeacherRequestByUserId(int userId);

	public List<TeacherRequest> getAllActiveTeacherRequests();

	public long getAllActiveTeacherRequestsCount();
}
