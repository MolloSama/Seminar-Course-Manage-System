package com.bkg.coursemanager.controller;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description User接口相关Controller
 * @author bao
 * @version v1.2
 * @date 2018/12/21
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkg.coursemanager.dao.UserDao;
import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.service.UserService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	/*
	 * 根据security获取当前用户信息并返回
	 */
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public User getUserInfo() {
    	User user=new User();
    	return user;
    }
    
	/*
	 * 根据security获取当前用户密码并发送至用户邮箱
	 */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public Map<String, Object> sendPasswordToUser(@RequestBody User user) {
    	User userNow=userDao.getUserByAccount(user.getAccount());
    	String password=userNow.getPassword();
    	String email=userNow.getEmail();
    	userService.sendEmail("您的课程系统密码", password, email);
    	Map<String, Object> status=new HashMap<String, Object>();
    	status.put("status", "success");
    	return status;
    }
    
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public Map<String, Object> updatePasswordByAccount(@RequestBody User user) 
    {
    	user.setAccount("24320162202932");
    	userDao.updateUserPasswordByAccount(user);
    	Map<String, Object> status=new HashMap<String, Object>();
    	status.put("status", "success");
    	return status;
    }
    
    @RequestMapping(value = "/email", method = RequestMethod.PUT)
    public Map<String, Object> updateEmailByAccount(@RequestBody User user) 
    {
    	userDao.updateUserEmailByAccount(user);
    	Map<String, Object> status=new HashMap<String, Object>();
    	status.put("status", "success");
    	return status;
    }
}
