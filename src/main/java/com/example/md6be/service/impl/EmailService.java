package com.example.md6be.service.impl;

import com.example.md6be.model.Customer;
import com.example.md6be.model.MyConstants;
import com.example.md6be.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService implements IEmailService {
    @Autowired
    public JavaMailSender mailSender;

//    public sendEmail(Customer customer){
//        LocalDateTime myDate = LocalDateTime.now();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formattedDate = dateTimeFormatter.format(myDate);
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(customer.getEmailAddress());
//        message.setSubject("THÔNG BÁO ĐĂNG KÝ TÀI KHOẢN THÀNH CÔNG");
//        message.setText("Chúc mừng bạn đã đăng ký tài khoản tại shop thành công \n"
//                + "Thông tin tài khoản: " + customer.getName() + "\n"
//                + "Email đăng ký: " + customer.getEmailAddress() + "\n"
//                + "Thời gian tạo: " + formattedDate + "\n"
//                + "Mọi thông tin phản hồi lại tại đây \n"
//                + "Cảm ơn bạn đã tham gia shop!");
//
//        mailSender.send(message);
//    }

    @Override
    public String sendEmail(Customer customer) {
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateTimeFormatter.format(myDate);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(customer.getEmailAddress());
        message.setSubject("THÔNG BÁO ĐĂNG KÝ TÀI KHOẢN THÀNH CÔNG");
        message.setText("Chúc mừng bạn đã đăng ký tài khoản tại shop thành công \n"
                + "Thông tin tài khoản: " + customer.getName() + "\n"
                + "Email đăng ký: " + customer.getEmailAddress() + "\n"
//                + "Mật khẩu: " + customer.getPassword() + "\n"
                + "Thời gian tạo: " + formattedDate + "\n"
                + "Mọi thông tin phản hồi lại tại đây \n"
                + "Cảm ơn bạn đã tham gia shop!");

        mailSender.send(message);
        return "Send email success";
    }
}
