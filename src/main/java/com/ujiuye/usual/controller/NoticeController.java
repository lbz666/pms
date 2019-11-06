package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.common.ResultEntity;
import com.ujiuye.common.StringUtils;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/info/{id}")
    @ResponseBody
    public Notice getNoticeInfo(@PathVariable("id") Integer id){
        return noticeService.getNoticeInfo(id);
    }


    @RequestMapping(value = "/latestNoticeList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getLatestNoticeList(){
        List<Notice> notices = noticeService.getLatestNoticeList();
        return ResultEntity.success().put("notices",notices);
    }


    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNoticeList(HttpServletRequest request,  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){

        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parseparameterMapToString(parameterMap);

        String requestURI = request.getRequestURI();

        PageInfo<Notice> page = noticeService.getNoticeJsonList(pageNum,parameterMap);

        return ResultEntity.success().put("page",page).put("queryStr",queryStr).put("requestURI",requestURI);



    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return ResultEntity.success();
    }
}
