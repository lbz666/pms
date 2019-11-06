package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/withAnalyseJsonList", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectwithAnalyseJsonList(){
        return projectService.getProjectwithAnalyseJsonList();
    }

    //附件下拉框
    @RequestMapping(value = "/jsonList", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectJsonList(){
        return projectService.getProjectJsonList();
    }


    //查询项目
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid, String keyword,Integer orderby){
        List<Project> list = projectService.search(cid, keyword, orderby);
        ModelAndView mv = new ModelAndView("project-base");
        mv.addObject("list",list);
        return mv;
    }

    //删除项目
    @RequestMapping(value = "/del", method = RequestMethod.DELETE )
    @ResponseBody
    public Map<String, Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result = projectService.batchDelete(ids);
        Map<String, Object> map = new HashMap<>();
        if (result){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else {
            map.put("statusCode",500);
            map.put("message","删除失败");
        }

        return map;
    }

    //更新项目
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(Project project){
        projectService.update(project);
        return "redirect:/pro/list";
    }

    //项目编辑
    @RequestMapping(value = "/edit/{pid}", method = RequestMethod.GET)
    public String edit(@PathVariable("pid") Integer pid,Map<String, Object> map){
        Project project = projectService.detail(pid);
        map.put("project",project);
        return "project-base-edit";
    }

    //查看详情
    @RequestMapping(value = "/detail/{pid}", method = RequestMethod.GET)
    public String detail(@PathVariable("pid") Integer pid, Map<String,Object> map){
        Project project = projectService.detail(pid);
        map.put("project",project);
        return "project-base-look";

    }


    //添加项目
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String  saveInfo(Project project){
        projectService.saveInfo(project);
        return "redirect:/pro/list";
    }
    //显示全部项目
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView getProjectList(){
        List<Project> list = projectService.getProjectList();
        ModelAndView mv = new ModelAndView("project-base");
        mv.addObject("list",list);
        return mv;
    }

}
