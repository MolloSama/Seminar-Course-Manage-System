package com.bkg.coursemanager.controller;

import com.bkg.coursemanager.dao.SeminarDao;
import com.bkg.coursemanager.entity.*;
import com.bkg.coursemanager.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description Seminar接口相关Controller
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/22
 */
@RestController
@RequestMapping("/seminar")
public class SeminarConrtoller {

    @Autowired
    private SeminarDao seminarDao;

    /*创建讨论课*/
    @RequestMapping(method=RequestMethod.POST)
    public Integer createSeminar(@RequestBody Seminar seminar) {

        return seminarDao.createSeminar(seminar);
    }

    /*获取讨论课所属的班级*/
    @RequestMapping(value="/{seminarId}/class", method=RequestMethod.GET)
    public Map<String,Object> getClassBySeminarId(@PathVariable("seminarId") Integer seminarId){

        Class klass = seminarDao.getClassBySeminarId(seminarId);

        Map<String,Object> classMap = new HashMap<String,Object>();
        classMap.put("id", klass.getId());
        classMap.put("grade", klass.getGrade());
        classMap.put("serial", klass.getSerial());

        return classMap;
    }

    /*按ID修改讨论课*/
    @RequestMapping(value={"/{seminarId}", "{seminarId}/class/{classId}"},method=RequestMethod.PUT)
    public Integer modifySeminarById(@PathVariable("seminarId") Integer seminarId, @RequestBody Seminar seminar) {

        return seminarDao.modifySeminarById(seminarId,seminar);
    }

    /*按ID删除讨论课*/
    @RequestMapping(value={"/{seminarId}", "{seminarId}/class/{classId}"}, method=RequestMethod.DELETE)
    public Integer deleteSeminar(@PathVariable("seminarId") Integer seminarId){

        return seminarDao.deleteSeminar(seminarId);
    }

    /*按ID获取讨论课*/
    @RequestMapping(value="/{seminarId}",method=RequestMethod.GET)
    public Seminar searchSeminarById(@PathVariable("seminarId") Integer seminarId) {

        return seminarDao.searchSeminarById(seminarId);
    }

    /*按ID修改班级讨论课
    * 设置不同班级讨论课的报告提交时间*/
    @RequestMapping(value={"/{seminarId}/class/{classId}", "{seminarId}/reportddl"}, method=RequestMethod.PUT)
    public Integer modifyClassSeminarById(@PathVariable("seminarId") Integer seminarId,
                                                     @PathVariable("classId") Integer classId,
                                                     @RequestBody ClassSeminar classSeminar){

        return seminarDao.modifyClassSeminarById(seminarId,classId,classSeminar);
    }

    /*设置讨论课轮次*/
    @RequestMapping(value="/{seminarId}/round", method=RequestMethod.PUT)
    public Integer modifySeminarRound(@PathVariable("seminarId") Integer seminarId, @RequestBody Round round){

        return seminarDao.modifySeminarRound(seminarId,round);
    }

    /*设置讨论课状态*/
    @RequestMapping(value="/{seminarId}/status", method=RequestMethod.PUT)
    public Integer modifySeminarStatus(@PathVariable("seminarId") Integer seminarId, @RequestBody ClassSeminar classSeminar){

        return seminarDao.modifySeminarStatus(seminarId,classSeminar);
    }

    /*按讨论课ID查找队伍讨论课的成绩*/
    @RequestMapping(value="/{seminarId}/team/{teamId}/seminarscore", method=RequestMethod.GET)
    public SeminarScore getSeminarScoreById(@PathVariable("seminarId") Integer seminarId,
                                          @PathVariable("teamId") Integer teamId) {

        return seminarDao.getSeminarScoreById(seminarId,teamId);
    }

    /*按讨论课ID更改队伍讨论课的成绩*/
    @RequestMapping(value="/{seminarId}/team/{teamId}/seminarscore", method=RequestMethod.PUT)
    public SeminarScore modifySeminarScoreById(@PathVariable("seminarId") Integer seminarId,
                                            @PathVariable("teamId") Integer teamId,
                                               @RequestBody SeminarScore seminarScore) {

        return seminarDao.modifySeminarScoreById(seminarId,teamId,seminarScore);
    }

    /*按讨论课ID查找讨论课的成绩*/
    @RequestMapping(value="/{seminarId}/seminarscore", method=RequestMethod.GET)
    public List<SeminarScore> getAllSeminarScoreById(@PathVariable("seminarId") Integer seminarId) {

        return seminarDao.getAllSeminarScoreById(seminarId);
    }
    
    /*按讨论课ID和班级ID查找相应的班级讨论课*/
    @RequestMapping(value="/{seminarId}/class/{classId}", method=RequestMethod.GET)
    public ClassSeminar getSeminarByIdAndClassId(@PathVariable("seminarId") Integer seminarId,@PathVariable("classId") Integer classId) {

        return seminarDao.getClassSeminarBySeminarIdAndClassId(seminarId, classId);
    }
    
    /*按班级讨论课ID和队伍ID查找相应的队伍讨论课分数*/
    @RequestMapping(value="/{classSeminarId}/classSeminar/{teamId}/team", method=RequestMethod.GET)
    public SeminarScore getSeminarScoreByTeamIdAndClassSeminarId(@PathVariable("classSeminarId") Integer classSeminarId,@PathVariable("teamId") Integer teamId)
    {
    	return seminarDao.getSeminarScoreByTeamIdAndClassSeminarId(teamId, classSeminarId);
    }
}
