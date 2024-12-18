package com.heima.schedule.test;
import com.heima.common.redis.CacheService;
import com.heima.schedule.ScheduleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-18  19:08
 */
@SpringBootTest(classes= ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private CacheService cacheService;
    @Test
    public void testList(){
        //在list的左边添加元素
//        cacheService.lLeftPush("list_001","lzy");
        //在list的右边删除元素并获取
        String s = cacheService.lRightPop("list_001");
        System.out.println(s);
    }

    @Test
    public void testZSet(){
        //添加数据到zset中  分值
        cacheService.zAdd("zset_001","zhangsan",100);
        cacheService.zAdd("zset_001","lisi",90);
        cacheService.zAdd("zset_001","wangwu",80);
        cacheService.zAdd("zset_001","zhaoliu",70);
    }


}
