package com.bkg.coursemanager.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.service.TeacherService;

/*
 * @author JQQ
 * @date 2018/12/20
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	/*
	 * 教师激活账号
	 */
	@RequestMapping(value = "/active", method = RequestMethod.PUT)
	public Map<String, Object> accountActive(@RequestBody Map<String, Object> active) {
		BigInteger teacherId=new BigInteger("1");//security获取当前用户id
		String password = (String) active.get("password");
		User teacher=teacherService.selectTeacherByTeacherId(teacherId);
		teacher.setPassword(password);
		teacherService.resetTeacherPasswordByTeacherId(teacherId, teacher);
		Map<String, Object> status=new HashMap<String, Object>();
		status.put("status", "success");
		return status;
	}

	/*
	 * 管理员创建一个老师
	 */
	@PostMapping("")
	public Map<String, Object> createTeacher(@RequestBody User teacher) {
		BigInteger newTeacherIdForTest=teacherService.insertTeacher(teacher);
		//输出新创建的教师的ID（数据库自增长字段）
		System.out.println(newTeacherIdForTest);
		Map<String, Object> status=new HashMap<String, Object>();
		status.put("status", "success");
		return status;
	}

	/*
	 * 管理员获得所有教师信息,不包括密码
	 */
	@GetMapping("")
	public List<Map<String , Object>> getAllTeachers() {
		List<User> teacherList=teacherService.listTeacher();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<teacherList.size();i++) {
			Map<String, Object> map=new HashMap<>();
			map.put("id",teacherList.get(i).getId());
			map.put("account", teacherList.get(i).getAccount());
			map.put("name", teacherList.get(i).getName());
			map.put("email", teacherList.get(i).getEmail());
			list.add(map);
		}
		return list;
	}

	/*
	 * 管理员根据姓名或教工号查询教师
	 */
	@GetMapping("/searchteacher")
	public Map<String, Object> searchTeacher(@RequestBody Map<String, Object> accountOrName) {
		Map<String, Object> teacherMap = new HashMap<String, Object>();
		String select=accountOrName.get("accountOrName").toString();
		
		// 判断这个字符串是不是数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(select);
		User teacher;
		if (!isNum.matches()) {// 是字符串
			teacher=teacherService.selectTeacherByTeacherName(select);
		} else { // 是数字
			teacher=teacherService.selectTeacherByTeacherAccount(select);
		}	
		teacherMap.put("id",BigInteger.valueOf(teacher.getId()).toString());
		teacherMap.put("account", teacher.getAccount());
		teacherMap.put("name", teacher.getName());
		teacherMap.put("email", teacher.getEmail());
		
		return teacherMap;
	}

	/*
	 * 管理员修改某一教师的信息,只更改account,teacher_name,email三项信息
	 */
	@PutMapping("/{teacherId}/information")
	public Map<String, Object> updateInformation(@RequestBody Map<String, Object> infromation,
			@PathVariable("teacherId") String teacherId) {
		User teacher=new User();
		teacher.setAccount(infromation.get("account").toString());
		teacher.setName(infromation.get("name").toString());
		teacher.setEmail(infromation.get("email").toString());
		
		teacherService.updateTeacherByTeacherId(new BigInteger(teacherId), teacher);
		Map<String, Object> status=new HashMap<String, Object>();
		status.put("status", "success");
		return status;
	}

	/*
	 * 管理员重置某一教师的密码,取出json.password
	 */
	@PutMapping("/{teacherId}/password")
	public Map<String, Object> reCreatePassword(@RequestBody Map<String, Object> newPassword, @PathVariable("teacherId") String teacherId) {
		User teacher=new User();
		teacher.setPassword(newPassword.get("password").toString());
		
		teacherService.resetTeacherPasswordByTeacherId(new BigInteger(teacherId), teacher);
		Map<String, Object> status=new HashMap<String, Object>();
		status.put("status", "success");
		return status;
	}

	/*
	 * 管理员按ID删除某一教师
	 */
	@DeleteMapping("/{teacherId}")
	public Map<String, Object> deleteTeacher(@PathVariable String teacherId) {
		teacherService.deleteTeacherByTeacherId(new BigInteger(teacherId));
		Map<String, Object> status=new HashMap<String, Object>();
		status.put("status", "success");
		return status;
	}
}
