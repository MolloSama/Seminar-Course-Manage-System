package com.bkg.coursemanager.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.ClassSeminar;
/*
 * @author JQQ
 * @date 2018/12/21
 */
@Mapper
@Component
public interface ClassSeminarMapper {
	/*
	 * 插入一条classSeminar记录
	 */
	Integer insertClassSeminar(@Param("classSeminar") ClassSeminar classSeminar);
	/*
	 * 根据classSeminarId查询
	 */
	ClassSeminar selectClassSeminarById(@Param("classSeminarId") int classSeminarId);
	/*
	 * 根据seminarId和classId查询,应该只能查到一条记录，因为一个班级下的一个讨论课实际只有一个
	 * 主要是attendanceId要用到
	 */
	ClassSeminar selectClassSemianrByForeignKey(@Param("seminarId") BigInteger seminarId,@Param("classId") BigInteger classId);
	
	
	/*
	 * update,更新report_ddl,status等,update语句中不更新klass_id和semianr_id.因为这两项再insert的时候就已经存在并不会有修改的需求
	 */
	void updateClassSeminarById(@Param("classSeminarId") BigInteger classSeminarId);
	
	void updateClassSeminarDDLById(@Param("classSeminarId") BigInteger classSeminarId);
	
	void updateClassSeminarStatusById(@Param("classSeminarId") BigInteger classSeminarId);
	/*
	 * delete
	 */
	void deleteClassSeminarById(@Param("classSeminarId") BigInteger classSeminarId);
}
