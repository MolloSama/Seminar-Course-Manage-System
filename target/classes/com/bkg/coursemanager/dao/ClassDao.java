package com.bkg.coursemanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.mapper.ClassMapper;

@Repository
public class ClassDao {
	@Autowired
	private ClassMapper classMapper;
	
	public Integer deleteClassByClassId(int classId)
	{
		classMapper.deleteClassStudentByClassId(classId);
		return classMapper.deleteClassByClassId(classId);
	}
	
	public Integer selectClassByClassId(int classId)
	{
		return  classMapper.selectClassByClassId(classId);
	}

	public Integer getCourseIdByClassId(int classId)
	{
		return classMapper.getCourseIdByClassId(classId);
	}
} 
