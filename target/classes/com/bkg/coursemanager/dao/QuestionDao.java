package com.bkg.coursemanager.dao;

import com.bkg.coursemanager.entity.Question;
import com.bkg.coursemanager.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用于Question相关的Dao层数据交互实现
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/21
 */

@Repository
public class QuestionDao {

    @Autowired
    QuestionMapper questionMapper;

    public List<Question> getAllQuestion(int seminarId, int classId)
    {
        return questionMapper.getAllQuestion(seminarId,classId);
    }

    public Integer askQuestion(int seminarId,int classId,int studentId)
    {
        return questionMapper.askQuestion(seminarId,classId,studentId);
    }

    public Integer scorePresentation(int questionId,Question question)
    {
        return questionMapper.scorePresentation(questionId,question);
    }
    
    public List<Question> getQuestionByClassSeminarId(int classSeminarId)
    {
    	return questionMapper.getQuestionByClassSeminarId(classSeminarId);
    }
}
