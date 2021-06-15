package com.tg.practice.serviceInterface;

import java.util.Locale;

public interface MessageFormatter {
	
	String format(String s, Object[] o, Locale l);
}
