package com.bkg.coursemanager.service;

import com.bkg.coursemanager.dao.CourseDao;
import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.Class;
import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.entity.strategy.ConflictCourseStrategy;
import com.bkg.coursemanager.entity.strategy.CourseMemberLimitStrategy;
import com.bkg.coursemanager.entity.strategy.MemberLimitStrategy;
import com.bkg.coursemanager.mapper.StrategyMapper;
import com.bkg.coursemanager.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 用于处理策略表的Service层业务逻辑处理
 * @author Weilun Zhang
 * @version v2.0
 * @date 2018/12/26
 */

@Service
public class StrategyService {

    @Autowired
    private StrategyMapper strategyMapper;

    @Autowired
    private CourseDao courseDao;

    public Map<String, String> handleTeamStrategy(Team team)
    {
        //获取当前队伍所在课程的ID
        int courseId = team.getCourse().getId();

        //获取当前小组所参与的课程的所有组队策略序号
        List<Integer> strategySerials = strategyMapper.getTeamStrategySerial(courseId);

        //根据不同的组队策略序号分情况讨论
        for(int strategySerial : strategySerials)
        {
            /*
            当前需求中跨班组队已移除

            //策略序号3：根据xxx选课分组，不允许跨班

            if (strategySerial == 3) {
                //获取冲突课程的list
                List<Integer> conflictCourseList =
                        strategyMapper.getConflictCourseId(strategyMapper.getTeamStrategyIdSerial2(courseId));

                for (int conflictCourse : conflictCourseList) {
                    for (Class klass : courseDao.getClassByCourseId(conflictCourse)) {
                        for (User member : team.getMembers()) {
                            for (int klassId : strategyMapper.getClassIdByStudentId(member.getId())) {
                                if (klassId == klass.getId()) continue;
                                else return 0;
                            }
                        }
                    }
                }
            }*/

            //策略序号1：每组人数限制及每组内课程人数限制
            if (strategySerial == 1) {
                //获取And策略表的id
                int andStrategyId = strategyMapper.getTeamStrategyIdSerial1(courseId);
                List<String> andStrategyName =
                        strategyMapper.getAndStrategyName(andStrategyId);

                //若只存在一种策略，则必是MemberLimitStrategy
                if(andStrategyName.size()==1)
                {
                    int memberLimitId =
                            strategyMapper.getAndStrategyId("MemberLimitStrategy", andStrategyId);
                    int minMember = strategyMapper.getMinMember(memberLimitId);
                    int maxMember = strategyMapper.getMaxMember(memberLimitId);

                    if(((team.getMembers().size()+1)<minMember)||((team.getMembers().size()+1)>maxMember))
                    {
                        Map<String, String> result = new HashMap<>();
                        result.put("status","0");
                        result.put("reason","队伍人数不满足限制要求");
                        return result;
                    }
                }

                else{
                    int orStrategyId = strategyMapper.getAndStrategyId("TeamOrStrategy",andStrategyId);
                    int memberLimitId =
                            strategyMapper.getAndStrategyId("MemberLimitStrategy", andStrategyId);

                    //每组内课程最大最小人数限制
                    int minMember = strategyMapper.getMinMember(memberLimitId);
                    int maxMember = strategyMapper.getMaxMember(memberLimitId);

                    if(((team.getMembers().size()+1)<minMember)||((team.getMembers().size()+1)>maxMember))
                    {
                        Map<String, String> result = new HashMap<>();
                        result.put("status","0");
                        result.put("reason","队伍人数不满足限制要求");
                        return result;
                    }

                    //每组内课程人数限制
                    List<Integer> courseMemberLimitId = strategyMapper.getCourseMemberLimitId(orStrategyId);

                    boolean orFlag = true;
                    int flagCount = 0;
                    for(int courseMemberLimit : courseMemberLimitId)
                    {
                        int limitCourseId = strategyMapper.getCourseIdByCourseMemberLimitId(courseMemberLimit);

                        //统计小组内某门课程的选修人数
                        int count = 0;
                        //判断组长是否选修某门限制课程
                        if(strategyMapper.checkCourseMember(limitCourseId, team.getLeader().getId())!=0)
                            count++;

                        //遍历members，统计每一个组员是否选修某门限制课程
                        for(User student : team.getMembers())
                        {
                            if(strategyMapper.checkCourseMember(limitCourseId, student.getId())!=0)
                                count++;
                        }

                        //每组内某门课程的最大最小人数限制
                        int minCourseMember = strategyMapper.getMinCourseMember(courseMemberLimit);
                        int maxCourseMember = strategyMapper.getMaxCourseMember(courseMemberLimit);
                        if((count<minCourseMember)||(count>maxCourseMember)) {
                            orFlag = false;
                        }
                        else flagCount++;
                    }
                    if(flagCount>0) orFlag=true;

                    if(!orFlag)
                    {
                        Map<String, String> result = new HashMap<>();
                        result.put("status","0");
                        result.put("reason","队伍内课程选修人数不满足限制要求");
                        return result;
                    }
                }
            }

            //策略序号3：选xxx课程和xxx课程不能在同一个组
            else{
                //获取冲突课程的list
                List<Integer> conflictCourseList =
                        strategyMapper.getConflictCourseId(strategyMapper.getTeamStrategyIdSerial2(courseId, strategySerial));

                boolean flag1 = false, flag2 = false;
                for (User member : team.getMembers()) {
                    for (int memberCourse : strategyMapper.getCourseIdByStudentId(member.getId())) {
                        if (memberCourse == conflictCourseList.get(0)) flag1 = true;
                        else if (memberCourse == conflictCourseList.get(1)) flag2 = true;
                    }
                }

                if (flag1 == true && flag2 == true)
                {
                    Map<String, String> result = new HashMap<>();
                    result.put("status", "0");
                    result.put("reason","队伍中成员不满足冲突课程限制要求");
                    return result;
                }
            }

        }
        Map<String, String> result = new HashMap<>();
        result.put("status","1");
        result.put("reason","合法");
        return result;
    }


