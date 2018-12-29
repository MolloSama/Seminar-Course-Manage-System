package com.bkg.coursemanager.controller;

import com.bkg.coursemanager.entity.Course;
import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.strategy.ConflictCourseStrategy;
import com.bkg.coursemanager.entity.strategy.CourseMemberLimitStrategy;
import com.bkg.coursemanager.entity.strategy.MemberLimitStrategy;
import com.bkg.coursemanager.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/team")
public class StrategyController {

    @Autowired
    StrategyService strategyService;

    /*组队要求设置下
     * 添加小组人数限制*/
    @RequestMapping(value="/strategy/{courseId}/memberlimit",method=RequestMethod.POST)
    public Integer addMemberLimitStrategy(@RequestBody MemberLimitStrategy memberLimitStrategy)
    {
        return strategyService.addMemberLimitStrategy(memberLimitStrategy.getCourseId(),memberLimitStrategy);
    }

    /*组队要求设置下
     * 添加小组内某课程的人数限制*/
    @RequestMapping(value="/strategy/{myCourseId}/coursememberlimit",method=RequestMethod.POST)
    public Integer addCourseMemberLimitStrategy(@RequestBody List<CourseMemberLimitStrategy> courseMemberLimitStrategyList)
    {
        return strategyService.addCourseMemberLimitStrategy(courseMemberLimitStrategyList);
    }

    /*组队要求设置下
     * 添加冲突课程策略*/
    @RequestMapping(value="/strategy/{courseId}/conflict",method=RequestMethod.POST)
    public Integer addConflictStrategy(@RequestBody List<ConflictCourseStrategy> conflictCourseStrategyList)
    {
        List<Integer> courseIdList = new ArrayList<>();
        for(ConflictCourseStrategy conflictCourseStrategy : conflictCourseStrategyList)
        {
            courseIdList.add(conflictCourseStrategy.getCourseId());
        }
        return strategyService.addConflictStrategy(conflictCourseStrategyList.get(0).getId(),courseIdList);
    }

    /*进行组队相关变化后，根据组队策略检查小组当前是否合法*/
    @RequestMapping(value="/strategy/check",method=RequestMethod.GET)
    public Map<String, String> checkTeamStrategy(@RequestBody Team team)
    {
        Map<String, String> judgement = strategyService.handleTeamStrategy(team);

        return judgement;
    }
}
