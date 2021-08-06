package me.kesx.jira.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.kesx.jira.service.JiraConnectService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JiraConnectServiceImpl implements JiraConnectService {
    @Value("${jira.url}")
    private String url;
    @Value("${jira.user}")
    private String user;
    @Value("${jira.pass}")
    private String pass;

    String restUrl = "http://"+ url +"/rest/api/2";
    @Override
    public  HttpResponse<JsonNode> getChangelog(String issue){
        return Unirest.get(restUrl + "/issue/" + issue + "?expand=changelog")
                .basicAuth(user, pass)
                .header("Accept", "application/json")
                .asJson();
    }

    @Override
    public boolean check(String issue) {
        HttpResponse<JsonNode> response = getChangelog(issue);
        return response.getStatus() == 200;
    }

    @Override
    public JSONArray getHistories(String issue){
        HttpResponse<JsonNode> response = getChangelog(issue);
        JSONObject jsonObject = JSONObject.parseObject(String.valueOf(response.getBody()));
        return(jsonObject.getJSONObject("changelog").getJSONArray("histories"));
    }
    @Override
    public HttpResponse<JsonNode> updateIssue(String issue, ObjectNode payload){
        return Unirest.put(restUrl + "/issue/" + issue)
                .basicAuth(user, pass)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload)
                .asJson();
    }
    @Override
    public HttpResponse<JsonNode> getField(){
        return Unirest.get(restUrl + "/field/")
                .basicAuth(user, pass)
                .header("Accept", "application/json")
                .asJson();
    }
}
