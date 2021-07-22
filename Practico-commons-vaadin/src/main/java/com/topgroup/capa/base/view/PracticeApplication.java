
package com.topgroup.capa.base.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.topgroup.capa.base.view.screen.PracticeScreen;
import com.vaadin.Application;
import com.vaadin.ui.Window;

@Component
@Scope("session")
public class PracticeApplication extends Application {
	private static final long serialVersionUID = -2719039325987214154L;

	private Window window;
	
	@Autowired
	private PracticeScreen practiceScreen;// = new PracticeScreen();

	@Override
	public void init() {
		setTheme("applayout");
		window = new Window("My Vaadin Application");
		setMainWindow(window);
		
		window.addComponent(practiceScreen);

		

	}

}
