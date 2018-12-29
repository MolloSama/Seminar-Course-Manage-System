package com.bkg.coursemanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.entity.SeminarScore;
import com.bkg.coursemanager.mapper.SeminarScoreMapper;

@Repository
public class SeminarScoreDao {
	 @Autowired
	 private SeminarScoreMapper seminarScoreMapper;
	 
	 public SeminarScore selectSeminarScoreByClassSeminarId(int classSeminarId)
	 {
		 return seminarScoreMapper.selectSeminarScoreByClassSeminarId(classSeminarId);
	 }
	 
	 public List<SeminarScore> selectSeminarScoreByClassIdAndTeamId(int classId,int teamId)
	 {
		 return seminarScoreMapper.selectSeminarScoreByClassIdAndTeamId(classId, teamId);
	 }
}
