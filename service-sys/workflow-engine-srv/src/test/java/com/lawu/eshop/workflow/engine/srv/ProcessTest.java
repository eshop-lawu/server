package com.lawu.eshop.workflow.engine.srv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.el.JuelExpression;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Ignore;
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
public class ProcessTest {
    
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
    
    /**
     * 存储服务
     */
    @Autowired
    private RepositoryService repositoryService;
    
    /**
     * 存储服务
     */
    @Autowired
    private IdentityService identityService;
    
    @Test
    public void create() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationProcess");
        
        Group group = identityService.createGroupQuery().groupId("workerGroup").singleResult();
        if (group == null) {
            group = identityService.newGroup("workerGroup");
            identityService.saveGroup(group);
        }
        User user = identityService.createUserQuery().userId("worker").singleResult();
        if (user == null) {
            user = identityService.newUser("worker");
            identityService.saveUser(user);
        }
        
        runtimeService.setVariable(processInstance.getId(), "workerName", "sunny");
        
        // 员工请假
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        runtimeService.setVariable(task.getExecutionId(), "workerName", "sunny");
        // 员工认领任务
        taskService.setAssignee(task.getId(), user.getId());
        taskService.complete(task.getId());
        System.out.println(task);
        
        // 经理审批
        Task task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task2.getId());
        System.out.println(task2);
        
        // 流程结束
        Task task3 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println(task3);
    }
    
    @Ignore
    @Test
    public void processDiagram() throws IOException {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("vacationProcess").singleResult();
        InputStream is = repositoryService.getProcessDiagram(processDefinition.getId());
        BufferedImage image = ImageIO.read(is);
        File file = new File("images/vacationProcess.png");
        if (!file.getParentFile().exists()) file.getParentFile().mkdir();
        FileOutputStream fos = new FileOutputStream(file);
        ImageIO.write(image, "png", fos);
        fos.close();
        is.close();
    }
    
    @Test
    public void deploy() throws IOException {
        
    }
    
    public static class WorkerWriterVacationCreateTaskListener implements TaskListener {
        
        @Override
        public void notify(DelegateTask delegateTask) {
            System.out.println("worker writer vacation create");
        }
        
    }
    
    public static class WorkerWriterVacationAssignmentTaskListener implements TaskListener {
        
        private JuelExpression workerName;
        
        public JuelExpression getWorkerName() {
            return workerName;
        }

        public void setWorkerName(JuelExpression workerName) {
            this.workerName = workerName;
        }
        
        @Override
        public void notify(DelegateTask delegateTask) {
            System.out.println(delegateTask.getAssignee() + "-" + workerName.toString() + " writer vacation assignment");
        }
        
    }
    
    public static class WorkerWriterVacationCompleteTaskListener implements TaskListener {
        @Override
        public void notify(DelegateTask delegateTask) {
            System.out.println("worker writer vacation complete");
        }
        
    }
    
    public static class WorkerWriterVacationAllTaskListener implements TaskListener {
        @Override
        public void notify(DelegateTask delegateTask) {
            System.out.println("worker writer vacation all");
        }
        
    }
    
    public static class WorkerWriterVacationStartTaskListener implements ExecutionListener {

        @Override
        public void notify(DelegateExecution execution) {
            System.out.println("worker writer vacation start");
        }
        
    }
    
    public static class WorkerWriterVacationEndTaskListener implements ExecutionListener {

        @Override
        public void notify(DelegateExecution execution) {
            System.out.println("worker writer vacation end");
        }
        
    }
}