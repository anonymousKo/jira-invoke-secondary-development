package me.kesx.jira.controller;

import me.kesx.jira.service.CountService;
import me.kesx.jira.service.JiraConnectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/count")
@Api(tags = "统计次数", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountController {
    @Autowired
    CountService countService;
    @Autowired
    JiraConnectService jiraConnectService;

//    @GetMapping(value = "/reopen/{issueId}")
//    public Integer countReopen(@PathVariable("issueId") String issue){
//        return countService.countReopenTimes(issue);
//    }

    @GetMapping(value = "/return/{issueId}")
    @ApiOperation(value = "统计退回次数", tags = "v1.0")
    public Integer countReturn(@PathVariable("issueId") String issue){
        if(jiraConnectService.check(issue)){
            return countService.countReturnTimes(issue);
        }
        return 404;
    }
}
