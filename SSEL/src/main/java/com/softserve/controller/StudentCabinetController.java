package com.softserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
import com.softserve.service.StudentCabinetSevice;

@Controller
public class StudentCabinetController {
	@Autowired
	private StudentCabinetSevice studCabinetService;
	@RequestMapping("/subscribe")
	public String performSubscribe(@RequestParam("course_id") String courseScheduler) {
		StudentGroup studGroup = new StudentGroup();
		
		return courseScheduler;
	}
	@RequestMapping("/student")
	public String printStudentCourses(@RequestParam("table") String table,Model model) {
		studCabinetService.initSubscribedList(1);
		if (table == null || table.equals("future")) {
			model.addAttribute("table", generateHtmlTable(studCabinetService.getFutureCourses()));
		} else if (table.equals("active")){
			model.addAttribute("table", generateHtmlTable(studCabinetService.getActiveCourses(),1));
		}
		return "student";
	}
	/**
	 * Generate html table code for future courses
	 * @param courses future courses list
	 * @return html code of table
	 */
	private String generateHtmlTable(List<CourseScheduler> courses) {
		String table = "";
		table += "<thead><tr><td>Subject name</td><td>Start time</td></tr></thead>";
		table += "<tbody>";
		for (CourseScheduler item: courses) {
			table += "<tr><td>"+item.getSubject().getName()+"</td><td>"+item.getStart()+"</td></tr>";
		}
		table += "</tbody>";
		return table;
	}
	/**
	 * Generate html table code for active courses
	 * @param courses list of active courses
	 * @param userId student id
	 * @return html code of table
	 */
	private String generateHtmlTable(List<CourseScheduler> courses,int userId) {
		String table = "";
		table += "<thead><tr><td>Start time</td><td>Subject name</td><td>Current rating</td>"
				+ "<td>Current progress</td></tr></thead>";
		table += "<tbody>";
		for (CourseScheduler item: courses) {
			StudentGroup group = studCabinetService.getStudentGroupByUserAndCourseId(userId, item.getId());
			table += "<tr><td>"+item.getSubject().getName()+"</td><td>"+item.getStart()+"</td>"
					+ "<td>"+group.getRating()+"</td><td>"+group.getProgress()+"</td></tr>";
		}
		table += "</tbody>";
		return table;
	}
}
