/**
 * 
 */
package com.wzg.service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.wzg.dao.IJdbcZfbDao;
import com.wzg.dao.ZfbRepository;
import com.wzg.entity.Zfb;
import com.wzg.mq.Produce;
import com.wzg.remote.RemoteService2;
/**
 * @author wuzhigang
 *
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DoMainService implements IDoMainService {
	@Autowired
	private RemoteService2 remoteService2;
	private String REDIS_BANK1_GID_KEY = "set.bank1.gid";
	private String BANK_BACK_MSG = "bank1.back";
	@Resource
	private IJdbcZfbDao jdbcZfbDao;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ZfbRepository zfbRepository;
	@Autowired
	private Produce produce;
	private Gson gson = new Gson();
	
	@Transactional()
	public void saveZfb(String msg) {
		Zfb zfb = gson.fromJson(msg, Zfb.class);
		String bankGid = zfb.getGid();
		//stp1 保证幂等性
		SetOperations setOp = redisTemplate.opsForSet();
		//已经saved过的bank1的gid
		//判断是否存在过此id
		boolean isExist = setOp.isMember(REDIS_BANK1_GID_KEY, bankGid);
		if (isExist) {
			return;
		}
		zfb.setBgid(bankGid);
		zfb.setGid(null);
		zfb.setLasttime(new Date());
		//stp2 saveZfb
		zfbRepository.saveAndFlush(zfb);
		// TODO 可以直接调用A服务的方法处理 
//		discoveryClient.getApplications().getRegisteredApplications();
		String msgstr = "{success:true, gid:"+ bankGid +"}";
		remoteService2.msgBack(msgstr);
//		produce.sendMsg(BANK_BACK_MSG, msgstr);
		//stp4 通过缓存保存已经处理过的银行Gid
		setOp.add(REDIS_BANK1_GID_KEY, bankGid);
	}
}
