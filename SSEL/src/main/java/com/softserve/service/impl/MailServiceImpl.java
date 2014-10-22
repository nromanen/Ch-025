package com.softserve.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.softserve.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger LOG = LoggerFactory
			.getLogger(MailServiceImpl.class);

	@Autowired
	private MailSender mailSender;

	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Override
	public void sendMail(String to, String subject, String body) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("email");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		try {
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
