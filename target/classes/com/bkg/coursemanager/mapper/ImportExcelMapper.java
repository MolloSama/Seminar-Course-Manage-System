package com.bkg.coursemanager.mapper;

import com.bkg.coursemanager.entity.Account;
import com.bkg.coursemanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用于EXCEL导入的Mapper
 * @author Weilun Zhang
 * @version v2.0
 * @date 2018/12/22
 */

@Mapper
public interface ImportExcelMapper {

    void addStudent(User user);

    int selectByAccount(String account);

    int getStudentId(String account);

    void addClassStudent(@Param("classId") int classId, @Param("studentId") int studentId, @Param("courseId") int courseId);
}
