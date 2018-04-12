package com.imooc.service.impl;

import org.springframework.stereotype.Service;

import com.imooc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello"+name;
	}

}
