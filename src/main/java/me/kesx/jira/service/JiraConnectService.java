package me.kesx.jira.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public interface JiraConnectService {
    HttpResponse<JsonNode> getChangelog(String issue);
    boolean check(String issue);
    JSONArray getHistories(String issue);
    HttpResponse<JsonNode> updateIssue(String issue, ObjectNode payload);
    HttpResponse<JsonNode> getField();
}
