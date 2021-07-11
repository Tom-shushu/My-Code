package com.camunda.camundademo.controller;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: camunda-demo
 * @Description:
 * @Author: zhouhong
 * @Create: 2021-07-08 10:06
 **/

@RestController
public class Test {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;
    @Autowired
    ProcessEngine processEngine;

    @Autowired
    ProcessEngine engine;

    /**
    * @Description: 流程定义部署
    * @Author: zhouhong
    * @Date: 2021/7/8
    */
    @PostMapping("/deploy")
    public void deploy() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("BPMN/apply.bpmn")
                .deploy();
        System.out.println(deploy.getId());
    }

    /**
     * @Description: 开启一个流程实例
     * @Author: zhouhong
     * @Date: 2021/7/8
     */
    @PostMapping("/start")
    public void runProcinst(){
        Map<String,Object> params = new HashMap<>();
        params.put("money",2001);
        ProcessInstance apply = runtimeService.startProcessInstanceByKey("apply",params);
        System.out.println(apply.getProcessDefinitionId());
        System.out.println(apply.getId());
        System.out.println(apply.getProcessInstanceId());
    }

    /**
     * @Description: 流程任务查询
     * @Author: zhouhong
     * @Date: 2021/7/8
     */
    @PostMapping("/taskquery")
    public void taskQuery() {
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("apply")
                .list();
         for (Task task : tasks) {
            System.out.println(task.getAssignee());
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getTenantId());
        }
    }

    /**
    * @Description: 当前需要处理的任务
    * @Author: zhouhong
    * @Date: 2021/7/8
    */
    @PostMapping("/mytaskquery")
    public List<HistoricTaskInstance> myTaskQuery() {
        List<HistoricTaskInstance> instances = engine.getHistoryService().createHistoricTaskInstanceQuery()
                .taskAssignee("lisi").unfinished().orderByHistoricActivityInstanceStartTime().asc().list();
        return instances;
    }

    /**
     * @Description: 流程任务执行
     * @Author: zhouhong
     * @Date: 2021/7/8
     */
    @PostMapping("/taskComplete")
    public void taskComplete(){
        //目前lisi只有一个任务，业务中根据场景选择其他合适的方式
        Task task = taskService.createTaskQuery()
                .taskAssignee("zhangsan")
                .singleResult();
        Map<String,Object> params = new HashMap<>();
        params.put("approve2","lisi");
        taskService.complete(task.getId(),params);
    }

    /**
     * @Description: 流程定义查询
     * @Author: zhouhong
     * @Date: 2021/7/8
     */
    @PostMapping("/queryDefine")
    public void queryDefine(){
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitions = query.processDefinitionKey("apply")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition definition : definitions) {
            System.out.println(definition.getDeploymentId());
            System.out.println(definition.getName());
            System.out.println(definition.getVersion());
            System.out.println(definition.getId());
            System.out.println(definition.getKey());
        }
    }

    /**
     * 删除流程定义
     */
    @PostMapping("/deleteDefine")
    public void deleteDefine(){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> definitions = processDefinitionQuery.processDefinitionKey("apply")
                .orderByProcessDefinitionVersion()
                .asc()
                .list();
        ProcessDefinition processDefinition = definitions.get(0);
        if (processDefinition != null){
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
        }
    }

    /**
     * 查询历史信息
     */
    @PostMapping("/queryHistory")
    public void queryHistory(){
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .finished()
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getProcessDefinitionKey());
            System.out.println(instance.getAssignee());
            System.out.println(instance.getStartTime());
            System.out.println(instance.getEndTime());
            System.out.println("=============================");
        }
    }

    /**
     * 启动一个流程实例，并且添加一个业务key
     * 业务key 可以在 act_ru_execution 中看到
     */

    public void startProcInstAddBusinessKey(){
        ProcessInstance apply = runtimeService.startProcessInstanceByKey("apply", "aaaa-scsc-89uc");
        System.out.println(apply.getBusinessKey());
    }

}
