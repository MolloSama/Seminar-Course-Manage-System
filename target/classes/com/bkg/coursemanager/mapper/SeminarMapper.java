package com.bkg.coursemanager.mapper;

import com.bkg.coursemanager.entity.*;
import com.bkg.coursemanager.entity.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @Description 用于Seminar相关的Mapper层数据操作接口
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/22
 */
public interface SeminarMapper {

    public Integer createSeminar(Seminar seminar);

    public Class getClassBySeminarId(int seminarId);

    public Integer modifySeminarById(@Param("seminarId") int seminarId, Seminar seminar);

    public Integer deleteSeminar(int seminarId);

    public Seminar searchSeminarById(@Param("seminarId") int seminarId);

    public Integer modifyClassSeminarById(@Param("seminarId") int seminarId,@Param("classId") int classId,
                                          @Param("classSeminar")ClassSeminar classSeminar);

    public Integer modifySeminarRound(@Param("seminarId") int seminarId,
                                      @Param("round")Round round);

    public Integer modifySeminarStatus(@Param("seminarId") int seminarId,
                                       @Param("classSeminar")ClassSeminar classSeminar);

    public SeminarScore getSeminarScoreById(@Param("seminarId") int seminarId, @Param("teamId") int teamId);
    
    public ClassSeminar getClassSeminarBySeminarIdAndClassId(@Param("seminarId") int seminarId,
                                                             @Param("classId") int classId);

    public SeminarScore modifySeminarScoreById(@Param("seminarId") int seminarId,@Param("teamId") int teamId,
                                               @Param("seminarScore")SeminarScore seminarScore);

    public List<SeminarScore> getAllSeminarScoreById(int seminarId);
    
    public SeminarScore getSeminarScoreByTeamIdAndClassSeminarId(@Param("teamId") int teamId,@Param("classSeminarId") int classSeminar);
    
   
}
