package me.kesx.jira.service;

import java.text.ParseException;
import java.util.Date;

public interface FinishDateService {
    Date getDevFinishDate(String issue) throws ParseException;
    Date getTestFinishDate(String issue) throws ParseException;
}
