package com.tg.practice.service;

import java.util.Locale;

import com.tg.practice.serviceInterface.MessageFormatter;

public class SignatureFormatter implements MessageFormatter{
	
	private final String signature;
	
	public SignatureFormatter(String signature) {
		this.signature = signature;
	}
	
	@Override
	public String format(String s, Object[] o, Locale l) {
		// TODO Auto-generated method stub
		return null;
	}

}
