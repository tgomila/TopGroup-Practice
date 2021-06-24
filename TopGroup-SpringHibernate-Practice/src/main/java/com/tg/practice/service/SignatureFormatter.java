package com.tg.practice.service;

import java.util.Locale;

import com.tg.practice.serviceInterface.MessageFormatter;

public class SignatureFormatter implements MessageFormatter{
	
	private String signature;
	
	public SignatureFormatter(String signature) {
		this.signature = signature;
	}
	
	public SignatureFormatter() {
	}
	
	public String format(String s, Object[] o, Locale l) {
		String mensaje = "";
		
		for(Object objeto: o) {
			mensaje = mensaje + objeto.toString();
		}
		
		String ret = s + " - " + this.signature + mensaje.length();
		return ret;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	

}
