package com.bkg.coursemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkg.coursemanager.dao.ScoreDao;
import com.bkg.coursemanager.entity.SeminarScore;

/*
 * @author JQQ
 * @date 2018/12/21
 */
@Service
public class ScoreService {
	@Autowired
	private ScoreDao scoreDao;
	public Boolean insertSeminarScore(SeminarScore seminarScore) {
		scoreDao.insertSeminarScore(seminarScore);
		return true;
	}
}
