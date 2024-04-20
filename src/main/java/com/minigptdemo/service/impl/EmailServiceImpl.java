package com.minigptdemo.service.impl;

import com.minigptdemo.constant.RedisConstants;
import com.minigptdemo.pojo.Email;
import com.minigptdemo.service.EmailService;
import com.minigptdemo.service.redis.RedisService;
import com.minigptdemo.utils.checkcode.CheckCodeUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private RedisService redisService;
    @Resource
    private JavaMailSenderImpl javaMailSender;

    //邮箱的各种配置信息
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    private static final String fromPersonal = "筋斗云团队";
    private static final String subject = "[SomersaultCloud]";


    //发送注册邮箱验证码
    public String sendRegisterMail(String mailAddress) {
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Properties props = javaMailSender.getJavaMailProperties();
        System.out.println(props);
        try {
            String checkCode = CheckCodeUtil.generateVerifyCode(6,null);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            //发件人
            helper.setFrom(username, fromPersonal);
            //收件人
            helper.setTo(mailAddress);
            helper.setSubject(subject);
            //选择渲染模板
            StringWriter writer = renderMail("register-email.ftl",checkCode);
            helper.setText(writer.toString(), true);
            javaMailSender.send(mimeMessage);
            redisService.setValueByMin(RedisConstants.REGISTER_EMAIL_CODE+mailAddress,checkCode,10);
            return checkCode;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    //选择渲染模板
    public StringWriter renderMail(String template,String checkCode) throws IOException, TemplateException {
        //模板渲染
        Configuration config = new Configuration(Configuration.VERSION_2_3_0);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 设置模板文件所在的类路径位置
        config.setClassLoaderForTemplateLoading(classLoader, "/template");
        // 加载模板文件
        Template model = config.getTemplate(template);
        Map<String, String> map = new HashMap<>();
        map.put("checkCode", checkCode);
        // 渲染模板
        StringWriter writer = new StringWriter();
        model.process(map, writer);
        return writer;
    }

    //校验注册邮箱验证码
    public boolean checkRegisterMail(Email email) {
        String rightCode = (String) redisService.getValue(RedisConstants.REGISTER_EMAIL_CODE+email.getAddress());
        //忽略大小写
        //校验后兑消缓存验证码
        if(email.getEmailCode().equalsIgnoreCase(rightCode)){
            redisService.deleteValue(RedisConstants.REGISTER_EMAIL_CODE+email.getAddress());
            return true;
        }else {
            return false;
        }
    }
}
