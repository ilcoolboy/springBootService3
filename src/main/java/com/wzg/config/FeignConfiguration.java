package com.wzg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import feign.Request;

@Configuration
public class FeignConfiguration {
	
	
	 /**
     * Method to create a bean to increase the timeout value, 
     * It is used to overcome the Retryable exception while invoking the feign client.
     * @param env,
     *            An {@link ConfigurableEnvironment}
     * @return A {@link Request}
     */
    @Bean
    public static Request.Options requestOptions(ConfigurableEnvironment env) {
        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 70000);
        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 60000);
        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
}
