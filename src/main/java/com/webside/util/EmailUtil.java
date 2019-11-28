package com.webside.util;

import javax.mail.AuthenticationFailedException;

import jodd.mail.Email;
import jodd.mail.MailException;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: EmailUtil
 * @Description: 邮件发送工具类,封装了jodd的mail工具类
 * @author gaogang
 * @date 2016年7月12日 下午4:22:12
 *
 */
@Component
public class EmailUtil {
	

	@Value("${mail.126.username}")
	private String USER_126;

	@Value("${mail.126.password}")
	private String PASSWORD_126;


	/**
	 * 发送126邮箱
	 * 
	 * @param toMail
	 * @param subject
	 * @param text
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean send126Mail(String toMail, String subject, String text) throws AuthenticationFailedException,MailException{
//		Email email = Email.create().from(USER_126).to(toMail)
//				.subject(subject).addText(text);
//		SmtpServer smtpServer = //SmtpServer.create("smtp.126.com")
//				SmtpServer.create("smtp.qq.com")
//				.authenticateWith(USER_126, PASSWORD_126);
//		SendMailSession session = smtpServer.createSession();
//		session.open();
//		session.sendMail(email);
//		session.close();
		return true;
	}
	
	public static void main(String[] args){
		EmailUtil eu = new EmailUtil();
		eu.USER_126     = "phydig@126.com";
		eu.PASSWORD_126 = "abc123";
		
		String toMail  = "3401890365@qq.com";
		String subject = "您的账号已经创建";
		String text    = "您的密码为123q2q";
		try{
			eu.send126Mail(toMail, subject, text);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
