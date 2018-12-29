package com.bkg.coursemanager.dao;

import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用于Student相关的Dao层数据交互实现
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/20
 */

@Repository
public class StudentDao {

    @Autowired
    private StudentMapper studentMapper;

    public List<User> getAllStudent()
    {
        return studentMapper.getAllStudent();

    }

    public User searchStudent(String identity)
    {
        return studentMapper.searchStudent(identity);
    }

    public User searchStudentById(int studentId)
    {
        return studentMapper.searchStudentById(studentId);
    }

    public Integer modifyStudentInfo(int studentId ,User user)
    {
        return studentMapper.modifyStudentInfo(studentId,user);
    }

    public Integer resetStudentPassword(int studentId)
    {
        return studentMapper.resetStudentPassword(studentId);
    }

    public Integer deleteStudent(int studentId)
    {
        return studentMapper.deleteStudent(studentId);
    }

    public Integer accountActive(User user)
    {
        return studentMapper.accountActive(user);
    }

}
