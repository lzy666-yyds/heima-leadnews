package com.heima.schedule.service.impl;/**
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.heima.common.constants.ScheduleConstants;
import com.heima.common.redis.CacheService;
import com.heima.model.schedule.dtos.Task;
import com.heima.model.schedule.pojos.Taskinfo;
import com.heima.model.schedule.pojos.TaskinfoLogs;
import com.heima.schedule.mapper.TaskinfoLogsMapper;
import com.heima.schedule.mapper.TaskinfoMapper;
import com.heima.schedule.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 *@Desc:
 *@Author: lzy
 *@CreateTime: 2024-12-18  19:47
 */
@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskinfoMapper taskinfoMapper;
    @Autowired
    private TaskinfoLogsMapper taskinfoLogsMapper;
    @Autowired
    private CacheService cacheService;
    /**
     * @Desc: 添加任务
     **/
    @Override
    public long addTask(Task task) {
        //1.添加任务到数据库中
        boolean success=addTaskToDb(task);
        if(success){
            //2.添加任务到redis
            addTaskToCache(task);
        }


        return task.getTaskId();
    }

    /**
     * @Desc: 取消任务
     **/
    @Override
    public boolean cancelTask(long taskId) {
        boolean flag = false;

        //删除任务，更新日志
        Task task = updateDb(taskId,ScheduleConstants.EXECUTED);

        //删除redis数据
        removeTaskFromCache(task);
        return true;
    }

    /**
     * @Desc: 按照类型和优先级来拉取任务
     **/
    @Override
    public Task poll(int type, int priority) {
        Task task = null;
        String key = type + "_" + priority;
        String task_json = cacheService.lRightPop(ScheduleConstants.TOPIC + key);
        if(StringUtils.isNoneBlank(task_json)){
            task = JSON.parseObject(task_json, Task.class);
            //更新数据库信息
            updateDb(task.getTaskId(),ScheduleConstants.EXECUTED);
        }
        return task;
    }

    /**
     * @Desc: 从redis中删除任务
     **/
    private void removeTaskFromCache(Task task) {
        String key = task.getTaskType() + "_" + task.getPriority();
        if(task.getExecuteTime()<=System.currentTimeMillis()){
            cacheService.zRemove(ScheduleConstants.FUTURE+key,task.toString());
        }else{
            cacheService.lRemove(ScheduleConstants.TOPIC+key,1,task.toString());
        }
    }

    /**
     * @Desc: 更新任务
     **/
    private Task updateDb(long taskId, int status) {
        //删除任务信息表
        taskinfoMapper.deleteById(taskId);
        //更新任务信息日志表
        TaskinfoLogs taskinfoLogs = taskinfoLogsMapper.selectById(taskId);
        taskinfoLogs.setStatus(status);
        taskinfoLogsMapper.updateById(taskinfoLogs);

        Task task = new Task();
        BeanUtils.copyProperties(taskinfoLogs,task);
        task.setExecuteTime(taskinfoLogs.getExecuteTime().getTime());
        return task;
    }

    /**
     * @Desc: 添加任务到redis
     **/
    private void addTaskToCache(Task task) {
        String key = task.getTaskType() + "_" + task.getPriority();

        //获取5分钟之后的时间  毫秒值
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,5);
        long nextScheduleTime = calendar.getTimeInMillis();


        //2.1 如果任务的执行时间小于等于当前时间，存入list
        if(task.getExecuteTime()<=System.currentTimeMillis()){
            cacheService.lLeftPush(ScheduleConstants.TOPIC+key,task.toString());
        }else if (task.getExecuteTime()<=nextScheduleTime){
            cacheService.zAdd(ScheduleConstants.FUTURE+key,task.toString(),task.getExecuteTime());
        }
    }

    /**
     * @Desc: 添加任务到数据库中
     **/
    private boolean addTaskToDb(Task task) {
        boolean flag = false;
        //保存任务表
        try {
            Taskinfo taskinfo = new Taskinfo();
            BeanUtils.copyProperties(task, taskinfo);
            taskinfo.setExecuteTime(new Date(task.getExecuteTime()));
            taskinfoMapper.insert(taskinfo);

            task.setTaskId(taskinfo.getTaskId());

            //保存任务日志表
            TaskinfoLogs taskinfoLogs = new TaskinfoLogs();
            BeanUtils.copyProperties(taskinfo, taskinfoLogs);
            taskinfoLogs.setStatus(ScheduleConstants.SCHEDULED);
            taskinfoLogs.setVersion(1);
            taskinfoLogsMapper.insert(taskinfoLogs);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
