package com.bkg.coursemanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkg.coursemanager.dao.RoundScoreDao;
import com.bkg.coursemanager.entity.RoundScore;

/*
 * @author JQQ
 * @date 2018/12/19
 */
@RestController
@RequestMapping("roundscore")
public class RoundScoreController{
	@Autowired
	private RoundScoreDao roundScoreDao;
	/*
	 * 新建轮次成绩
	 */
	@PostMapping(value="")
	public int createRoundScore() {
		int roundScoreId=22;
		return roundScoreId;
		
	}
	/*
	 * 按roundId和teamId获取讨论课成绩信息
	 * @param roundScoreId
	 */
	@GetMapping("/{classId}/team/{teamId}")
	public List<RoundScore> getRoundScore(@PathVariable ("classId") int classId,@PathVariable ("teamId") int teamId){
		return roundScoreDao.getRoundScoreByClassAndTeamId(classId,teamId);
	}
	
	/*
	 * 按ID修改轮次成绩信息
	 * @param roundScoreId
	 */
	@PostMapping("/{roundScoreId}")
	public void updateRoundScore(@PathVariable ("roundScoreId") int roundScoreId) {
		
	}
}