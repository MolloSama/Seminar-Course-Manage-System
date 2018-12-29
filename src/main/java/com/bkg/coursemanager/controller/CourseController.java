package com.bkg.coursemanager.controller;
/**
 * @Description Course接口相关Controller
 * @author bao
 * @version v1.2
 * @date 2018/12/21
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkg.coursemanager.entity.Course;
import com.bkg.coursemanager.entity.Round;
import com.bkg.coursemanager.entity.SeminarShareRequest;
import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.TeamShareRequest;
import com.bkg.coursemanager.entity.TeamValidRequest;
import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.dao.CourseDao;
import com.bkg.coursemanager.dao.TeamDao;
import com.bkg.coursemanager.entity.Class;
import com.bkg.coursemanager.entity.Seminar;


@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private CourseDao courseDao;
	
	/*
	 * 创建课程
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public Map<String, Object> createCourse(@RequestBody Course course)
	{
		String teamStartTime=course.getStartTeamTime();
		String teamEndTime=course.getEndTeamTime();
		teamStartTime+=" 00:00:00";
		teamEndTime+=" 00:00:00";
		course.setStartTeamTime(teamStartTime);
		course.setEndTeamTime(teamEndTime);
		courseDao.createCourse(course);
		Map<String, Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 获取所有课程
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public List<Course> getAllCourse()
	{
		return courseDao.getAllCourse();
	}
	
	/*
	 * 获取学生所有课程
	 */
	@RequestMapping(value="/{studentId}/student", method=RequestMethod.GET)
	public List<Course> getStudentCourseByStudentId(@PathVariable Integer studentId)
	{
		return courseDao.getStudentCourseByStudentId(studentId);
	}
	
	/*
	 * 获取学生相应课程下的班级
	 */
	@RequestMapping(value="/{courseId}/student/{studentId}/class", method=RequestMethod.GET)
	public Class getStudentCourseByStudentId(@PathVariable("courseId") Integer courseId,@PathVariable("studentId") Integer studentId)
	{
		return courseDao.getStudentClassByCourseIdAndStudentId(courseId,studentId);
	}
	
	/*
	 * 获取课程相应的轮次
	 */
	@RequestMapping(value="/{courseId}/round", method=RequestMethod.GET)
	public List<Round> getRoundByCourseId(@PathVariable Integer courseId)
	{
		List<Round> rounds=null;
		Course course=courseDao.getCourseByCourseId(courseId);
		if(course.getSeminarMainCourse()!=null)rounds=courseDao.getRoundByCourseId(course.getSeminarMainCourse().getId());
		else rounds=courseDao.getRoundByCourseId(courseId);
		for(Round round:rounds)
		{
			round.setOrderName(round.getOrderName());
		}
		return rounds;
	}
	
	/*
	 * 获取课程相应的讨论课
	 */
	@RequestMapping(value="/{courseId}/seminar", method=RequestMethod.GET)
	public List<Seminar> getSeminarByCourseId(@PathVariable Integer courseId)
	{
		List<Seminar> seminars=null;
		Course course=courseDao.getCourseByCourseId(courseId);
		if(course.getSeminarMainCourse()!=null)seminars=courseDao.getSeminarByCourseId(course.getSeminarMainCourse().getId());
		else seminars=courseDao.getSeminarByCourseId(courseId);
		return seminars;
	}
	/*
	 * 按ID获取课程
	 */
	@RequestMapping(value="/{courseId}", method=RequestMethod.GET)
	public Course getCourseByCourseId(@PathVariable Integer courseId)
	{
		return courseDao.getCourseByCourseId(courseId);
	}
	
	/*
	 * 按ID删除课程
	 */
	@RequestMapping(value="/{courseId}", method=RequestMethod.DELETE)
	public Map<String, Object> deleteCourseByCourseId(@PathVariable Integer courseId)
	{
		courseDao.deleteCourseByCourseId(courseId);
		Map<String, Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 按ID获取课程下所有的队伍
	 */
	@RequestMapping(value="/{courseId}/team", method=RequestMethod.GET)
	public List<Team> getAllTeamByCourseId(@PathVariable Integer courseId)
	{
		return courseDao.getAllTeamByCourseId(courseId);
	}
	
	/*
	 * 按ID获取课程里我的队伍
	 */
	@RequestMapping(value="/{courseId}/myTeam", method=RequestMethod.GET)//getUserId
	public Team getMyTeamByCourseId(@PathVariable Integer courseId)
	{
		int userId=1;
		return courseDao.getMyTeamByCourseId(courseId, userId);
	}
	
	/*
	 * 按ID获取课程里我的组队状态
	 */
	@RequestMapping(value="/{courseId}/myTeamStatus", method=RequestMethod.GET)//getUserId
	public Map<String, Object> getMyTeamStatusByCourseId(@PathVariable Integer courseId)
	{
		int userId=1;
		Map<String, Object> status=new HashMap<>();
		Team team=courseDao.getMyTeamByCourseId(courseId, userId);
		if(team==null)status.put("status", "noTeam");
		else
		{
			if(team.getLeader().getId()==userId)status.put("status", "teamLeader");
			else status.put("status", "haveTeam");
		}
		return status;
	}
	
	/*
	 * 按ID获取课程未组队学生
	 */
	@RequestMapping(value="/{courseId}/noTeam", method=RequestMethod.GET)
	public List<User> getNoTeamByCourseId(@PathVariable Integer courseId)
	{
		return courseDao.getNoTeamByCourseId(courseId);
	}
	
	/*
	 * 按ID获取课程下的班级
	 */
	@RequestMapping(value="/{courseId}/class", method=RequestMethod.GET)
	public List<Class> getClassByCourseId(@PathVariable Integer courseId)
	{
		return courseDao.getClassByCourseId(courseId);
	}
	
	/*
	 * 按ID在相应课程下创建班级
	 */
	@RequestMapping(value="/{courseId}/class", method=RequestMethod.POST)
	public Map<String, Object> getClassByCourseId(@RequestBody Class class1)
	{
		courseDao.createClass(class1);
		Map<String, Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 按ID获取与课程有关的组队共享信息
	 */
	@RequestMapping(value="/{courseId}/teamShare", method=RequestMethod.GET)
	public List<Map<String, Course>> getTeamShareByCourseId(@PathVariable Integer courseId)
	{
		List<Map<String, Course>> teamShares=new ArrayList<>();
		Course now=courseDao.getCourseByCourseId(courseId);
		Course mainCourse=now.getTeamMainCourse();
		if(mainCourse!=null)
		{
			Map<String, Course>	subTeamShare=new HashMap<String, Course>();
			subTeamShare.put("mainCourse", mainCourse);
			subTeamShare.put("subCourse", now);
			teamShares.add(subTeamShare);
		}
		List<Course> subCourses=courseDao.getSubTeamShareCourseByCourseId(courseId);
		for(Course subCourse:subCourses)
		{
			Map<String, Course>	mainTeamShare=new HashMap<String, Course>();
			mainTeamShare.put("mainCourse", now);
			mainTeamShare.put("subCourse", subCourse);
			teamShares.add(mainTeamShare);
		}
		return teamShares;
	}
	
	/*
	 * 按ID获取与课程有关的讨论课共享信息
	 */
	@RequestMapping(value="/{courseId}/seminarShare", method=RequestMethod.GET)
	public List<Map<String, Course>> getSeminarShareByCourseId(@PathVariable Integer courseId)
	{
		List<Map<String, Course>> seminarShares=new ArrayList<>();
		Course now=courseDao.getCourseByCourseId(courseId);
		Course mainCourse=now.getSeminarMainCourse();
		if(mainCourse!=null)
		{
			Map<String, Course>	subSeminarShare=new HashMap<String, Course>();
			subSeminarShare.put("mainCourse", mainCourse);
			subSeminarShare.put("subCourse", now);
			seminarShares.add(subSeminarShare);
		}
		List<Course> subCourses=courseDao.getSubSeminarShareCourseByCourseId(courseId);
		for(Course subCourse:subCourses)
		{
			Map<String, Course>	mainSeminarShare=new HashMap<String, Course>();
			mainSeminarShare.put("mainCourse", now);
			mainSeminarShare.put("subCourse", subCourse);
			seminarShares.add(mainSeminarShare);
		}
		return seminarShares;
	}
	
	/*
	 * 当此课程为主课程时，按ID取消与从课程的组队共享(取消了其他的表怎么改)
	 */
	@RequestMapping(value="/{courseId}/teamMainShare/{teamShareCourseId}", method=RequestMethod.DELETE)
	public Map<String, Object> deleteTeamMainShare(@PathVariable("courseId") Integer courseId, @PathVariable("teamShareCourseId") Integer teamShareCourseId )
	{
		Course mainCourse =new Course();
		mainCourse.setId(0);
		
		Course subCourse=new Course();
		subCourse.setId(teamShareCourseId);
		subCourse.setTeamMainCourse(mainCourse);
		courseDao.updateMainCourseByCourseId(subCourse);
		teamDao.deleteCLassTeamByCourseId(teamShareCourseId);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 当此课程为主课程时，按ID取消与从课程的讨论课共享(取消了其他的表怎么改)
	 */
	@RequestMapping(value="/{courseId}/seminarMainShare/{seminarShareCourseId}", method=RequestMethod.DELETE)
	public Map<String, Object>  deleteSeminarMainShare(@PathVariable("courseId") Integer courseId, @PathVariable("seminarShareCourseId") Integer seminarShareCourseId )
	{
		Course mainCourse =new Course();
		mainCourse.setId(0);
		
		Course subCourse=new Course();
		subCourse.setId(seminarShareCourseId);
		subCourse.setSeminarMainCourse(mainCourse);
		courseDao.updateMainCourseByCourseId(subCourse);
		teamDao.deleteCLassTeamByCourseId(courseId);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 当此课程为从课程时，按ID取消与主课程的组队共享(取消了其他的表怎么改)
	 */
	@RequestMapping(value="/{courseId}/teamSubShare", method=RequestMethod.DELETE)
	public Map<String, Object>  deleteTeamSubShare(@PathVariable("courseId") Integer courseId)
	{
		Course mainCourse =new Course();
		mainCourse.setId(0);
		
		Course subCourse=new Course();
		subCourse.setId(courseId);
		subCourse.setTeamMainCourse(mainCourse);
		courseDao.updateMainCourseByCourseId(subCourse);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 当此课程为从课程时，按ID取消与主课程的讨论课共享(取消了其他的表怎么改)
	 */
	@RequestMapping(value="/{courseId}/seminarSubShare", method=RequestMethod.DELETE)
	public Map<String, Object>  deleteSeminarSubShare(@PathVariable("courseId") Integer courseId)
	{
		Course mainCourse =new Course();
		mainCourse.setId(0);
		
		Course subCourse=new Course();
		subCourse.setId(courseId);
		subCourse.setSeminarMainCourse(mainCourse);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	@RequestMapping(value="/teamShareRequest", method=RequestMethod.POST)
	public Map<String, Object> createTeamShareRequest(@RequestBody TeamShareRequest teamShareRequest)
	{
		courseDao.createTeamShareRequest(teamShareRequest);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;		
	}
	
	@RequestMapping(value="/seminarShareRequest", method=RequestMethod.POST)
	public Map<String,Object> createSeminarShareRequest(@RequestBody SeminarShareRequest seminarShareRequest)
	{
		courseDao.createSeminarShareRequest(seminarShareRequest);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}

	@RequestMapping(value="/{courseId}/teamValid",method=RequestMethod.GET)
	public List<TeamValidRequest> getTeamValidRequest(@PathVariable("courseId") Integer courseId)
	{
		return courseDao.getTeamValidRequestByCourseId(courseId);
	}
   	
}
