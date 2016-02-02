/*
 * ProjectAPIDemo.java    1.0 2014/7/10
 *
 * Copyright (c) 2014-2030 Monmouth Technologies, Inc.
 * http://www.mt.com.tw
 * 10F-1 No. 306 Chung-Cheng 1st Road, Linya District, 802, Kaoshiung, Taiwan
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Monmouth
 * Technologies, Inc. You shall not disclose such Confidential Information and 
 * shall use it only in accordance with the terms of the license agreement you
 * entered into with Monmouth Technologies.
 */
package tw.com.mt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import tw.com.mt.util.ProjectServiceClient;

import com.ctu.tm.data.xsd.ProjectBaseBean;

/**
 * Demo all project related API functions.
 *
 * @version 1.0 2014/7/10
 * @author ken
 *
 */
public class ProjectAPIDemo {
    private String seperateLine = "========================================";
    
    private ProjectBaseBean getProject(int projObjKey) {
        ProjectBaseBean project = ProjectServiceClient.getProject(projObjKey);
        return project;
    }
    
    /**
     * Get all projects belong to the company of the current security token.
     * @return all projects in an Array
     */
    private ProjectBaseBean[]  getAllProjects() {
        ProjectBaseBean[] projects = ProjectServiceClient.findProjectsTM75("");
        return projects;
    }

    /**
     * Create a new project.
     */
    private int createProject() {
        Calendar cal = GregorianCalendar.getInstance();
        String projectID = "Test-" + cal.getTimeInMillis();
        String beginDateString = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        cal.add(Calendar.MONTH, 3);
        String endDateString = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        
        int beginDate = Integer.parseInt(beginDateString);
        int endDate = Integer.parseInt(endDateString);

        return ProjectServiceClient.createProjectTM75(
                "Test Project created by tm-soap-api",// projName-專案名稱
                projectID, // projId-專案代號,不可重複
                "This project is for testing purpose only", // projDesc-說明
                "C", // projType-機密等級
                "Kenny", // projSponsor-贊助者
                "admin", // ownerID-專案主持人
                "admin", // createdByUserID-專案建立者ID
                "Kaohsiung City, Taiwan", // projOfficeAddress-專案執行地址
                2, // tiers-專案架構
                beginDate, // startDate-預定開始日，請依此yyyymmdd格式填寫
                endDate, // endDate-預定完成日，請依此yyyymmdd格式填寫
                0, // projImportance-專案的重要性(1最低-5最重要)(預設為 4)
                "", // sda0-系統預設欄位，請參閱2.4.3
                "", // sda1-系統預設欄位，請參閱2.4.3
                "", // sda2-系統預設欄位，請參閱2.4.3
                "", // sda3-系統預設欄位，請參閱2.4.3
                "", // sda4-系統預設欄位，請參閱2.4.3
                "", // sda5-系統預設欄位，請參閱2.4.3
                "", // sda6-系統預設欄位，請參閱2.4.3
                "", // sda7-系統預設欄位，請參閱2.4.3
                "", // sda8-系統預設欄位，請參閱2.4.3
                "", // sda9-系統預設欄位，請參閱2.4.3
                "", // sda10-系統預設欄位，請參閱2.4.3
                "", // sda11-系統預設欄位，請參閱2.4.3
                "", // sda12-系統預設欄位，請參閱2.4.3
                "", // sda13-系統預設欄位，請參閱2.4.3
                "", // sda14-系統預設欄位，請參閱2.4.3
                "", // sda15-系統預設欄位，請參閱2.4.3
                "", // sda16-系統預設欄位，請參閱2.4.3
                "", // sda17-系統預設欄位，請參閱2.4.3
                "", // sda18-系統預設欄位，請參閱2.4.3
                "", // sda19-系統預設欄位，請參閱2.4.3
                "", // uda40-自定欄位，請參閱2.3.2
                "", // uda41-自定欄位，請參閱2.3.2
                "", // uda42-自定欄位，請參閱2.3.2
                "", // uda43-自定欄位，請參閱2.3.2
                "", // uda44-自定欄位，請參閱2.3.2
                "", // uda45-自定欄位，請參閱2.3.2
                "", // uda46-自定欄位，請參閱2.3.2
                "", // uda47-自定欄位，請參閱2.3.2
                "", // uda48-自定欄位，請參閱2.3.2
                "", // uda49-自定欄位，請參閱2.3.2
                "", // uda50-自定欄位，請參閱2.3.2
                "", // uda51-自定欄位，請參閱2.3.2
                "", // uda52-自定欄位，請參閱2.3.2
                "", // uda53-自定欄位，請參閱2.3.2
                "", // uda54-自定欄位，請參閱2.3.2
                "", // uda55-自定欄位，請參閱2.3.2
                "", // uda56-自定欄位，請參閱2.3.2
                "", // uda57-自定欄位，請參閱2.3.2
                "", // uda58-自定欄位，請參閱2.3.2
                "" // uda59-自定欄位，請參閱2.3.2
        );
    }
    
    private void printMessage(String message) {
        System.out.println("");
        System.out.println(seperateLine);
        System.out.println(message);
        System.out.println(seperateLine);
        System.out.println("");
    }

    public static void main(String[] args) {
        ProjectAPIDemo mytest = new ProjectAPIDemo();
        ProjectBaseBean[] projects = mytest.getAllProjects();

        if (projects == null || projects.length == 0) {
            mytest.printMessage("There is no project");  
        } else {
            mytest.printMessage("Print out all projects");
            for (ProjectBaseBean project : projects) {
                System.out.println(String.format("Project Name: %s - Owner: %s",
                    project.getProjName(), project.getProjOwnerID()));
            }
        }
        
        mytest.printMessage("Create a new project");
        int projObjKey = mytest.createProject();
        if (projObjKey > 0) {
            System.out.println(String.format("New Project Key : %d",
                    projObjKey));
        } else {
            System.out.println(String.format("Error Code : %d",
                    projObjKey));
            System.exit(0);
        }
        
        mytest.printMessage("Get this newly created project");
        ProjectBaseBean project = mytest.getProject(projObjKey);
        System.out.println(String.format("Project Name: %s - Owner: %s",
                project.getProjName(), project.getProjOwnerID()));
    }
}
