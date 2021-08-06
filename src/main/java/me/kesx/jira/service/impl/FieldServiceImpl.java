package me.kesx.jira.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.kesx.jira.service.FieldService;
import me.kesx.jira.service.JiraConnectService;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    JiraConnectService jiraConnectService;
    @Override
    public String findFieldId(String filedName){
        JsonNode fields = jiraConnectService.getField().getBody();
        return "";
    }

    @Override
    public void editField(String issueId, Date finishDate){
        // The payload definition using the Jackson library
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            ObjectNode update = payload.putObject("update");
            {
                ArrayNode date = update.putArray("customfield_10103");
                ObjectNode date0 = date.addObject();
                {
                    date0.put("set", String.valueOf(finishDate));
                }
            }
        }
        Unirest.config().setObjectMapper(new kong.unirest.ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        System.out.println(jiraConnectService.updateIssue(issueId,payload).getStatus());

    }

}
