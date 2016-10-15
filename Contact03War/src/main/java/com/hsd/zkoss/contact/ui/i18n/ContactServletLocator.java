package com.hsd.zkoss.contact.ui.i18n;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import javax.servlet.ServletContext;

public class ContactServletLocator implements org.zkoss.util.resource.LabelLocator {
	private ServletContext svlctx;
	private String name;

	public ContactServletLocator(ServletContext svlctx, String name) {
		this.svlctx = svlctx;
		this.name = name;
	}

	public URL locate(Locale locale) throws MalformedURLException {
		return svlctx.getResource("/WEB-INF/" + name + "_" + locale + ".properties");
	}

}
