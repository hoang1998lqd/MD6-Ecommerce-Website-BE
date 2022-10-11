package com.example.md6be.service.impl;

import com.example.md6be.model.Customer;
import com.example.md6be.model.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Customer customer){
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateTimeFormatter.format(myDate);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL);
        message.setSubject("THÔNG BÁO ĐĂNG KÝ TÀI KHOẢN THÀNH CÔNG");
        message.setText("Chúc mừng bạn đã đăng ký tài khoản tại shop thành công \n"
                + "Thông tin tài khoản: " + customer.getName() + "\n"
                + "Email đăng ký: " + customer.getEmailAddress() + "\n"
                + "Thời gian tạo: " + formattedDate + "\n"
                + "Mọi thông tin phản hồi lại tại đây \n"
                + "Cảm ơn bạn đã tham gia shop!");

        mailSender.send(message);
    }
}
