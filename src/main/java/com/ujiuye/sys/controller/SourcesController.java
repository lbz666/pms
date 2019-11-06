package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {

    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){
        List<Sources> list = sourcesService.getSourcesByPid(0);
        queryChildren(list.get(0));
        return list;
    }

    //完成递归查询
    private void queryChildren(Sources source) {
        Integer id = source.getId();
        List<Sources> sources = sourcesService.getSourcesByPid(id);
        //递归：
        for (Sources source1 : sources) {
            queryChildren(source1);
        }
        
        source.setChildren(sources);
    }
}













