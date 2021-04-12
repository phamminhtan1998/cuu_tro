package com.phamtan.cuu_tro.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean("asyncThreadPool")
    public ThreadPoolTaskExecutor threadPoolExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(3);
        executor.setThreadNamePrefix("WebHookAsyncThread--");
        executor.initialize();
        return executor;
    }
}
