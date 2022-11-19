package cn.yunhe.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/15 16:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mail.xml")
public class TestSpringMail {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage mailMessage;
    @Test
    public void testSend(){
        mailMessage.setTo("liu.7132@osu.edu");
        mailMessage.setSubject("测试spring整合javamail");
        mailMessage.setText("我是一个测试spring整合javamail的内容");
        javaMailSender.send(mailMessage);
    }

    /**
     * 测试发送图片邮件
     */
    @Test
    public void testSendImage() throws MessagingException {
        JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) this.javaMailSender;
        //创建MimeMessage ，封装带有图片的内容
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("shizhanliu0627@sina.com");
        mimeMessageHelper.setTo("shizhanliu0627@sina.com");
        mimeMessageHelper.setSubject("测试邮件中嵌套图片!！");
        // true 表示启动HTML格式的邮件
        mimeMessageHelper.setText(
                "<html><body><h1>hello!!spring image html mail</h1><img src = cid:image/></body></html>", true);
        FileSystemResource img = new FileSystemResource("D:/p1.jpg");
        mimeMessageHelper.addInline("image",img);
        javaMailSenderImpl.send(mimeMessage);
        System.out.println("successful");
    }

    /**
     * 发送带附件的邮件 测试成功
     * @throws MessagingException
     */
    @Test
    public void testSendAttachment() throws MessagingException {
        JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) this.javaMailSender;
        //创建MimeMessage ，封装带有图片的内容
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("shizhanliu0627@sina.com");
        mimeMessageHelper.setTo("shizhanliu0627@sina.com");
        mimeMessageHelper.setSubject("测试邮件中包含附件!！");
        // true 表示启动HTML格式的邮件
        mimeMessageHelper.setText(
                "<html><body><h1>hello!!spring attachment html mail</h1>"
                        +"</body></html>", true);
        FileSystemResource file = new FileSystemResource("D:/excel1.xls");
        mimeMessageHelper.addInline("salary.xls",file);
        javaMailSenderImpl.send(mimeMessage);
        System.out.println("successful");





    }


}
