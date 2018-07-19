package com.wzg.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wzg.entity.Zfb;
@Component
public class JdbcZfbDao implements IJdbcZfbDao {
	@Autowired
	private JdbcTemplate JdbcTemplate;
	@Override
	public void saveZfb(String name, BigDecimal account) {
		// TODO Auto-generated method stub
		JdbcTemplate.update("insert into zfb (gid, name, account, lasttime) values(sys_guid(), ?, ?, sysdate)", name, account);
	}
	@Override
	public List getAll() {
		return JdbcTemplate.query("select   gid, name, account, lasttime from zfb", new BeanPropertyRowMapper<Zfb>(Zfb.class));
	}
	
}
