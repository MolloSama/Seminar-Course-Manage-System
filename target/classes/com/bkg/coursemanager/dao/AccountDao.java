package com.bkg.coursemanager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bkg.coursemanager.entity.Account;
import com.bkg.coursemanager.mapper.AccountMapper;

@Repository
public class AccountDao {
	@Autowired
	private AccountMapper accountMapper;
	
	public Account getAccountByID(String accountID)
	{
		return accountMapper.getAccountByID(accountID);
	}
}
