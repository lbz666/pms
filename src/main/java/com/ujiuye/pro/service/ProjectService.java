package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;

import java.util.List;

public interface ProjectService {
    void saveInfo(Project project);

    List<Project> getProjectList();

    Project detail(Integer pid);


    void update(Project project);


    boolean batchDelete(Integer[] ids);

    List<Project> search(Integer cid, String keyword, Integer orderby);


    List<Project> getProjectJsonList();

    List<Project> getProjectwithAnalyseJsonList();
}
