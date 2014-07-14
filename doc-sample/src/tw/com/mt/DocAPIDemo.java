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
import tw.com.mt.util.TaskServiceClient;

import com.ctu.tm.data.xsd.DocBaseBean;
import com.ctu.tm.data.xsd.TaskBaseBean;

/**
 * Demo all project related API functions.
 * 
 * @version 1.0 2014/7/10
 * @author ken
 * 
 */
public class DocAPIDemo {
    /**
     * The project key of the parent project.
     */
    private int projObjKey;
    /**
     * The object key of the parent object, it could be a task, topic or project.
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
    public DocAPIDemo() {
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

    private DocBaseBean getDoc(int docObjKey) {
        DocBaseBean doc = DocServiceClient.getDoc(docObjKey);
        return doc;
    }

    /**
     * Get all docs of the specified project.
     * 
     * @return all docs in an Array
     */
    private DocBaseBean[] getAllDocs() {
        DocBaseBean[] docs = DocServiceClient.loadDocsByRootObjKey(projObjKey);
        return docs;
    }

    /**
     * Create a new project.
     */
    private int createDoc() {
        String summary = "西 江 月";
        String content = "明月別枝驚鵲，清風半月鳴蟬，稻花香裡說豐年，聽取蛙聲一片"
                + "。七八個星天外，兩三點雨山前，舊時茆店社林邊，路轉溪橋忽見。";

        return DocServiceClient.createDoc("admin", // userID-建立者
                projObjKey, // rootObjKey-文件建立的專案key，如為文件中心則為100
                parentObjKey, // parentObjKey-文件建立的所屬位置的key，
                              // 如無目錄則與rootObjKey相同
                0, // folderObjKey-專案文件分類夾
                0, // processKey-套用流程key值
                AppConstants.DOC_TYPE_NOTES, // docType-要建立的文件類型
                0, // size-文件檔案大小
                summary, // docSummary-文件摘要
                content, // appLongData-文件檔名，記事本內容
                "", // appShortData-網址，檔案類型時輸
                    // //入"N;application/msword;檔案名稱;使用編碼"
                "", // docContent-全文檢索要使用的文件內容
                "", // knowledgeType-知識類型
                "N", // isRequiredFlag-是否為定義產出文件
                "100", // serialNumber-文件編號
                true, // anyoneCanAccess-是否允許全部成員檢視
                "", // accessList-存取成員,(userID,userID,userID)
                "", // currVersion-文件版本
                "", // verifiedAt-審核日期
                0, // confidentialityKey-文件機密等級
                0, // stateKey-流程狀態key值
                "", // docServerID-存放文件檔案的文件伺服器ID
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
                "", // uda0-自定欄位，請參閱2.3.2
                "", // uda1-自定欄位，請參閱2.3.2
                "", // uda2-自定欄位，請參閱2.3.2
                "", // uda3-自定欄位，請參閱2.3.2
                "", // uda4-自定欄位，請參閱2.3.2
                "", // uda5-自定欄位，請參閱2.3.2
                "", // uda6-自定欄位，請參閱2.3.2
                "", // uda7-自定欄位，請參閱2.3.2
                "", // uda8-自定欄位，請參閱2.3.2
                "", // uda9-自定欄位，請參閱2.3.2
                "", // uda10-自定欄位，請參閱2.3.2
                "", // uda11-自定欄位，請參閱2.3.2
                "", // uda12-自定欄位，請參閱2.3.2
                "", // uda13-自定欄位，請參閱2.3.2
                "", // uda14-自定欄位，請參閱2.3.2
                "", // uda15-自定欄位，請參閱2.3.2
                "", // uda16-自定欄位，請參閱2.3.2
                "", // uda17-自定欄位，請參閱2.3.2
                "", // uda18-自定欄位，請參閱2.3.2
                "", // uda19-自定欄位，請參閱2.3.2
                "", // uda60-自定欄位，請參閱2.3.2
                "", // uda61-自定欄位，請參閱2.3.2
                "", // uda62-自定欄位，請參閱2.3.2
                "", // uda63-自定欄位，請參閱2.3.2
                "", // uda64-自定欄位，請參閱2.3.2
                "", // uda65-自定欄位，請參閱2.3.2
                "", // uda66-自定欄位，請參閱2.3.2
                "", // uda67-自定欄位，請參閱2.3.2
                "", // uda68-自定欄位，請參閱2.3.2
                "", // uda69-自定欄位，請參閱2.3.2
                "", // uda70-自定欄位，請參閱2.3.2
                "", // uda71-自定欄位，請參閱2.3.2
                "", // uda72-自定欄位，請參閱2.3.2
                "", // uda73-自定欄位，請參閱2.3.2
                "", // uda74-自定欄位，請參閱2.3.2
                "", // uda75-自定欄位，請參閱2.3.2
                "", // uda76-自定欄位，請參閱2.3.2
                "", // uda77-自定欄位，請參閱2.3.2
                "", // uda78-自定欄位，請參閱2.3.2
                "", // uda79-自定欄位，請參閱2.3.2
                "Y", // notificationFlag-是否郵件通知(Y/N)
                "ken@mt.com.tw", // mailTo-寄件者
                "ken@mt.com.tw", // extraEmailTo-收件者
                "", // hiddenExtraEmailTo-密件附本
                "郵件通知", // emailSubject-郵件標題
                "郵件通知內容", // emailContents-郵件內容
                "tw" // language-郵件使用的語言
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
        DocAPIDemo mytest = new DocAPIDemo();

        mytest.printMessage("Create a new doc");
        int docObjKey = mytest.createDoc();
        if (docObjKey > 0) {
            System.out
                    .println(String.format("New Doc Key : %d", docObjKey));
        } else {
            System.out.println(String.format("Error Code : %d", docObjKey));
            System.exit(0);
        }

        mytest.printMessage("Print out all docs of project "
            + mytest.projObjKey);
        DocBaseBean[] docs = mytest.getAllDocs();
        for (DocBaseBean doc : docs) {
            System.out.println(String.format("Doc Summary: %s - Created By: %s",
                    doc.getDocSummary(), doc.getCreatedByID()));
        }

        mytest.printMessage("Get this newly created doc");
        DocBaseBean doc = mytest.getDoc(docObjKey);
        System.out.println(String.format("Doc Summary: %s - Created By: %s",
                doc.getDocSummary(), doc.getCreatedByID()));
    }
}
