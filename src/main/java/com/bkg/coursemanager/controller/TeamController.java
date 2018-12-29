package com.bkg.coursemanager.controller;
/**
 * @Description Team接口相关Controller
 * @author bao
 * @version v1.2
 * @date 2018/12/21
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkg.coursemanager.entity.Course;
import com.bkg.coursemanager.entity.strategy.CourseMemberLimitStrategy;
import com.bkg.coursemanager.entity.strategy.MemberLimitStrategy;
import com.bkg.coursemanager.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.TeamValidRequest;
import com.bkg.coursemanager.entity.User;
import com.sun.mail.auth.Ntlm;
import com.bkg.coursemanager.dao.CourseDao;
import com.bkg.coursemanager.dao.TeamDao;

@RestController
@RequestMapping(value="/team")
public class TeamController {
	/*
	 * 
	 */
	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StrategyService strategyService;
	
	/*
	 * 创建队伍，并为队伍自动添加序列号
	 */
	@RequestMapping(value="",method=RequestMethod.POST)//不用传Leader和member的name
	public Map<String,Object> createTeam(@RequestBody Team team)
	{
		int serial=1;
		//组队策略先不加
		List<Team> teams=courseDao.getAllTeamByCourseId(team.getCourse().getId());
		for(Team teamExit:teams)
		{
			if(teamExit.getSerial()!=serial)break;//从1开始找到第一个没有被占用的序号
			serial++;
		}
		team.setSerial(serial);
		team.setStatus(0);//提交后小组为未提交审核
		teamDao.createTeam(team);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 修改队伍信息
	 */
	@RequestMapping(value="/{teamId}",method=RequestMethod.PUT)
	public Map<String,Object> updateTeam(@PathVariable("teamId") Integer teamId,@RequestBody Team team)
	{
		team.setId(teamId);
		teamDao.updateTeamByTeamId(team);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 根据ID获取队伍信息
	 */
	@RequestMapping(value="/{teamId}",method=RequestMethod.GET)
	public Team getTeamById(@PathVariable("teamId") Integer teamId)
	{
		return teamDao.getTeamById(teamId);
	}
	
	@RequestMapping(value="/{classId}/class",method=RequestMethod.GET)
	public List<Team> getTeamByClassId(@PathVariable("classId") Integer classId)
	{
		return teamDao.getTeamByClassId(classId);
	}
	
	/*
	 * 根据ID删除队伍,并清空与队伍有关的组员的队伍信息
	 */
	@RequestMapping(value="/{teamId}",method=RequestMethod.DELETE)
	public Map<String,Object> deleteTeamById(@PathVariable("teamId") Integer teamId)
	{
		teamDao.deleteTeamByTeamId(teamId);
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}

	/*
	 * 为队伍批量添加组员
	 */
	@RequestMapping(value="/{teamId}/add",method=RequestMethod.PUT)
	public Map<String, Object> addTeamMembers(@PathVariable("teamId") Integer teamId,@RequestBody List<Map<String, Object>> members)
	{
		Team team=teamDao.getTeamById(teamId);
		int courseId=team.getCourse().getId();
		for(Map<String, Object> member:members)
		{
			
			String memberId=(String) member.get("memberId");
			int id=Integer.parseInt(memberId);
			//System.out.println(id);
			teamDao.addTeamMember(teamId, id, courseId);
		}
		Map<String, Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 删除队伍某个组员(需要传入班级信息)
	 */
	@RequestMapping(value="/{teamId}/remove",method=RequestMethod.PUT)
	public Map<String, Object> deleteTeamMember(@PathVariable("teamId") Integer teamId,@RequestBody Map<String, Object> member)
	{
		String memberId=(String) member.get("memberId");
		int id=Integer.parseInt(memberId);
		Team team=teamDao.getTeamById(teamId);
		int courseId=team.getCourse().getId();
		teamDao.updateStudentTeamByStudentIdAndCourseId(id, courseId, 0);
		Map<String, Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 创建组队申请请求
	 */
	@RequestMapping(value="/{teamId}/teamValidRequest",method=RequestMethod.POST)
	public Map<String, Object> createTeamValidRequest(@PathVariable("teamId") Integer teamId, @RequestBody TeamValidRequest teamValidRequest)
	{
		Team team=teamDao.getTeamById(teamId);
		teamValidRequest.setStatus(2);//更新组队合法请求的状态为审核中
		teamValidRequest.setTeacher(team.getCourse().getTeacher());
		teamValidRequest.setTeam(team);
		teamDao.addTeamValidRequest(teamValidRequest);
		Map<String, Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	
	/*
	 * 老师处理组队合法请求
	 */
	@RequestMapping(value="/{teamId}/deal",method=RequestMethod.PUT)
	public Integer dealTeamValidRequest(@PathVariable("teamId") Integer teamId, @RequestBody TeamValidRequest teamValidRequest)
	{
		Integer record=teamDao.updateTeamValidRequest(teamValidRequest);
		Team team=teamDao.getTeamById(teamId);
		team.setStatus(teamValidRequest.getStatus());
		teamDao.updateTeamByTeamId(team);
		return record;
	}
	
	@RequestMapping(value="/{teamId}/course/{courseId}/leader/{leaderId}",method=RequestMethod.GET)
	public List<User> getMembersByTeamIdAndCourseIdAndLeaderId(@PathVariable("teamId") Integer teamId,@PathVariable("courseId") Integer courseId,@PathVariable("leaderId") Integer leaderId)
	{
		return teamDao.getMembersByTeamIdAndCourseIdAndLeaderId(teamId, courseId, leaderId);
	}

}
