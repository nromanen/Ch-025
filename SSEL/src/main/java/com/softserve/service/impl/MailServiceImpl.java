package com.softserve.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.softserve.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger LOG = LoggerFactory
			.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Override
	public void sendMail(String to, String subject, String body) {
		final MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("email");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			executorService.submit(new Runnable() {

				@Override
				public void run() {
					mailSender.send(message);
				}
			});
		} catch (Exception e) {
			LOG.error("Errors in sendMail method. Tried to send mail(to = {})",
					to, e);
		}

	}

}
