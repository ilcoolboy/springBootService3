package com.wzg.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wzg.entity.Zfb;
import com.wzg.service.IDoMainService;

@RestController
@RequestMapping("/main")
public class MainController {
	@Autowired
	private IDoMainService doMainService;
	
	@RequestMapping("/save")
	public void saveZfb(String name, BigDecimal account) {
		Zfb zfb = new Zfb();
		zfb.setName(name);
		zfb.setAccount(account);
		doMainService.saveZfb(new Gson().toJson(zfb));
	}
}
