package com.bkg.coursemanager.dao;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.ClassSeminar;
import com.bkg.coursemanager.mapper.ClassSeminarMapper;

/*
 * @author JQQ
 * @date 2018/12/21
 * 无对应service
 */
@Component
public class ClassSeminarDao {
	@Autowired
	private ClassSeminarMapper classSeminarMapper;
	
	/*
	 * 插入一条classSeminar记录
	 */
	public Integer insertClassSeminar(ClassSeminar classSeminar) {
		return classSeminarMapper.insertClassSeminar(classSeminar);
	}
	/*
	 * 根据classSeminarId查询
	 */
	public ClassSeminar selectClassSeminarById(int classSeminarId) {
		return classSeminarMapper.selectClassSeminarById(classSeminarId);
	}
	/*
	 * 根据seminarId和classId查询所有信息
	 * 主要是attendanceId要用到
	 */
	public ClassSeminar selectClassSemianrByForeignKey(BigInteger seminarId,BigInteger classId){
		return classSeminarMapper.selectClassSemianrByForeignKey(seminarId, classId);
	}
	
	/*
	 * update,更新report_ddl,status等,update语句中不更新klass_id和semianr_id.因为这两项再insert的时候就已经存在并不会有修改的需求
	 */
	public void updateClassSeminarById(BigInteger classSeminarId) {
		classSeminarMapper.updateClassSeminarById(classSeminarId);
	}
	public void updateClassSeminarDDLById(BigInteger classSeminarid) {
		classSeminarMapper.updateClassSeminarDDLById(classSeminarid);
	}
	public void updateClassSeminarStatusById(BigInteger classSeminarid) {
		classSeminarMapper.updateClassSeminarStatusById(classSeminarid);
	}
	
	/*
	 * delete
	 */
	public void deleteClassSeminarById(BigInteger classSeminarId) {
		classSeminarMapper.deleteClassSeminarById(classSeminarId);
	}
	
	

}
