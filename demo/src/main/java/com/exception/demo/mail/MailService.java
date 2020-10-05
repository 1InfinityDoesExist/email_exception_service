package com.exception.demo.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

@Component
public class MailService {

    @Autowired
    private GmailProvider gmailProvider;

    @Value("${reset.password.email.host}")
    private String host;
    @Value("${reset.password.email.port}")
    private int port;
    @Value("${reset.password.email.id}")
    private String emailId;
    @Value("${reset.password.email.pwd}")
    private String password;
    @Value("${reset.password.email.provider}")
    private String provider;
    @Value("${reset.password.email.subject}")
    private String subject;

    public void sendMail(ModelMap modelMap) throws Exception {

        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(host);
        javaMailSenderImpl.setPort(port);
        javaMailSenderImpl.setUsername(emailId);
        javaMailSenderImpl.setPassword(password);
        javaMailSenderImpl.setJavaMailProperties(getMailProperties(provider));
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject((String) modelMap.get("subject"));
        mimeMessageHelper.setText((String) modelMap.get("mailBody"));
        if (modelMap.get("to") instanceof List) {
            mimeMessageHelper.setTo((String[]) modelMap.get("to"));
        } else {
            mimeMessageHelper.setTo((String) modelMap.get("to"));
        }
        javaMailSenderImpl.send(mimeMessage);

    }

    private Properties getMailProperties(String provider) throws Exception {
        Properties properties = new Properties();
        if (StringUtils.isEmpty(provider)) {
            // exception
        }
        if (provider.equalsIgnoreCase("GOOGLE")) {
            getGmailProperties(properties);
        }
        return properties;
    }

    private void getGmailProperties(Properties properties) {
        gmailProvider.getGmailProperties().forEach((key, value) -> {
            properties.setProperty(key, value);
        });
    }
}
