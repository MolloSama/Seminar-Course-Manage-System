package com.bkg.coursemanager.controller;
/**
 * @Description Request接口相关Controller
 * @author bao
 * @version v1.2
 * @date 2018/12/21
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkg.coursemanager.dao.CourseDao;
import com.bkg.coursemanager.dao.RequestDao;
import com.bkg.coursemanager.dao.TeamDao;
import com.bkg.coursemanager.entity.Course;
import com.bkg.coursemanager.entity.SeminarShareRequest;
import com.bkg.coursemanager.entity.TeamShareRequest;

@RestController
@RequestMapping(value="/request")
public class RequestController {
	
	@Autowired
	private RequestDao requestDao;
	
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private CourseDao courseDao;
	
	/*
	 * 根据ID获取共享组队请求信息
	 */
	@RequestMapping(value="/teamShare/{teamShareId}",method=RequestMethod.GET)
	public TeamShareRequest getTeamShareRequestById(@PathVariable("teamShareId") Integer teamShareId)
	{
		return requestDao.getTeamShareRequestByID(teamShareId);
	}
	
	/*
	 * 根据ID更改共享组队请求状态，如果同意则更改从课程相关信息
	 */
	@RequestMapping(value="/teamShare/{teamShareId}",method=RequestMethod.PUT)
	public Integer updateTeamShareRequestStatusById(@PathVariable("teamShareId") Integer teamShareId,@RequestBody TeamShareRequest teamShareRequest)
	{
		TeamShareRequest oldTeamShareRequest=requestDao.getTeamShareRequestByID(teamShareId);
		oldTeamShareRequest.setStatus(teamShareRequest.getStatus());
		teamShareRequest=oldTeamShareRequest;
		if(teamShareRequest.getStatus()==1)
		{
			Course subCourse=teamShareRequest.getSubCourse();
			Course mainCourse=teamShareRequest.getMainCourse();
			int mainCourseId=mainCourse.getId();
			int subCourseId=subCourse.getId();
			teamDao.teamShare(subCourseId, mainCourseId);
			subCourse.setTeamMainCourse(mainCourse);
			courseDao.updateMainCourseByCourseId(subCourse);
		}
		return requestDao.updateTeamShareRequestStatusById(teamShareRequest);
	}
	
	/*
	 * 根据ID获取共享讨论课请求信息
	 */
	@RequestMapping(value="/seminarShare/{seminarShareId}",method=RequestMethod.GET)
	public SeminarShareRequest getSeminarShareRequestById(@PathVariable("seminarShareId") Integer seminarShareId)
	{
		return requestDao.getSeminarShareRequestByID(seminarShareId);
	}
	
	/*
	 * 根据ID更改讨论课请求状态，如果同意则更改从课程相关信息
	 */
	@RequestMapping(value="/seminarShare/{seminarShareId}",method=RequestMethod.PUT)
	public Integer updateSeminarShareRequestStatusById(@PathVariable("seminarShareId") Integer seminarShareId,@RequestBody SeminarShareRequest seminarShareRequest)
	{
		//insert into seminar
		SeminarShareRequest oldSeminarShareRequest=requestDao.getSeminarShareRequestByID(seminarShareId);
		oldSeminarShareRequest.setStatus(seminarShareRequest.getStatus());
		seminarShareRequest=oldSeminarShareRequest;
		if(seminarShareRequest.getStatus()==1)
		{
			Course subCourse=seminarShareRequest.getSubCourse();
			Course mainCourse=seminarShareRequest.getMainCourse();
			int mainCourseId=mainCourse.getId();
			int subCourseId=subCourse.getId();
			subCourse.setSeminarMainCourse(mainCourse);
			courseDao.updateMainCourseByCourseId(subCourse);
		}
		return requestDao.updateSeminarShareRequestStatusById(seminarShareRequest);
	}
}
