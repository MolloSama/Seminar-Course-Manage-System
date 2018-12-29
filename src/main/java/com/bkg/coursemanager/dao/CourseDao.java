package com.bkg.coursemanager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.mapper.CourseMapper;
import com.bkg.coursemanager.entity.Class;
import com.bkg.coursemanager.entity.Course;
import com.bkg.coursemanager.entity.Round;
import com.bkg.coursemanager.entity.SeminarShareRequest;
import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.TeamShareRequest;
import com.bkg.coursemanager.entity.TeamValidRequest;
import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.entity.Seminar;

@Repository
public class CourseDao {
	@Autowired
	private CourseMapper courseMapper;
	
	public List<Round> getRoundByCourseId(int courseId)
	{
		return courseMapper.getRoundByCourseId(courseId);
	}
	
	public Course getCourseByCourseId(int courseId)
	{
		return courseMapper.getCourseByCourseId(courseId);
	}
	
	public List<Team> getAllTeamByCourseId(int courseId)
	{
		return courseMapper.getAllTeamByCourseId(courseId);
	}
	
	public Team getMyTeamByCourseId(int courseId,int userId)
	{
		return courseMapper.getMyTeamByUserId(courseId, userId);
	}
	
	public List<User> getNoTeamByCourseId(int courseId)
	{
		return courseMapper.getNoTeamByCourseId(courseId);
	}
	
	public List<Class> getClassByCourseId(int courseId)
	{
		return courseMapper.getClassByCourseId(courseId);
	}
	
	public List<Course> getAllCourse()
	{
		return courseMapper.getAllCourse();
	}
	
	public List<Course> getSubTeamShareCourseByCourseId(int courseId)
	{
		return courseMapper.getSubTeamShareCourseByCourseId(courseId);
	}
	
	public List<Course> getSubSeminarShareCourseByCourseId(int courseId)
	{
		return courseMapper.getSubSeminarShareCourseByCourseId(courseId);
	}
	
	public Integer updateMainCourseByCourseId(Course course)
	{
		return courseMapper.updateMainCourseByCourseId(course);
	}
	
	public Integer deleteCourseByCourseId(int courseId)
	{
		return courseMapper.deleteCourseByCourseId(courseId);
	}
	
	public Integer createCourse(Course course)
	{
		return courseMapper.addCourse(course);
	}
	
	public Integer createClass(Class class1)
	{
		return courseMapper.addClass(class1);
	}
	
	public Integer createTeamShareRequest(TeamShareRequest teamShareRequest)
	{
		return courseMapper.addTeamShareRequest(teamShareRequest);
	}
	
	public Integer createSeminarShareRequest(SeminarShareRequest seminarShareRequest)
	{
		return courseMapper.addSeminarShareRequest(seminarShareRequest);
	}
	
	public List<TeamValidRequest> getTeamValidRequestByCourseId(int courseId)
	{
		return courseMapper.getTeamValidRequestByCourseId(courseId);
	}

	public List<Course> getStudentCourseByStudentId(Integer studentId) {
		return courseMapper.getStudentCourseByStudentId(studentId);
	}

	public Class getStudentClassByCourseIdAndStudentId(Integer courseId, Integer studentId) {
		return courseMapper.getStudentClassByCourseIdAndStudentId(courseId,studentId);
	}

	public List<Seminar> getSeminarByCourseId(Integer courseId) {
		return courseMapper.getSeminarByCourseId(courseId);
	}
}
