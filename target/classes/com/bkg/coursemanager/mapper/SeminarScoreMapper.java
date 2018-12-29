package com.bkg.coursemanager.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.SeminarScore;


@Mapper
@Component
/**
 * @author JQQ
 * @date 2018/12/21
*/

public interface SeminarScoreMapper {
	Integer insertSeminarScore(@Param("seminarScore") SeminarScore seminarScore); 
    //每次展示、报告、书面报告各自打分或总的修改一次讨论课成绩都要调用这个；返回Integer?
    Integer updateSeminarScore(@Param("seminarScore") SeminarScore seminarScore);
    
    SeminarScore selectSeminarScoreByClassSeminarId(@Param("classSeminarId") int classSeminarId);
    
    List<SeminarScore> selectSeminarScoreByClassIdAndTeamId(@Param("classId") int classId,@Param("teamId") int teamId);
}
