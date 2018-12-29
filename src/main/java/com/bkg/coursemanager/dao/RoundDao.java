package com.bkg.coursemanager.dao;

import com.bkg.coursemanager.entity.Round;
import com.bkg.coursemanager.entity.RoundScore;
import com.bkg.coursemanager.entity.Seminar;
import com.bkg.coursemanager.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description 用于Round相关的Dao层数据交互实现
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/21
 */

@Repository
public class RoundDao {

    @Autowired
    private RoundMapper roundMapper;

    public List<Seminar> getAllSeminar(int roundId)
    {
        return roundMapper.getAllSeminar(roundId);
    }

    public Round getRoundById(int roundId)
    {
        return roundMapper.getRoundById(roundId);
    }

    public Integer modifyRoundById(int roundId, Round round)
    {
        return roundMapper.modifyRoundById(roundId,round);
    }

    public Integer createRound(Round round)
    {
        return roundMapper.createRound(round);
    }

    public List<RoundScore> searchScoreByRoundId(int roundId)
    {
        return roundMapper.searchScoreByRoundId(roundId);
    }
    
    public List<Round> getRoundByClassId(int classId)
    {
    	return roundMapper.getRoundByClassId(classId);
    }

    public RoundScore getRoundScoreById(int roundId,int teamId)
    {
        return roundMapper.getRoundScoreById(roundId,teamId);
    }

    public Integer updateRoundScore(int roundId,int teamId,RoundScore roundScore)
    {
        return roundMapper.updateRoundScore(roundId,teamId,roundScore);
    }


}
