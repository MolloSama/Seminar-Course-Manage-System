package com.bkg.coursemanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bkg.coursemanager.entity.Team;
import com.bkg.coursemanager.entity.TeamValidRequest;
import com.bkg.coursemanager.entity.User;

public interface TeamMapper {
	Integer deleteClassTeamByCourseId(@Param("courseId") int courseId);
	List<User> getMembersByTeamIdAndCourseIdAndLeaderId(@Param("teamId") int teamId,@Param("courseId") int courseId,@Param("leaderId") int leaderId);
	Team getTeamByTeamId(@Param("teamId") int teamId);
	List<Team> getAllTeamByCourseId(@Param("courseId") int courseId);
	List<Team> getTeamByClassId(@Param("classId") int classId);
	TeamValidRequest getTeamValidRequestById(@Param("TeamValidRequestId") int TeamValidRequestId);
	Integer addTeam(Team team);
	Integer addTeamValidRequest(TeamValidRequest teamValidRequest);
	Integer addClassStudent(@Param("classId") int classId,@Param("studentId") int studentId,@Param("courseId") int courseId,@Param("teamId") int teamId);
	Integer updateTeamByTeamId(Team team);
	Integer addTeamMember(@Param("teamId") int teamId,@Param("memberId") int memberId,@Param("courseId") int courseId);
	Integer updateTeamValidRequest(TeamValidRequest teamValidRequest);
	Integer updateClassStudentTeamIdByCourseIdAndStudentId(@Param("teamId") int teamId,@Param("courseId") int courseId,@Param("studentId") int studentId);
	Integer updateClassStudentTeamIdByTeamId(@Param("oldTeamId") int oldTeamId,@Param("newTeamId") int newTeamId);
	Integer teamShare(@Param("subCourseId") int subCourseId,@Param("mainCourseId") int mainCourseId);
	Integer deleteTeamByTeamId(@Param("teamId") int teamId);
}
