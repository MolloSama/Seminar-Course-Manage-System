package com.bkg.coursemanager.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkg.coursemanager.entity.Account;

@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestBoot {
	@RequestMapping(value="/account",method=RequestMethod.GET)
	public Account getAccount()
	{
		Account bao=new Account();
		bao.setAccountID("24320162202932");
		bao.setCode(" ");
		bao.setEmail("837412842@qq.com");
		bao.setName("熊安书");
		bao.setPassword("sdad sasd");
		bao.setRole("student");
		bao.setStatus(0);
		bao.setTimeInterval(60);
		return bao;
	}
}
