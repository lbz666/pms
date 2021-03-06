package com.ujiuye.compare.bean;

import java.util.Date;

public class BenchMarking {
    private Integer id;
    private String companyName;
    private double salesAmount;
    private Integer year;
    private String buisness;
    private String priority;
    private String disadvantage;
    private String status;
    private Integer emp_count;
    private Date createTime;
    private String simpleDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBuisness() {
        return buisness;
    }

    public void setBuisness(String buisness) {
        this.buisness = buisness;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmp_count() {
        return emp_count;
    }

    public void setEmp_count(Integer emp_count) {
        this.emp_count = emp_count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSimpleDesc() {
        return simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    @Override
    public String toString() {
        return "BenchMarking{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", salesAmount=" + salesAmount +
                ", year=" + year +
                ", buisness='" + buisness + '\'' +
                ", priority='" + priority + '\'' +
                ", disadvantage='" + disadvantage + '\'' +
                ", status='" + status + '\'' +
                ", emp_count=" + emp_count +
                ", createTime=" + createTime +
                ", simpleDesc='" + simpleDesc + '\'' +
                '}';
    }
}
