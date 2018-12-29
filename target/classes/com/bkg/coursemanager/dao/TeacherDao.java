package com.bkg.coursemanager.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.mapper.TeacherMapper;

/*
 * @author JQQ
 * @date 2018/12/21
 */
@Component
public class TeacherDao {
	@Autowired
	private TeacherMapper teacherMapper;
	
	public List<User> listTeacher(){
		return teacherMapper.listTeacher();
	}
	/*
	 * 管理员创建一个老师
	 */
	public BigInteger insertTeacher(User teacher) {
		Integer res=teacherMapper.insertTeacher(teacher);
		if(res>0) {
			return BigInteger.valueOf(teacher.getId());
		}
		else {
			return new BigInteger("-1");
		}
	}
	
	public User selectTeacherByTeacherId(BigInteger teacherId) {
		return teacherMapper.selectTeacherByTeacherId(teacherId);
	}

	public User selectTeacherByTeacherAccount(String teacherAccount) {
		User teacher=teacherMapper.selectTeacherByTeacherAccount(teacherAccount);
		if(null==teacher) {
			//抛出TeacherNotFoundException
		}
		return teacher;
	}
	
	public User selectTeacherByTeacherName(String teacherName) {
		User teacher=teacherMapper.selectTeacherByTeacherName(teacherName);
		if(null==teacher) {
			//抛出TeacherNotFoundException
		}
		return teacher;
		
	}
	
	public Boolean updateTeacherByTeacherId(BigInteger teacherId,User teacher) {
		Integer res=teacherMapper.updateTeacherByTeacherId(teacherId, teacher);
		if(res>0) {
			return true;
		}
		else {
			//抛出TeacherNotFoundException
			return false;
		}
	}
	
	public void deleteTeacherByTeacherId(@Param("teacherId") BigInteger teacherId) {
		teacherMapper.deleteTeacherByTeacherId(teacherId);
	}
	
	//管理员将密码重置为“123456”:若默认密码不限定为“123456”，则可将次方法与下面的方法合并
	public Boolean resetTeacherPasswordByTeacherId(@Param("teacherId") BigInteger teacherId,@Param("teacher") User teacher) {
		Integer res=teacherMapper.resetTeacherPasswordByTeacherId(teacherId, teacher);
		if(res>0) {
			return true;
		}
		else {
			//抛出TeacherNotFoundException
			return false;
		}
	}
}
