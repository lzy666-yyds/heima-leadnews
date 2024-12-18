package com.heima.schedule.service;

import com.heima.model.schedule.dtos.Task;
import com.heima.schedule.ScheduleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Description:
 */
@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Test
    public void addTask() {
        Task task = new Task();
        task.setTaskType(100);
        task.setPriority(10);
        task.setParameters("hello world".getBytes());
        task.setExecuteTime(new Date().getTime()+500);
        long taskId = taskService.addTask(task);
        System.out.println(taskId);
    }

    @Test
    public void cancelTask() {
        boolean b = taskService.cancelTask(1869357128811958273L);
        System.out.println(b);
    }
}