    public Integer addConflictStrategy(int courseId,List<Integer> courseIdList)
    {
        ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy();
        conflictCourseStrategy.setCourseId(courseIdList.get(0));
        strategyMapper.addFirstConflictStrategy(conflictCourseStrategy);
        courseIdList.remove(0);

        for(int conflictCourseId : courseIdList)
        {
            strategyMapper.addConflictStrategy(conflictCourseStrategy.getId(),conflictCourseId);
        }

        conflictCourseStrategy.setCourseId(courseId);

        return strategyMapper.addConflictStrategyIntoTeam(conflictCourseStrategy);
    }

    public Integer addMemberLimitStrategy(int courseId, MemberLimitStrategy memberLimitStrategy)
    {
        strategyMapper.addMemberLimitStrategy(memberLimitStrategy);
        strategyMapper.addMemberLimitStrategyIntoAnd(memberLimitStrategy);

        memberLimitStrategy.setMax_member(courseId);
        strategyMapper.addMemberLimitStrategyIntoTeam(memberLimitStrategy);
        return memberLimitStrategy.getId();
    }

    public Integer addCourseMemberLimitStrategy(List<CourseMemberLimitStrategy> courseMemberLimitStrategyList)
    {
        int maxOrId = strategyMapper.getMaxOrId()+1;
        int maxAndId = strategyMapper.getMaxAndId();

        for(CourseMemberLimitStrategy courseMemberLimitStrategy : courseMemberLimitStrategyList)
        {
            strategyMapper.addCourseMemberLimitStrategy(courseMemberLimitStrategy);
            int courseMemberStrategyId =courseMemberLimitStrategy.getId();
            strategyMapper.addCourseMemberLimitStrategyIntoOr(maxOrId,courseMemberStrategyId);
        }

        return strategyMapper.addCourseMemberLimitStrategyIntoAnd(maxAndId,maxOrId);
    }
}
