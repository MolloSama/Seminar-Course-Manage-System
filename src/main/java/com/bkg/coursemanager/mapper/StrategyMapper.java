package com.bkg.coursemanager.mapper;

import com.bkg.coursemanager.entity.strategy.ConflictCourseStrategy;
import com.bkg.coursemanager.entity.strategy.CourseMemberLimitStrategy;
import com.bkg.coursemanager.entity.strategy.MemberLimitStrategy;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description 用于组队策略相关的Mapper层数据操作接口
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/25
 */
public interface StrategyMapper {

    List<Integer> getTeamStrategySerial(int courseId);

    Integer getTeamStrategyIdSerial1(int courseId);

    Integer getTeamStrategyIdSerial2(@Param("courseId") int courseId, @Param("serial") int serial);

    List<String> getAndStrategyName(int strategyId);

    Integer getAndStrategyId(@Param("strategyName") String strategyName,@Param("strategyId") int strategyId);

    Integer getMinMember(int memberLimitId);

    Integer getMaxMember(int memberLimitId);

    List<Integer> getCourseMemberLimitId(int orStrategyId);

    Integer getMinCourseMember(int courseMemberLimitId);

    Integer getMaxCourseMember(int courseMemberLimitId);

    Integer getCourseIdByCourseMemberLimitId(int courseMemberLimitId);

    Integer checkCourseMember(@Param("courseId") int courseId, @Param("studentId") int studentId);

    List<Integer> getConflictCourseId(int strategyId);

    List<Integer> getClassIdByStudentId(int studentId);

    List<Integer> getCourseIdByStudentId(int studentId);

    Integer addMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy);

    Integer addMemberLimitStrategyIntoAnd(MemberLimitStrategy memberLimitStrategy);

    Integer addMemberLimitStrategyIntoTeam(MemberLimitStrategy memberLimitStrategy);

    Integer addFirstConflictStrategy(ConflictCourseStrategy conflictCourseStrategy);

    Integer addConflictStrategy(@Param("id") int id, @Param("courseId") int courseId);

    Integer addConflictStrategyIntoTeam(ConflictCourseStrategy conflictCourseStrategy);

    Integer addCourseMemberLimitStrategy(CourseMemberLimitStrategy courseMemberLimitStrategy);

    Integer addCourseMemberLimitStrategyIntoOr(@Param("maxOrId") int maxOrId,
                                               @Param("courseMemberStrategyId") int courseMemberStrategyId);

    Integer addCourseMemberLimitStrategyIntoAnd(@Param("maxAndId") int maxAndId,@Param("maxOrId") int maxOrId);

    Integer getMaxOrId();

    Integer getMaxAndId();
}
