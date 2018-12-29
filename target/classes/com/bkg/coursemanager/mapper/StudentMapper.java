package com.bkg.coursemanager.mapper;

import com.bkg.coursemanager.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 用于Student相关的Mapper层数据操作接口
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/20
 */
public interface StudentMapper {

    public List<User> getAllStudent();

    public User searchStudent(String identity);

    public User searchStudentById(int studentId);

    public Integer modifyStudentInfo(@Param("studentId") int studentId ,@Param("user") User user);

    public Integer resetStudentPassword(int studentId);

    public Integer deleteStudent(int studentId);

    public Integer accountActive(User user);


}
