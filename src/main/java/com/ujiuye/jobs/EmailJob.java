package com.ujiuye.jobs;

import com.ujiuye.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Email email = (Email)dataMap.get("email");

        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)dataMap.get("javaMailSenderImpl");
        Scheduler sched = (Scheduler)dataMap.get("scheduler");


        try {
            //邮件对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom("lbz971208@163.com");
            helper.setTo(email.getEname());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent(),true);

            //发送邮件
            javaMailSender.send(mimeMessage);
            System.out.println("EMAIL PASS");
            sched.shutdown(true);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
