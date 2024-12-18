package com.heima.schedule.service;

import com.heima.model.schedule.dtos.Task;

/**
 * @Description:对外访问接口
 */
public interface TaskService {
    /**
     * @Desc:添加任务
     **/
    public long addTask(Task task);

    /**
     * @Desc: 取消任务
     **/
    public boolean cancelTask(long taskId);

    /**
     * @Desc: 按照类型和优先级来拉取任务
     **/
    public Task poll(int type,int priority);
}
