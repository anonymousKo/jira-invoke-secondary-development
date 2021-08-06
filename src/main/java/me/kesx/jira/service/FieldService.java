package me.kesx.jira.service;

import java.util.Date;

public interface FieldService {
    String findFieldId(String filedName);
    void editField(String issueId, Date finishDate);
}
