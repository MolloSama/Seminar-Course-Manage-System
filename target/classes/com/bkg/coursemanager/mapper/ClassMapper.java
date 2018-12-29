package com.bkg.coursemanager.mapper;

import org.apache.ibatis.annotations.Param;

public interface ClassMapper {
	public Integer deleteClassByClassId(@Param("classId") int classId);
	
	public Integer deleteClassStudentByClassId(@Param("classId") int classId);

	public Integer selectClassByClassId(@Param("classId") int classId);

	public Integer getCourseIdByClassId(@Param("classId") int classId);
	
	
}
