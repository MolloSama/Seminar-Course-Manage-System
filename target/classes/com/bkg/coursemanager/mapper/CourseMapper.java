package com.bkg.coursemanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bkg.coursemanager.entity.Class;
import com.bkg.coursemanager.entity.Course;
import com.bkg.coursemanager.entity.Round;
import com.bkg.coursemanager.entity.Seminar;
import com.bkg.coursemanager.entity.SeminarShareRequest;
import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.TeamShareRequest;
import com.bkg.coursemanager.entity.TeamValidRequest;
import com.bkg.coursemanager.entity.User;

public interface CourseMapper {
	public List<Course> getAllCourse(); 
	public Course getCourseByCourseId(@Param("courseId") int courseId); 
	public List<Round> getRoundByCourseId(@Param("courseId") int courseId);
	public List<Course> getStudentCourseByCourseId(@Param("courseId") int courseId,@Param("userId") int userId);
	public List<Course> getTeacherClassByCourseId(@Param("courseId") int courseId,@Param("userId") int userId);
	public List<Team> getAllTeamByCourseId(@Param("courseId") int courseId);
	public Team getMyTeamByUserId(@Param("courseId") int courseId,@Param("userId") int userId);
	public List<User> getNoTeamByCourseId(@Param("courseId") int courseId);
	public List<Class> getClassByCourseId(@Param("courseId") int courseId);
	public List<Course> getSubTeamShareCourseByCourseId(@Param("courseId") int courseId);
	public List<Course> getSubSeminarShareCourseByCourseId(@Param("courseId") int courseId);
	public Integer addClass(Class class1);
	public Integer addCourse(Course course);
	public Integer addTeamShareRequest(TeamShareRequest teamShareRequest);
	public Integer addSeminarShareRequest(SeminarShareRequest seminarShareRequest);
	public Integer updateMainCourseByCourseId(Course course);
	public Integer deleteCourseByCourseId(@Param("courseId") int courseId);
	public List<TeamValidRequest> getTeamValidRequestByCourseId(@Param("courseId") int courseId);
	public List<Course> getStudentCourseByStudentId(@Param("studentId") int studentId);
	public Class getStudentClassByCourseIdAndStudentId(@Param("courseId") int courseId, @Param("studentId") int studentId);
	public List<Seminar> getSeminarByCourseId(@Param("courseId") int courseId);
}
