package com.bkg.coursemanager.dao;

import com.bkg.coursemanager.entity.*;
import com.bkg.coursemanager.entity.Class;
import com.bkg.coursemanager.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用于Seminar相关的Dao层数据交互实现
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/21
 */

@Repository
public class SeminarDao {

    @Autowired
    private SeminarMapper seminarMapper;

    public Integer createSeminar(Seminar seminar)
    {
        return seminarMapper.createSeminar(seminar);
    }

    public Class getClassBySeminarId(int seminarId)
    {
        return seminarMapper.getClassBySeminarId(seminarId);
    }

    public Integer modifySeminarById(int seminarId,Seminar seminar)
    {
        return seminarMapper.modifySeminarById(seminarId,seminar);
    }

    public Integer deleteSeminar(int seminarId)
    {
        return seminarMapper.deleteSeminar(seminarId);
    }

    public Seminar searchSeminarById(int seminarId)
    {
        return seminarMapper.searchSeminarById(seminarId);
    }

    public Integer modifyClassSeminarById(int seminarId,int classId,ClassSeminar classSeminar)
    {
        return seminarMapper.modifyClassSeminarById(seminarId,classId,classSeminar);
    }

    public Integer modifySeminarRound(int seminarId, Round round)
    {
        return seminarMapper.modifySeminarRound(seminarId,round);
    }

    public Integer modifySeminarStatus(int seminarId, ClassSeminar classSeminar)
    {
        return seminarMapper.modifySeminarStatus(seminarId,classSeminar);
    }

    public SeminarScore getSeminarScoreById(int seminarId, int teamId)
    {
        return seminarMapper.getSeminarScoreById(seminarId,teamId);
    }

    public SeminarScore modifySeminarScoreById(int seminarId,int teamId,SeminarScore seminarScore)
    {
        return seminarMapper.modifySeminarScoreById(seminarId,teamId,seminarScore);
    }
    
    public ClassSeminar getClassSeminarBySeminarIdAndClassId(int seminarId,int classId)
    {
    	return seminarMapper.getClassSeminarBySeminarIdAndClassId(seminarId, classId);
    }

    public List<SeminarScore> getAllSeminarScoreById(int seminarId)
    {
        return seminarMapper.getAllSeminarScoreById(seminarId);
    }
    
    public SeminarScore getSeminarScoreByTeamIdAndClassSeminarId(int teamId,int classSeminarId)
    {
    	return seminarMapper.getSeminarScoreByTeamIdAndClassSeminarId(teamId, classSeminarId);
    }

}
