package com.bkg.coursemanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.TeamValidRequest;
import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.mapper.TeamMapper;
@Repository
public class TeamDao {
	@Autowired
	private TeamMapper teamMapper;
	
	public List<User> getMembersByTeamIdAndCourseIdAndLeaderId(int teamId,int courseId,int leaderId)
	{
		return teamMapper.getMembersByTeamIdAndCourseIdAndLeaderId(teamId, courseId, leaderId);
	}
	
	public Team getTeamById(int id)
	{
		return teamMapper.getTeamByTeamId(id);
	}
	
	public List<Team> getAllTeamByCourseId(int courseId)
	{
		return teamMapper.getAllTeamByCourseId(courseId);
	}
	
	//是否要做事务
	public Integer createTeam(Team team)
	{
		Integer record=teamMapper.addTeam(team);
		List<User> members=team.getMembers();
		User leader=team.getLeader();
		teamMapper.addTeamMember(team.getId(), leader.getId(), team.getCourse().getId());
		if(members!=null)
		{
			for(User member:members)
			{
				System.out.println(member.getId()+"!!!!!!!!!!!!!!!!!");
				teamMapper.addTeamMember(team.getId(), member.getId(), team.getCourse().getId());
			}
		}
		return record;
	} 
	
	public Integer addTeamValidRequest(TeamValidRequest teamValidRequest)
	{
		return teamMapper.addTeamValidRequest(teamValidRequest);
	}
	
	public Integer addClassStudent(int classId,int studentId,int courseId,int teamId)
	{
		return teamMapper.addClassStudent(classId, studentId, courseId, teamId);
	}
	
	public Integer updateTeamByTeamId(Team team)
	{
		return teamMapper.updateTeamByTeamId(team);
	}
	
	public Integer addTeamMember(int teamId,int memberId,int courseId)
	{
		return teamMapper.addTeamMember(teamId, memberId, courseId);
	}
	
	public TeamValidRequest getTeamValidRequestById(int teamValidRequestId)
	{
		return teamMapper.getTeamValidRequestById(teamValidRequestId);
	}
	
	public Integer updateTeamValidRequest(TeamValidRequest teamValidRequest)
	{
		return teamMapper.updateTeamValidRequest(teamValidRequest);
	}
	
	public Integer deleteTeamByTeamId(int teamId)
	{
		teamMapper.deleteTeamByTeamId(teamId);
		return teamMapper.updateClassStudentTeamIdByTeamId(teamId, 0);
	}
	
	public Integer updateStudentTeamByStudentIdAndCourseId(int studentId,int courseId,int teamId)
	{
		return teamMapper.updateClassStudentTeamIdByCourseIdAndStudentId(teamId, courseId, studentId);
	}
	
	public Integer teamShare(int subCourseId,int mainCourseId)
	{
		return teamMapper.teamShare(subCourseId, mainCourseId);
	}
	
	public Integer deleteCLassTeamByCourseId(int courseId)
	{
		return teamMapper.deleteClassTeamByCourseId(courseId);
	}

	public List<Team> getTeamByClassId(Integer classId) {
		// TODO Auto-generated method stub
		return teamMapper.getTeamByClassId(classId);
	}
}
