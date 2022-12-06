package com.example.finishwithboot.service;

import com.example.finishwithboot.model.Group;

import java.util.List;

public interface GroupService {

    void saveGroup(Group group, Long id);

    List<Group> getAllGroup(Long id);

    List<Group> getAllGroups();

    Group getById(Long id);

    void updateGroup(Long id, Group group);

    void deleteGroup(Long id);

//    void addGroupByCourseId(Long id, Group group);

    void assignGroupToCourse(Long courseId, Long groupId);

}
