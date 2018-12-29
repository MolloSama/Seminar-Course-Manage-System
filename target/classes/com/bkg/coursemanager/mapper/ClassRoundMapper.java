package com.bkg.coursemanager.mapper;

import org.apache.ibatis.annotations.Param;

import com.bkg.coursemanager.entity.ClassRound;

public interface ClassRoundMapper {
	ClassRound getClassRoundById(@Param("id") int id);
}
