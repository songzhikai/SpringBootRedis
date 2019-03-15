package com.redis.controller;

import com.alibaba.fastjson.JSON;
import com.redis.model.User;
import com.redis.service.RedisHelperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@EnableScheduling
public class TestRedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisHelperImpl redisHelper;

    @RequestMapping("/test")
//    @Scheduled(cron = "*/3 * * * * *")
    public void test() throws InterruptedException {
        Map map = new HashMap();
        map.put("name", "zhangsan1");
        map.put("age", "19");
        redisHelper.setValue("502", map, 2); //2秒后失效
        Map res = (Map)redisHelper.getValue("502");
        if(res != null){
            System.out.println(JSON.toJSONString(res));
            Thread.sleep(3000);
            Map res1 = (Map)redisHelper.getValue("502");
            System.out.println("休眠"+JSON.toJSONString(res1));
        }else{
            System.out.println("exists is false");
        }
    }

}
