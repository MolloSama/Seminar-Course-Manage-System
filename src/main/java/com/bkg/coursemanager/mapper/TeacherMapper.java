package com.bkg.coursemanager.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.User;
/*
 * @author JQQ
 * @date 2018/12/21
 */
@Mapper
@Component
public interface TeacherMapper {
	/*
	 * 获得所有教师信息
	 * @return 教师列表
	 * @exception TeacherNotFoundException
	 */
	List<User> listTeacher();
	/*
	 * 管理员创建一个老师
	 */
	Integer insertTeacher(@Param("teacher") User teacher);
	
	User selectTeacherByTeacherId(@Param("teacherId") BigInteger teacherId);

	User selectTeacherByTeacherAccount(@Param("teacherAccount") String teacherAccount);
	
	User selectTeacherByTeacherName(@Param("teacherName") String teacherName);
	
	Integer updateTeacherByTeacherId(@Param("teacherId") BigInteger teacherId,@Param("teacher") User teacher);
	
	void deleteTeacherByTeacherId(@Param("teacherId") BigInteger teacherId);
	
	//管理员将密码重置为“123456”:若默认密码不限定为“123456”，则可将次方法与下面的方法合并
	Integer resetTeacherPasswordByTeacherId(@Param("teacherId") BigInteger teacherId,@Param("teacher") User teacher);
	
	//教师激活账号（传入一个新密码）
	//此方法可以不用，等同于上个方法
	//void updateTeacherPasswordByTeacherId(@Param("teacherId") BigInteger teacherId,@Param("teacher") User teacher);
	
	
	
	
}
