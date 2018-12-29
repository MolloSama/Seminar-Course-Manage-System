package com.bkg.coursemanager.mapper;

import org.apache.ibatis.annotations.Param;

import com.bkg.coursemanager.entity.User;

public interface UserMapper {
	User getStudentById(@Param("id") int id);
	User getTeacherById(@Param("id") int id);
	User getStudentByAccount(@Param("account") String account);
	User getTeacherByAccount(@Param("account") String account);
	Integer updateStudentPasswordByAccount(User user);
	Integer updateTeacherPasswordByAccount(User user);
	Integer updateStudentEmailByAccount(User user);
	Integer updateTeacherEmailByAccount(User user);
}
