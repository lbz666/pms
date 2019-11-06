package com.offcn;

import com.ujiuye.cust.bean.Goods;
import com.ujiuye.cust.service.GoodsServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-quartz.xml");

    }

    @Test
    public void test03(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        GoodsServiceImpl bean = context.getBean(GoodsServiceImpl.class);
        List<Goods> goodsList = bean.getGoodsList();


    }
    @Test
    public  void test01() throws  Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);

        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

        helper.setFrom("lbz971208@163.com");
        helper.setTo("lbz971208@163.com");
        helper.setSubject("这是一封测试邮件");
        helper.setText("<html><head></head><body>对方水电费<img src=cid:identifile><span style='color:red'>嘿嘿嘿嘿嘿嘿嘿</span><h1>禄口街道</h1></body></html>",true);
        //添加附件
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\11.jpg"));
        helper.addAttachment("cat.jpg",file); //必须添加后缀，否则不知道如何解析
        //图片作为内置资源
        FileSystemResource file1 = new FileSystemResource(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\22.jpg"));
        helper.addInline("identifile",file1);
        //发送邮件
        bean.send(mimeMessage);
        System.out.println("EMAIL PASS");
    }
}
