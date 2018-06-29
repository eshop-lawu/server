package com.lawu.eshop.workflow.engine.srv;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 工作流引擎服务启动类
 * @author jiangxinjun
 * @createDate 2018年4月11日
 * @updateDate 2018年4月11日
 */
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class WorkflowEngineSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(WorkflowEngineSrvApplication.class);

    public static void main(String[] args) {
        logger.info("workflow-engine-srv is starting");
        SpringApplication.run(WorkflowEngineSrvApplication.class, args);
        logger.info("workflow-engine-srv is started");
    }

}
