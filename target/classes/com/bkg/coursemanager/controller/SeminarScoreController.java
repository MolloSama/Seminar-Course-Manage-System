package com.bkg.coursemanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkg.coursemanager.dao.ClassSeminarDao;
import com.bkg.coursemanager.dao.SeminarScoreDao;
import com.bkg.coursemanager.entity.ClassSeminar;
import com.bkg.coursemanager.entity.SeminarScore;
import com.bkg.coursemanager.service.ScoreService;

/*
 * @author JQQ
 * @date 2018/12/19
 */
@RestController
@RequestMapping("/seminarscore")
public class SeminarScoreController {
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private SeminarScoreDao seminarScoreDao;
	/*
	 * 新建讨论课成绩
	 */
	@PostMapping("")
	public void createSeminarScore(@RequestBody SeminarScore seminarScore) {
		scoreService.insertSeminarScore(seminarScore);
	}
	
	@GetMapping("/{classSeminarId}")
	public SeminarScore selectSeminarScoreByClassSeminarId(@PathVariable("classSeminarId") int classSeminarId) {
		return seminarScoreDao.selectSeminarScoreByClassSeminarId(classSeminarId);
	}
	
	@GetMapping("/{classId}/team/{teamId}")
	List<SeminarScore> selectSeminarScoreByClassIdAndTeamId(@PathVariable("classId") int classId,@PathVariable("teamId") int teamId)
	{
		return seminarScoreDao.selectSeminarScoreByClassIdAndTeamId(classId, teamId);
	}
}
