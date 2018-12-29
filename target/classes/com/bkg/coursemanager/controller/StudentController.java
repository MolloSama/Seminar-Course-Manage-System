package com.bkg.coursemanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkg.coursemanager.dao.StudentDao;
import com.bkg.coursemanager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description Student接口相关Controller
 * @author Weilun Zhang
 * @version v1.2
 * @date 2018/12/21
 */
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentDao studentDao;

    /*管理员获得所有学生信息*/
	@RequestMapping(method=RequestMethod.GET)
	public List<Map<String,Object>> getAllStudent(){
		List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();

		List<User> users = studentDao.getAllStudent();

		for(User user : users)
		{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("id", user.getId());
			dataMap.put("account", user.getAccount());
			dataMap.put("name", user.getName());
			dataMap.put("email", user.getEmail());
			userList.add(dataMap);
		}

		return userList;
	}

    /*管理员根据姓名或学生号查询学生*/
	@RequestMapping(value="/searchstudent", method=RequestMethod.GET)
	public Map<String,Object> searchStudent(@RequestParam String identity){

		User user = studentDao.searchStudent(identity);

		Map<String,Object> userMap = new HashMap<String,Object>();
		userMap.put("id", user.getId());
		userMap.put("account", user.getAccount());
		userMap.put("name", user.getName());
		userMap.put("email", user.getEmail());

		return userMap;
	}
	

    /*根据学生ID查询学生*/
    public Map<String,Object> searchStudentById(Integer studentId){

        User user = studentDao.searchStudentById(studentId);

        Map<String,Object> userMap = new HashMap<String,Object>();
        userMap.put("id", user.getId());
        userMap.put("account", user.getAccount());
        userMap.put("name", user.getName());
        userMap.put("email", user.getEmail());

        return userMap;
    }

    /*管理员修改某一学生的信息*/
    @RequestMapping(value="/{studentId}/information", method=RequestMethod.PUT)
    public Map<String,Object> modifyStudentInfo(@PathVariable Integer studentId, @RequestBody User user){

    	int result = studentDao.modifyStudentInfo(studentId,user);

        Map<String,Object> userMap =searchStudentById(studentId);

        return userMap;
    }

    /*管理员重置某一学生的密码*/
    @RequestMapping(value="{studentId}/password", method=RequestMethod.PUT)
    public Map<String,Object> resetStudentPassword(@PathVariable Integer studentId){

        int result = studentDao.resetStudentPassword(studentId);

        Map<String,Object> userMap = searchStudentById(studentId);

        return userMap;
    }

    /*管理员按ID删除某一学生*/
    @RequestMapping(value="/{studentId}", method=RequestMethod.DELETE)
    public Integer deleteStudent(@PathVariable Integer studentId){

        return studentDao.deleteStudent(studentId);
    }


   /* 学生激活账号*/
    @RequestMapping(value="/active",method=RequestMethod.PUT)
	public Map<String,Object> accountActive(@RequestBody User user){
    	
    	int studentId=1;//security获取当前用户ID
    	user.setId(studentId);
        int result = studentDao.accountActive(user);

        Map<String,Object> userMap = searchStudentById(result);
        userMap.put("status", "success");

        return userMap;
	}
}
