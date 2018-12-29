package com.bkg.coursemanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.SeminarScore;
import com.bkg.coursemanager.mapper.SeminarScoreMapper;

/*
 * @author JQQ
 * @date 2018/12/21
 */
@Component
public class ScoreDao {
	@Autowired
	SeminarScoreMapper seminarScoreMapper;
	

	
	public Boolean insertSeminarScore(SeminarScore seminarScore) {
		Integer res=seminarScoreMapper.insertSeminarScore(seminarScore);
		if(res>0) {
			return true;
		}
		else {
			return false;
			//抛出SeminarScoreNotFoundExcetion
		}
	}

}
