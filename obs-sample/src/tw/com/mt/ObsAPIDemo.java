/*
 * DocAPIDemo.java    1.0 2014/7/11
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
import tw.com.mt.util.DocServiceClient;
import tw.com.mt.util.ObsServiceClient;
import tw.com.mt.util.TaskServiceClient;

import com.ctu.tm.data.xsd.DocBaseBean;
import com.ctu.tm.data.xsd.OrganizationBase;
import com.ctu.tm.data.xsd.TaskBaseBean;
import com.ctu.tm.data.xsd.UserBaseBean;

/**
 * Demo all project related API functions.
 * 
 * @version 1.0 2014/7/10
 * @author ken
 * 
 */
public class ObsAPIDemo {
    /**
     * The object key of the parent organization.
     */
    private int parentObjKey;
    /**
     * Property file name.
     */
    private String propertyFile = "application.properties";
    /**
     * User id of the newly created user.
     */
    private String userId = "";
    /**
     * Use this line to separate content when printing out information.
     */
    private String seperateLine = "========================================";

    /**
     * Default constructor.
     */
    public ObsAPIDemo() {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        try {
            config.addConfiguration(new PropertiesConfiguration(propertyFile));
            this.parentObjKey = config.getInt("parentObjKey");
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Get an organization.
     * @param orgObjKey organization key
     * @return an OrganizationBase instance
     */
    private OrganizationBase getOrganization(int orgObjKey) {
        OrganizationBase org = ObsServiceClient.loadOrganization(orgObjKey);
        return org;
    }

    /**
     * Get an employee.
     * @param userId employee's id
     * @return an employee as an UserBaseBean instance
     */
    private UserBaseBean getEmployee(String userId) {
        return ObsServiceClient.getEmployee(userId);
    }

    /**
     * Get all organizations of company.
     * 
     * @return all organizations in an Array
     */
    private OrganizationBase[] getAllOrganizations() {
        OrganizationBase[] orgs = ObsServiceClient.getAllOrganizations();
        return orgs;
    }

    /**
     * Get all employees of the specified organization.
     * @param orgObjKey organization key
     * @return employees as an UserBaseBean array
     */
    private UserBaseBean[] getEmployees(int orgObjKey) {
        UserBaseBean[] employees = ObsServiceClient.getEmployees(orgObjKey);
        return employees;
    }

    /**
     * Create a new organization.
     * 
     * @return organization key
     */
    private int createOrganization() {
        Calendar cal = Calendar.getInstance();
        String orgId = "Org-" + cal.getTimeInMillis();

        return ObsServiceClient.createOrganization("admin", // createBy-建立者
                parentObjKey, // parentObjKey-上層單位
                0, // orgType-單位類型(0:內部組織，1:外部組織)
                orgId, // orgID-單位代號
                "軟體開發部", // orgName-單位名稱
                "RD", // orgShortName-單位簡稱
                "", // phone-單位電話
                "", // fax-傳真
                "", // email-Email
                "", // address-地址
                "", // docServerId-文件伺服器ID
                "", // principal-負責人
                "", // note-備註
                "" // vatNumber-統一編號
        );
    }

    /**
     * Create a new user.
     * 
     * @return user key
     */
    private int createUser(int orgObjKey) {
        Calendar cal = Calendar.getInstance();
        this.userId = "fakeUser-" + cal.getTimeInMillis();
        
        return ObsServiceClient.createEmployee(
                "admin", // createBy-建立者
                orgObjKey, // orgObjKey-使用者所屬單位
                0, // orgType-組織類型 (內部:0，外部:1)
                userId, // userID-使用者ID
                "Fake User", // userName-使用者姓名
                "12345", // passwd-平台密碼
                "Y", // setLogin-是否開通平台帳號(Y/N)
                "N", // hasManagerAuth-單位管理權限
                "N", // isExtDirector-專任/兼任(N/Y)
                "", // validDuration-平台有效期限
                "", // prefix-稱謂
                "軟體工程師", // title-職位
                "", // workPhone-工作電話
                "", // workPhoneExt-分機
                "", // homePhone-家用電話
                "", // mobilePhone-行動電話
                "", // fax-傳真
                "", // email-電子郵件
                "", // docServerID-文件服務器
                "", // mobileEmail-行動電話郵址
                "", // attr1-其他1
                "", // attr2-其他2
                "", // attr3-其他3
                "", // attr4-其他4
                "", // aboutMe-備註
                "1000", // classLevel-分級類別 基本:1000,專業:2000,高階:3000
                "", // vintage-學歷
                "", // career-經歷
                "", // expertise-專長
                0, // costStdTime-標準工時
                0 // costOverTime-加班工時
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
        ObsAPIDemo mytest = new ObsAPIDemo();

        mytest.printMessage("Create a new organization");
        int orgObjKey = mytest.createOrganization();
        if (orgObjKey > 0) {
            System.out.println(String.format("New Org Key : %d", orgObjKey));
        } else {
            System.out.println(String.format("Error Code : %d", orgObjKey));
            System.exit(0);
        }

        mytest.printMessage("Print out all organizations");
        OrganizationBase[] orgs = mytest.getAllOrganizations();
        for (OrganizationBase org : orgs) {
            System.out.println(
                    String.format("Organization: %s", org.getName()));
        }

        mytest.printMessage("Get this newly created organization");
        OrganizationBase org = mytest.getOrganization(orgObjKey);
        System.out.println(String.format("Organization: %s", org.getName()));

        mytest.printMessage("Create a new user");
        int userKey = mytest.createUser(orgObjKey);
        if (userKey > 0) {
            System.out.println(String.format("New User Key : %d", userKey));
        } else {
            System.out.println(String.format("Error Code : %d", userKey));
            System.exit(0);
        }

        mytest.printMessage("Print out all users of org: " + orgObjKey);
        UserBaseBean[] users = mytest.getEmployees(orgObjKey);
        for (UserBaseBean user : users) {
            System.out.println(String.format("User: %s(%s)",
                    user.getUserName(), user.getUserID()));
        }

        mytest.printMessage("Get this newly created user");
        UserBaseBean user = mytest.getEmployee(mytest.userId);
        System.out.println(String.format("User: %s(%s)",
                user.getUserName(), user.getUserID()));
    }
}
