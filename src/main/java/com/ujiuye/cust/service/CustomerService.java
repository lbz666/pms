package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;

import java.util.List;

public interface CustomerService {

    public void saveInfo(Customer customer);

    List<Customer> getCustomerList();


    Customer getCustomeDetail(Integer id);

    void updateCustomer(Customer customer);

    List<Customer> search(Integer cid, String keyword, Integer orderby);


    boolean batchDelete(Integer[] ids);
}
