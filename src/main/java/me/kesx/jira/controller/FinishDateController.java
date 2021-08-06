package me.kesx.jira.controller;

import me.kesx.jira.service.FieldService;
import me.kesx.jira.service.FinishDateService;
import me.kesx.jira.service.JiraConnectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/date")
@Api(tags="完成时间", produces = MediaType.APPLICATION_JSON_VALUE)
public class FinishDateController {
    @Autowired
    FinishDateService finishDateService;
    @Autowired
    JiraConnectService jiraConnectService;
    @Autowired
    FieldService fieldService;

    @ApiOperation(value = "查询开发完成日期", tags = "v1.0")
    @RequestMapping(value = "/dev/{issueId}")
    public Date dev(@PathVariable("issueId") String issue) {
        Date finishDate = null;
        try {
            finishDate = finishDateService.getDevFinishDate(issue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fieldService.editField(issue,finishDate);
        return finishDate;
    }
    @ApiOperation(value = "查询测试完成日期", tags = "v1.0")
    @GetMapping(value = "/test/{issueId}")
    public Date test(@PathVariable("issueId") String issue){
        Date finishDate = null;

        try{
            finishDate = finishDateService.getTestFinishDate(issue);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return finishDate;
    }
}
