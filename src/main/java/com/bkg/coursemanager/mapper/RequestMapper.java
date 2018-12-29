package com.bkg.coursemanager.mapper;

import org.apache.ibatis.annotations.Param;

import com.bkg.coursemanager.entity.SeminarShareRequest;
import com.bkg.coursemanager.entity.TeamShareRequest;

public interface RequestMapper {
	public TeamShareRequest getTeamShareRequestById(@Param("id") int id);
	public SeminarShareRequest getSeminarShareRequestById(@Param("id") int id);
	public Integer updateTeamShareRequestStatusById(TeamShareRequest teamShareRequest);
	public Integer updateSeminarShareRequestStatusById(SeminarShareRequest seminarShareRequest);
}
