package com.bkg.coursemanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bkg.coursemanager.entity.RoundScore;


public interface RoundScoreMapper {

	List<RoundScore> getRoundScoreByClassIdAndTeamId(@Param("classId") int classId,@Param("teamId") int teamId);
}
