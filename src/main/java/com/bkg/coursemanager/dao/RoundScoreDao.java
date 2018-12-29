package com.bkg.coursemanager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.entity.RoundScore;
import com.bkg.coursemanager.mapper.RoundScoreMapper;

@Repository
public class RoundScoreDao {

	@Autowired
	private RoundScoreMapper roundScoreMapper;
	
	public List<RoundScore> getRoundScoreByClassAndTeamId(int classId,int teamId) {
		return roundScoreMapper.getRoundScoreByClassIdAndTeamId(classId,teamId);
	}

}
