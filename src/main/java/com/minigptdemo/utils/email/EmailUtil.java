package com.minigptdemo.utils.email;

import com.minigptdemo.constant.RedisConstants;
import com.minigptdemo.service.redis.RedisService;
import com.minigptdemo.utils.checkcode.CheckCodeUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



//TODO 待改: 屎山邮件工具类

public class EmailUtil {


    //发送注册邮件
    public String sendRegisterMail(String mailAddress){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("1750859115@qq.com");
        javaMailSender.setPassword("rdrrkprirrxmfafg");

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            //发件人
            helper.setFrom("1750859115@qq.com","筋斗云团队");
            //收件人
            helper.setTo(mailAddress);
            helper.setSubject("[SomersaultCloud]");

            //模板渲染
            Configuration config = new Configuration(Configuration.VERSION_2_3_0);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // 设置模板文件所在的类路径位置
            config.setClassLoaderForTemplateLoading(classLoader, "/template");

            // 加载模板文件
            Template template = config.getTemplate("register-email.ftl");
            Map<String,String> map = new HashMap<>();
            String checkCode = CheckCodeUtil.generateVerifyCode(6,null);
            map.put("checkCode", checkCode);


            // 渲染模板
            StringWriter writer = new StringWriter();
            template.process(map, writer);

            helper.setText(writer.toString(), true);
            javaMailSender.send(mimeMessage);
            return checkCode;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
