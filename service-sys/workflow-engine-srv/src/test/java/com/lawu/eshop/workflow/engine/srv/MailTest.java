package com.lawu.eshop.workflow.engine.srv;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 头脑游戏缓存服务单元测试
 * @author jiangxinjun
 * @createDate 2018年4月2日
 * @updateDate 2018年4月2日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    
    /**
     * 运行时服务
     */
    @Autowired
    private RuntimeService runtimeService;
    
    /**
     * 任务服务
     */
    @Autowired
    private TaskService taskService;
    
    @Test
    public void create() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("mailTaskProcess");
        
        // 员工请假
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
        System.out.println(task);
        
        // 经理审批
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
        System.out.println(task);
        
        // 流程结束
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println(task);
    }
    
}