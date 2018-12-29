package com.bkg.coursemanager.controller;


import com.bkg.coursemanager.dao.QuestionDao;
import com.bkg.coursemanager.entity.Question;
import com.bkg.coursemanager.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description Question接口相关Controller
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/21
 */

@RestController
public class QuestionController {


    @Autowired
    QuestionDao questionDao;

    /*本节讨论课所有提问*/
    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question", method=RequestMethod.GET)
    public List<Question> getAllQuestion(@PathVariable("seminarId") Integer seminarId,
                                         @PathVariable("classId") Integer classId) {

        return questionDao.getAllQuestion(seminarId,classId);
    }

    //此处存在问题
    /*提问*/
    @RequestMapping(value="/seminar/{seminarId}/class/{classId}/question", method=RequestMethod.POST)
    public Integer askQuestion(@PathVariable("seminarId") Integer seminarId,
                               @PathVariable("classId") Integer classId,
                               @RequestParam Integer studentId) {

        return questionDao.askQuestion(seminarId,classId,studentId);
    }

    /*给提问打分，修改提问打分*/
    @RequestMapping(value="/question/{questionId}", method=RequestMethod.PUT)
    public Integer scorePresentation(@PathVariable("questionId") Integer questionId, @RequestBody Question question){

        return questionDao.scorePresentation(questionId,question);
    }
    
    /*根据classSeminarId获得相应的question*/
    @RequestMapping(value="/question/classSeminar/{classSeminarId}", method=RequestMethod.GET)
    public List<Question> getQuestionByClassSeminarId(@PathVariable("classSeminarId") Integer classSeminarId)
    {
    	return questionDao.getQuestionByClassSeminarId(classSeminarId);
    }
}
