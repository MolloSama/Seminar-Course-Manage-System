package com.bkg.coursemanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.mapper.UserMapper;

@Repository
public class UserDao {
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByAccount(String account)
	{
		User student=userMapper.getStudentByAccount(account);
	    User teacher=userMapper.getStudentByAccount(account);
		if(student!=null)return student;
		else if(teacher!=null)return teacher;
		else return null;
	}
	
	public Integer updateUserPasswordByAccount(User user)
	{
		Integer record=userMapper.updateStudentPasswordByAccount(user);
		record+=userMapper.updateTeacherPasswordByAccount(user);
		return record;
	}
	
	public Integer updateUserEmailByAccount(User user)
	{
		Integer record=userMapper.updateStudentEmailByAccount(user);
		record+=userMapper.updateTeacherEmailByAccount(user);
		return record;
	}
}
