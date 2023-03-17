package com.zhangxuerong.activiti7_workflow;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Part1_Deployment {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void initDeploymentBPMN() {
        String fineName = "BPMN/Part_Deployment.bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(fineName)
                // .addClasspathResource("BPMN/Part_Deployment.png")  // 图片
                .name("流程部署测试BPMN_v2")
                .deploy();

        System.out.println(deployment.getName());
    }

    @Test
    public void initDeploymentZip() {
        String fineName = "BPMN/Part_Deployment-v3.zip";
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fineName);
        assert inputStream != null;
        ZipInputStream zip = new ZipInputStream(inputStream);
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zip)
                .name("流程部署测试zip")
                .deploy();

        System.out.println(deployment.getName());
    }

    @Test
    public void getDeployments() {
        List<Deployment> list = repositoryService.createDeploymentQuery()
                .list();
        for (Deployment dep : list) {
            System.out.println("Id："+dep.getId());
            System.out.println("Name："+dep.getName());
            System.out.println("时间"+dep.getDeploymentTime());
            System.out.println("key"+dep.getKey());
        }
    }

}
