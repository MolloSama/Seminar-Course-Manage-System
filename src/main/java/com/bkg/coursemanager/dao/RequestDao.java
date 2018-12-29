package com.bkg.coursemanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.entity.SeminarShareRequest;
import com.bkg.coursemanager.entity.TeamShareRequest;
import com.bkg.coursemanager.mapper.RequestMapper;

@Repository
public class RequestDao {
	@Autowired
	private RequestMapper requestMapper;
	
	public TeamShareRequest getTeamShareRequestByID(int id)
	{
		return requestMapper.getTeamShareRequestById(id);
	}
	
	public SeminarShareRequest getSeminarShareRequestByID(int id)
	{
		return requestMapper.getSeminarShareRequestById(id);
	}
	
	public Integer updateTeamShareRequestStatusById(TeamShareRequest teamShareRequest)
	{
		return requestMapper.updateTeamShareRequestStatusById(teamShareRequest);
	}
	
	public Integer updateSeminarShareRequestStatusById(SeminarShareRequest seminarShareRequest)
	{
		return requestMapper.updateSeminarShareRequestStatusById(seminarShareRequest);
	}
}
