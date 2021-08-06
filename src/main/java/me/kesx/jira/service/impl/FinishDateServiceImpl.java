package me.kesx.jira.service.impl;

import com.alibaba.fastjson.JSONArray;
import me.kesx.jira.service.FinishDateService;
import me.kesx.jira.service.JiraConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FinishDateServiceImpl implements FinishDateService {
    @Autowired
    JiraConnectService jiraConnectService;

    @Override
    public Date getDevFinishDate(String issue) throws ParseException {
        JSONArray histories = jiraConnectService.getHistories(issue);
        for(int i = histories.size() - 1;i >0 ;i--){
            String fromString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("fromString");
            String toString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("toString");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            if("开发中".equals(fromString) && "测试中".equals(toString)){

                return simpleDateFormat.parse((String) histories.getJSONObject(i).get("created"));
            }
        }
        return null;
    }

    @Override
    public Date getTestFinishDate(String issue) throws ParseException {
        JSONArray histories = jiraConnectService.getHistories(issue);
        for(int i = histories.size() - 1;i >0 ;i--){
            String fromString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("fromString");
            String toString = (String) histories.getJSONObject(i).getJSONArray("items").getJSONObject(0).get("toString");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            if("测试中".equals(fromString) && "开发中".equals(toString)){

                return simpleDateFormat.parse((String) histories.getJSONObject(i).get("created"));
            }
        }
        return null;
    }

}
