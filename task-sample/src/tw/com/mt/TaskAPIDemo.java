/*
 * TaskAPIDemo.java    1.0 2014/7/11
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

import java.util.Calendar;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import tw.com.mt.app.AppConstants;
import tw.com.mt.util.TaskServiceClient;
import com.ctu.tm.data.xsd.TaskBaseBean;

/**
 * Demo all project related API functions.
 * 
 * @version 1.0 2014/7/10
 * @author ken
 * 
 */
public class TaskAPIDemo {
    /**
     * The project key of the parent project.
     */
    private int projObjKey;
    /**
     * The object key of the parent object, it could be a topic or a task.
     */
    private int parentObjKey;
    /**
     * Property file name.
     */
    private String propertyFile = "application.properties";
    /**
     * Use this line to separate content when printing out information.
     */
    private String seperateLine = "========================================";

    /**
     * Default constructor.
     */
    public TaskAPIDemo() {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        try {
            config.addConfiguration(new PropertiesConfiguration(propertyFile));
            this.projObjKey = config.getInt("projObjKey");
            this.parentObjKey = config.getInt("parentObjKey");
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private TaskBaseBean getTask(int taskObjKey) {
        TaskBaseBean task = TaskServiceClient.getTask(taskObjKey);
        return task;
    }

    /**
     * Get all tasks of the specified project.
     * 
     * @return all projects in an Array
     */
    private TaskBaseBean[] getAllTasksCreatedByUser100() {
        TaskBaseBean[] tasks = TaskServiceClient.findTasks(
                projObjKey,
                AppConstants.TASK_BIA_CREATED_BY,
                "100"
        );
        return tasks;
    }

    /**
     * Create a new project.
     */
    private int createTask() {
        Calendar cal = Calendar.getInstance();
        String taskName = "Task-" + cal.getTimeInMillis();

        return TaskServiceClient.createTask(
                projObjKey, // projObjKey-新建立工作項目所在的專案key值
                parentObjKey, // parentObjKey-新建立工作項目所屬的子專案/專案key值
                "工作敘述永不變", // taskDesc-工作Memo區,
                100, // taskOwnerKey-工作項目負責人, 填入使用者Key值 ,
                0, // udaSet-在何組工作之下
                0, // sdaSet-套用哪一組預設欄位，填0即可
                "", // sda0-系統預設欄位
                "", // sda1-系統預設欄位
                "", // sda2-系統預設欄位
                "", // sda3-系統預設欄位
                "", // sda4-系統預設欄位
                "", // sda5-系統預設欄位
                "", // sda6-系統預設欄位
                "", // sda7-系統預設欄位
                "", // sda8-系統預設欄位
                taskName, // sda9-系統預設欄位
                "", // sda10-系統預設欄位
                "", // sda11-系統預設欄位
                "", // sda12-系統預設欄位
                "", // sda13-系統預設欄位
                "", // sda14-系統預設欄位
                "", // sda15-系統預設欄位
                "", // sda16-系統預設欄位
                "", // sda17-系統預設欄位
                "", // sda18-系統預設欄位
                "", // sda19-系統預設欄位
                "", // uda0-自定欄位
                "", // uda1-自定欄位
                "", // uda2-自定欄位
                "", // uda3-自定欄位
                "", // uda4-自定欄位
                "", // uda5-自定欄位
                "", // uda6-自定欄位
                "", // uda7-自定欄位
                "", // uda8-自定欄位
                "", // uda9-自定欄位
                "", // uda10-自定欄位
                "", // uda11-自定欄位
                "", // uda12-自定欄位
                "", // uda13-自定欄位
                "", // uda14-自定欄位
                "", // uda15-自定欄位
                "", // uda16-自定欄位
                "", // uda17-自定欄位
                "", // uda18-自定欄位
                "", // uda19-自定欄位
                "", // uda60-自定欄位
                "", // uda61-自定欄位
                "", // uda62-自定欄位
                "", // uda63-自定欄位
                "", // uda64-自定欄位
                "", // uda65-自定欄位
                "", // uda66-自定欄位
                "", // uda67-自定欄位
                "", // uda68-自定欄位
                "", // uda69-自定欄位
                "", // uda70-自定欄位
                "", // uda71-自定欄位
                "", // uda72-自定欄位
                "", // uda73-自定欄位
                "", // uda74-自定欄位
                "", // uda75-自定欄位
                "", // uda76-自定欄位
                "", // uda77-自定欄位
                "", // uda78-自定欄位
                "", // uda79-自定欄位
                "", // accessList-存取成員,(userID,userID,userID)
                "", // userGroupAccessList-存取群組, (群組ID,群組ID)
                "", // subTeamAccessList-存取子團隊,(subTeamObjKey,subTeamObjKey)
                "N", // isMilestoneFlag-里程碑 (Y/N)
                999999, // seqNO-序列編號
                "admin" // userID-建立者ID
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
        TaskAPIDemo mytest = new TaskAPIDemo();

        mytest.printMessage("Create a new task");
        int taskObjKey = mytest.createTask();
        if (taskObjKey > 0) {
            System.out
                    .println(String.format("New Task Key : %d", taskObjKey));
        } else {
            System.out.println(String.format("Error Code : %d", taskObjKey));
            System.exit(0);
        }

        mytest.printMessage("Print out all tasks created by User 100 in project "
                + mytest.projObjKey);
        TaskBaseBean[] tasks = mytest.getAllTasksCreatedByUser100();
        for (TaskBaseBean task : tasks) {
            System.out.println(String.format("Task Name: %s - Owner: %s",
                    task.getTaskName(), task.getTaskOwnerID()));
        }

        mytest.printMessage("Get this newly created task");
        TaskBaseBean task = mytest.getTask(taskObjKey);
        System.out.println(String.format("Task Name: %s - Owner: %s",
                task.getTaskName(), task.getTaskOwnerID()));
    }
}
