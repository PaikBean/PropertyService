package com.propertyservice.propertyservice.service;

import com.propertyservice.propertyservice.repository.company.ManagerRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private JavaMailSender mailSender;
    private ManagerRepository managerRepository;
    private EntityExceptionService entityExceptionService;


    public void test(String sender, String receiver, String resetPassword) throws MessagingException {
        // MimeMessage : 이미지와 같이 메일에 첨부할 수 있음.
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // MimeMessage mimeMessage, boolean multipart, @Nullable String encoding
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(sender); // 발신인 ( 관리자 이메일로 설정 )
        mimeMessageHelper.setTo(receiver);   // 수신인.
        mimeMessageHelper.setSubject("[Property Service] 비밀번호 재설정 안내"); // 제목.

        // 메일 본문
        mimeMessageHelper.setText(getContent(resetPassword), true);

        //메일 보내기
        mailSender.send(mimeMessage);
    }

    public String getContent(String randomPassword){
        return "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"max-width: 600px;\">\n" +
                "    <div style=\"background-color: rgb(33, 47, 61); border-bottom: 2px solid rgb(86, 101, 115);  color: rgb(234, 236, 238); font-size: 28px; font-weight:bold; padding: 20px 30px;\">\n" +
                "        Property Service\n" +
                "    </div>\n" +
                "    <div style=\"line-height: 133%; padding: 30px;\">\n" +
                "        <span>안녕하세요!&nbsp;<b>Property Service</b>입니다.</span>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <span>해당 이메일 주소로 비밀번호 재설정을 요청하여 랜덤 비밀번호를 설정하여 전송하였습니다. </span>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <code style=\"background-color: rgb(234, 236, 238);  color: rgb(86, 101, 115);display: inline-block; font-size: 13px; padding: 15px;\">\n" +
                randomPassword +
                "        </code>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <span>해당 비밀번호를 타인에게 알려줄 경우 개인정보 도용의 위험이 있습니다. 절대 타인에게 알려주지 마세요.</span>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <span>혹시 본인이 요청한 적 없다면 이 이메일을 폐기해 주시기 바랍니다.</span>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <span>감사합니다.</span>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }



}
