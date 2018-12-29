package com.bkg.coursemanager.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkg.coursemanager.dao.TeacherDao;
import com.bkg.coursemanager.entity.User;

/*
 * @author JQQ
 * @date 2018/12/21
 */
@Service
public class TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	public List<User> listTeacher(){
		return teacherDao.listTeacher();
	}
	/*
	 * 管理员创建一个老师
	 */
	public BigInteger insertTeacher(User teacher) {
		teacherDao.insertTeacher(teacher);
		return BigInteger.valueOf(teacher.getId());
	}
	
	public User selectTeacherByTeacherId(BigInteger teacherId) {
		return teacherDao.selectTeacherByTeacherId(teacherId);
	}

	public User selectTeacherByTeacherAccount(String teacherAccount) {
		User teacher=teacherDao.selectTeacherByTeacherAccount(teacherAccount);
		if(null==teacher) {
			//抛出TeacherNotFoundException
		}
		return teacher;
	}
	
	public User selectTeacherByTeacherName(String teacherName) {
		User teacher=teacherDao.selectTeacherByTeacherName(teacherName);
		if(null==teacher) {
			//抛出TeacherNotFoundException
		}
		return teacher;
		
	}
	
	public Boolean updateTeacherByTeacherId(BigInteger teacherId,User teacher) {
		Boolean res=teacherDao.updateTeacherByTeacherId(teacherId, teacher);
		return res;
	}
	
	public void deleteTeacherByTeacherId(BigInteger teacherId) {
		teacherDao.deleteTeacherByTeacherId(teacherId);
	}
	
	//管理员将密码重置为“123456”:若默认密码不限定为“123456”，则可将次方法与下面的方法合并
	public Boolean resetTeacherPasswordByTeacherId(BigInteger teacherId,User teacher) {
		Boolean res=teacherDao.resetTeacherPasswordByTeacherId(teacherId, teacher);
		return res;
	}
}
