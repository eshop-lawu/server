package com.lawu.eshop.workflow.engine.srv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MultiPersonSigningTest {
    
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
    public void test() {
        Map<String, Object> vars = new HashMap<>();
        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(i);
        }
        vars.put("datas", datas);
        //vars.put("pass", true);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("multiPersonSigning", vars);
        
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        System.out.println(tasks.size());
        
        for (Task item : tasks) {
            System.out.println("data------->" + runtimeService.getVariable(item.getExecutionId(), "data"));
        }
        
        // 一票否决当前流程
        Map<String, Object> taskResult = new HashMap<>();
        taskResult.put("pass", false);
        taskService.complete(tasks.get(2).getId(), taskResult);
        
        // 流程结束
        Task task3 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println(task3);
    }
    
}