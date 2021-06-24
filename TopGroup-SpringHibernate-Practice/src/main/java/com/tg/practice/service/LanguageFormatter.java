package com.tg.practice.service;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.tg.practice.serviceInterface.MessageFormatter;

public class LanguageFormatter implements MessageFormatter{
	
	private MessageSource ms;
	
	public String format(String s, Object[] o, Locale l) {
		return ms.getMessage(s, o, l);
	}

	public MessageSource getMs() {
		return ms;
	}

	public void setMs(MessageSource ms) {
		this.ms = ms;
	}
	
	
}
