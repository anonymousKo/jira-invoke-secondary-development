package me.kesx.jira.service.impl;

import com.alibaba.fastjson.JSONArray;
import me.kesx.jira.service.CountService;
import me.kesx.jira.service.JiraConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountServiceImpl implements CountService {
    @Autowired
    JiraConnectService jiraConnectService;

//    @Override
//    public Integer countReopenTimes(String issue){
//        Integer reopenTimes = 0;
//        JSONArray histories = getLogs(issue);
//        for(int i = 0 ;i < histories.size();i++){
//            String fromString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("fromString");
//            String toString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("toString");
//            System.out.println(fromString + toString);
//            if("Done".equals(fromString) || "完成".equals(fromString)){
//                reopenTimes++;
//            }
//        }
//        return reopenTimes;
//    }

    @Override
    public Integer countReturnTimes(String issue){
        Integer returnTimes = 0;
        JSONArray histories = jiraConnectService.getHistories(issue);
        for(int i = 0 ;i < histories.size();i++){
            String fromString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("fromString");
            String toString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("toString");
//            System.out.println(fromString + toString);
            if("测试中".equals(fromString) && "REOPEN".equals(toString)){
                returnTimes++;
            }
        }
        return returnTimes;
    }
}
