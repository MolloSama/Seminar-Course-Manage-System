package com.bkg.coursemanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkg.coursemanager.dao.RoundDao;
import com.bkg.coursemanager.entity.Round;
import com.bkg.coursemanager.entity.RoundScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bkg.coursemanager.entity.Seminar;


/**
 * @Description Round接口相关Controller
 * @author Weilun Zhang
 * @version v1.2
 * @date 2018/12/21
 */
@RestController
@RequestMapping("/round")
public class RoundController {

	@Autowired
	private RoundDao roundDao;

	/*按ID获取某轮的所有讨论课*/
	@RequestMapping(value="/{roundId}/seminar",method=RequestMethod.GET)
	public List<Map<String,Object>> getAllSeminar(@PathVariable("roundId") Integer roundId) {
		List<Map<String,Object>> seminarList = new ArrayList<Map<String,Object>>();

		List<Seminar> seminars = roundDao.getAllSeminar(roundId);

		for(Seminar seminar : seminars)
		{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("id", seminar.getId());
			dataMap.put("topic", seminar.getTopic());
			dataMap.put("order", seminar.getOrder());
			seminarList.add(dataMap);
		}

		return seminarList;
	}

	/*按ID获取轮次*/
	@RequestMapping(value="/{roundId}",method=RequestMethod.GET)
	public Round getRoundById(@PathVariable("roundId") Integer roundId) {
		
		Round round=roundDao.getRoundById(roundId);
		round.setOrderName(round.getOrderName());
		return round;
	}

	/*按ID修改轮次*/
	@RequestMapping(value="/{roundId}",method=RequestMethod.PUT)
	public Integer modifyRoundById(@PathVariable("roundId") Integer roundId, @RequestBody Round round) {

		return roundDao.modifyRoundById(roundId,round);
	}

	/*创建轮次*/
	@RequestMapping(method=RequestMethod.POST)
	public Integer createRound(@RequestBody Round round) {

		return roundDao.createRound(round);
	}

	/*按轮次ID查找所有的成绩*/
	@RequestMapping(value="/{roundId}/roundscore", method=RequestMethod.GET)
	public List<RoundScore> searchScoreByRoundId(@PathVariable("roundId") Integer roundId) {

		return roundDao.searchScoreByRoundId(roundId);
	}

	/*按ID获取轮次成绩信息*/
	@RequestMapping(value="/{roundId}/team/{teamId}/roundscore", method=RequestMethod.GET)
	public RoundScore getRoundScoreById(@PathVariable("roundId") Integer roundId,
											  @PathVariable("teamId") Integer teamId) {

		return roundDao.getRoundScoreById(roundId,teamId);
	}

	/*按ID修改轮次成绩信息*/
	@RequestMapping(value="/{roundId}/team/{teamId}/roundscore", method=RequestMethod.PUT)
	public Integer updateRoundScore(@PathVariable("roundId") Integer roundId,
									@PathVariable("teamId") Integer teamId,
									@RequestBody RoundScore roundScore) {

		return roundDao.updateRoundScore(roundId,teamId,roundScore);
	}
	
	/*按班级ID查找相应的轮次信息*/
	@RequestMapping(value="/{classId}/class", method=RequestMethod.GET)
	public List<Round> updateRoundScore(@PathVariable("classId") Integer classId
									 ){
		return roundDao.getRoundByClassId(classId);
	}

}
