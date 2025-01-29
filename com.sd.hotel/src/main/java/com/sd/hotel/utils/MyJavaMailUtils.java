package com.sd.hotel.utils;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


//application.properties 파일에서 속성을 읽어오도록 설정합니다.
@PropertySource(value = "classpath:application.properties")
@Component
public class MyJavaMailUtils {


  // Spring의 Environment 객체를 주입받습니다.
  @Autowired
  private Environment env;
  
  public void sendMail(String to, String subject, String content) {
    
    // 이메일을 보내는 호스트의 정보 : 구글 , SMTP 서버 설정 구성
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 서버 호스트 설정
    props.put("mail.smtp.port", 587); // SMTP 서버 포트 설정
    props.put("mail.smtp.auth", true);  // SMTP 인증 사용 설정
    props.put("mail.smtp.starttls.enable", true); // TLS 보안 사용 설정
    
    // javax.mail.Session 객체 생성 : 이메일을 보내는 사용자의 정보 (개인 정보)
    Session session = Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(env.getProperty("spring.mail.username")
                                        , env.getProperty("spring.mail.password"));
      }
    });
    
    try {
      
      // 메일 만들기 (보내는 사람 + 받는 사람 + 제목 + 내용)
      MimeMessage mimeMessage = new MimeMessage(session);
      mimeMessage.setFrom(new InternetAddress(env.getProperty("spring.mail.username"), "SD Hotel")); //발신자 설정
      mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 수신자 설정
      mimeMessage.setSubject(subject);
      mimeMessage.setContent(content, "text/html; charset=UTF-8");
      
      // 메일 보내기
      Transport.send(mimeMessage);
      
    } catch (Exception e) {
      e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력합니다.
    }
    
  }
  
}
