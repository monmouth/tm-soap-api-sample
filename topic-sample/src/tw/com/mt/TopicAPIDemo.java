/*
 * TopicAPIDemo.java    1.0 2014/7/11
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
import tw.com.mt.util.SubprojectServiceClient;
import com.ctu.tm.data.xsd.TopicBaseBean;

/**
 * Demo all project related API functions.
 *
 * @version 1.0 2014/7/10
 * @author ken
 *
 */
public class TopicAPIDemo {
    /**
     * The project key of the parent project.
     */
    private int projObjKey;
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
    public TopicAPIDemo() {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        try {
            config.addConfiguration(new PropertiesConfiguration(propertyFile));
            this.projObjKey = config.getInt("projObjKey");
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private TopicBaseBean getTopic(int topicObjKey) {
        TopicBaseBean topic = SubprojectServiceClient.getTopic(topicObjKey);
        return topic;
    }
    
    /**
     * Get all topics of the specified project.
     * @return all projects in an Array
     */
    private TopicBaseBean[] getAllTopics() {
        TopicBaseBean[] topics = SubprojectServiceClient.findTopicsTM75(
                projObjKey, ""// extra where
        );
        return topics;
    }

    /**
     * Create a new project.
     */
    private int createTopic() {
        Calendar cal = Calendar.getInstance();
        String topicName = "Topic-" + cal.getTimeInMillis();
        
        return SubprojectServiceClient.createTopicTM75(
                projObjKey, // projObjKey-欲建立子專案的專案的key值
                projObjKey, // parentObjKey-欲建立子專案於其下的專案/子專案的key值
                100,// topic owner key
                topicName, // topicSummary-子專案名稱
                "這是測試使用的", // topicDesc-子專案敘述
                "admin", // userID-建立者ID
                "", // uda20-自定欄位，請參閱2.3.2
                "", // uda21-自定欄位，請參閱2.3.2
                "", // uda22-自定欄位，請參閱2.3.2
                "", // uda23-自定欄位，請參閱2.3.2
                "", // uda24-自定欄位，請參閱2.3.2
                "", // uda25-自定欄位，請參閱2.3.2
                "", // uda26-自定欄位，請參閱2.3.2
                "", // uda27-自定欄位，請參閱2.3.2
                "", // uda28-自定欄位，請參閱2.3.2
                "", // uda29-自定欄位，請參閱2.3.2
                "", // uda30-自定欄位，請參閱2.3.2
                "", // uda31-自定欄位，請參閱2.3.2
                "", // uda32-自定欄位，請參閱2.3.2
                "", // uda33-自定欄位，請參閱2.3.2
                "", // uda34-自定欄位，請參閱2.3.2
                "", // uda35-自定欄位，請參閱2.3.2
                "", // uda36-自定欄位，請參閱2.3.2
                "", // uda37-自定欄位，請參閱2.3.2
                "", // uda38-自定欄位，請參閱2.3.2
                "" // uda39-自定欄位，請參閱2.3.2
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
        TopicAPIDemo mytest = new TopicAPIDemo();

        mytest.printMessage("Create a new topic");
        int topicObjKey = mytest.createTopic();
        if (topicObjKey > 0) {
            System.out.println(String.format("New Topic Key : %d",
                    topicObjKey));
        } else {
            System.out.println(String.format("Error Code : %d",
                    topicObjKey));
            System.exit(0);
        }
        
        mytest.printMessage("Print out all topics of project " + mytest.projObjKey);
        TopicBaseBean[] topics = mytest.getAllTopics();
        for (TopicBaseBean topic : topics) {
            System.out.println(String.format("Topic Name: %s - Owner Key: %s",
                    topic.getTopicSummary(), topic.getTopicOwnerKey()));
        }
        
        mytest.printMessage("Get this newly created topic");
        TopicBaseBean topic = mytest.getTopic(topicObjKey);
        System.out.println(String.format("Topic Name: %s - Owner Key: %s",
                topic.getTopicSummary(), topic.getTopicOwnerKey()));
    }
}
