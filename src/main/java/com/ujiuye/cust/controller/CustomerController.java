package com.ujiuye.cust.controller;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> exportExcel(){
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("customers");
        sheet1.setColumnWidth(3,4000);
        Row row = sheet1.createRow(0);
        Cell[] cell = new HSSFCell[5];
        for (int i = 0; i < 5; i ++){
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("ID");
        cell[1].setCellValue("联系人");
        cell[2].setCellValue("公司名称");
        cell[3].setCellValue("添加时间");
        cell[4].setCellValue("联系电话");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Customer> customerList = customerService.getCustomerList();

        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            Row row1 = sheet1.createRow(i + 1);

            Cell[] cell1 = new HSSFCell[5];
            for (int j = 0; j < 5; j ++){
                cell1[j] = row1.createCell(j);
            }
            cell1[0].setCellValue(customer.getId());
            cell1[1].setCellValue(customer.getCompanyperson());
            cell1[2].setCellValue(customer.getComname());
            Date addtime = customer.getAddtime();
            String format = sdf.format(addtime);
            cell1[3].setCellValue(format);
            cell1[4].setCellValue(customer.getComphone());
        }
        FileOutputStream fos= null;

        try {
            fos= new FileOutputStream(new File("d:\\Desktop\\customers1.xls"));
            wb.write(fos);
            fos.close();
        }catch (Exception ex){

        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "下载成功");
        return map;

    }

    //查看负责人(异步方式)
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer info(@PathVariable("id") Integer id){
        Customer customer = customerService.getCustomeDetail(id);
        return customer;
    }


    //查看客户(异步方式)
    @RequestMapping(value = "/jsonList", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerJsonList(){

        return customerService.getCustomerList();
    }

    //批量删除用户
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result = customerService.batchDelete(ids);
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

    //搜索查询
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(Integer cid, String keyword, Integer orderby){
        List<Customer> list = customerService.search(cid, keyword, orderby);
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list",list);
        return mv;

    }

    //更新客户
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(Customer customer){
        customerService.updateCustomer(customer);
        return "redirect:/cust/list";
    }

    //编辑用户
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Map<String,Object> map){
        Customer customer = customerService.getCustomeDetail(id);
        map.put("customer", customer);
        return "customer-edit";
    }

    //查看客户(同步方式)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getCustomerList(){
        List<Customer> list = customerService.getCustomerList();
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list",list);
        return mv;

    }
    //查看详情
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Map<String,Object> map){
        Customer customer = customerService.getCustomeDetail(id);
        map.put("customer", customer);
        return "customer-look";
    }
    //添加客户
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        customerService.saveInfo(customer);
        return "redirect:/cust/list";
    }
}
