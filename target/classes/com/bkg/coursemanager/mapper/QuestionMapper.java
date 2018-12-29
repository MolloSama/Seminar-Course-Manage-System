package com.bkg.coursemanager.mapper;

import com.bkg.coursemanager.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用于Seminar相关的Mapper层数据操作接口
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/21
 */
public interface QuestionMapper {

    public List<Question> getAllQuestion(@Param("seminarId") int seminarId, @Param("classId") int classId);

    public Integer askQuestion(@Param("seminarId") int seminarId, @Param("classId") int classId, @Param("studentId") int studentId);

    public Integer scorePresentation(@Param("questionId") int questionId,
                                     @Param("question")Question question);
    
    public List<Question> getQuestionByClassSeminarId(@Param("classSeminarId") int classSeminarId);
}
