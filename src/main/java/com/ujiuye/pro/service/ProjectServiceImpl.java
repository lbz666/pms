package com.ujiuye.pro.service;

import com.ujiuye.cust.bean.CustomerExample;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public List<Project> getProjectList() {
        List<Project> projects = projectMapper.selectByExample(new ProjectExample());
        return projects;
    }

    @Override
    public Project detail(Integer pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        return project;
    }

    @Override
    public void update(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(idList);
        int i = projectMapper.deleteByExample(example);
        return ids.length == i;

    }


    @Override
    public List<Project> search(Integer cid, String keyword, Integer orderby) {

        return projectMapper.search(cid, keyword, orderby);
    }

    @Override
    public List<Project> getProjectJsonList() {
        List<Project> projects = projectMapper.selectByExample(new ProjectExample());

        return projects;
    }

    @Override
    public List<Project> getProjectwithAnalyseJsonList() {
        return projectMapper.getProjectwithAnalyseJsonList();
    }


}
