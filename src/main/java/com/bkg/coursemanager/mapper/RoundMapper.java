package com.bkg.coursemanager.mapper;

import com.bkg.coursemanager.entity.Round;
import com.bkg.coursemanager.entity.RoundScore;
import com.bkg.coursemanager.entity.Seminar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用于Round相关的Mapper层数据操作接口
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/21
 */
public interface RoundMapper {

    public List<Seminar> getAllSeminar(int roundId);

    public Round getRoundById(int roundId);

    public Integer modifyRoundById(@Param("roundId") int roundId, @Param("round") Round round);

    public Integer createRound(Round round);

    public List<RoundScore> searchScoreByRoundId(int roundId);
    
    public List<Round> getRoundByClassId(int classId);

    public RoundScore getRoundScoreById(@Param("roundId") int roundId, @Param("teamId") int teamId);


    public Integer updateRoundScore(@Param("roundId") int roundId, @Param("teamId") int teamId,
                                    @Param("roundScore")RoundScore roundScore);


}
