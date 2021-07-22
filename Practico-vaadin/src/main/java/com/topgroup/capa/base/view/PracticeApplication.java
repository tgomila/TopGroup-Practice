
package com.topgroup.capa.base.view;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.topgroup.capa.base.view.screen.*;
import com.vaadin.Application;
import com.vaadin.ui.Window;

public class PracticeApplication extends Application {
	private static final long serialVersionUID = -2719039325987214154L;

	private Window window;
	private static ClassPathXmlApplicationContext context;

	@Override
	public void init() {
		setTheme("applayout");
		window = new Window("My Vaadin Application");
		setMainWindow(window);
		
		//window.addComponent(new PracticeScreen());
		//context = new ClassPathXmlApplicationContext(new String[] {"data-layer-context.xml"});
		//window.addComponent(new PracticeScreenTest());
		window.addComponent(new PracticeScreenTom());
		
		

	}

}
