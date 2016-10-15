package com.hsd.zkoss.contact.ui.workflow;

import javax.servlet.ServletContext;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.WebApp;

import com.hsd.zkoss.contact.ui.i18n.ContactServletLocator;

public class ContactAppInit implements org.zkoss.zk.ui.util.WebAppInit {
	
	public void init(WebApp wapp) throws Exception {
		Labels.register(new ContactServletLocator((ServletContext) wapp.getNativeContext(), "zk-label"));
	}
	
}
