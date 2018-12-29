package com.bkg.coursemanager.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class UserService {
	
	@Autowired
	JavaMailSender jms;
	
	public boolean sendEmail(String title,String content,String email)
	{
		//建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		//发送者
		mainMessage.setFrom("coursesystem2_8@163.com");
		//接收者
		mainMessage.setTo(email);
		//发送的标题
		mainMessage.setSubject(title);
		//发送的内容
		mainMessage.setText(content);
		jms.send(mainMessage);
		return true;
	}
}
