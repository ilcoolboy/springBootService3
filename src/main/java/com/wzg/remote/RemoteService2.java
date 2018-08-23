package com.wzg.remote;

import static org.mockito.Mockito.timeout;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wzg.config.FeignConfiguration;

@FeignClient(name= "eurekaClient1", configuration = FeignConfiguration.class)
@RequestMapping("/server2/index")
public interface RemoteService2 {
	@RequestMapping(value = "/msgBack")
	public Map msgBack(@RequestParam(value = "str")String str);
}
