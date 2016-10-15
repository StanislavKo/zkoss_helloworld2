package com.hsd.zkoss.contact.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.zkoss.bind.Binder;
import org.zkoss.bind.DefaultBinder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import com.hsd.zkoss.contact.ui.model.bean.UIAddress;
import com.hsd.zkoss.contact.ui.model.bean.UIContact;

public class AddressViewModel {

	private static Logger logger = Logger.getLogger(AddressViewModel.class);

	private UIAddress address = new UIAddress(UUID.randomUUID().toString());

	// @Wire("#todoList")
	// private Listbox todoList;
	// private ListModelList<TodoItem> todoListModel;

	public UIAddress getAddress() {
		return address;
	}

	public void setAddress(UIAddress address) {
		this.address = address;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		logger.info("afterCompose()");
		
		Session sess = Sessions.getCurrent();
		if (sess.getAttribute("address") != null) {
			address = (UIAddress) sess.getAttribute("address");
		}
		
		Selectors.wireComponents(view, this, false);
	}

	@Command("onCreate")
	public void onCreate() {
		logger.info("onCreate()");
	}

	@Command
	public void cancel() {
		logger.info("cancel()");
//		addresses.add(new UIAddress());
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("address");
		
		Executions.sendRedirect("/index.zul");
	}

	@Command
	public void save() {
		logger.info("save()");
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("address");
		
		List<UIAddress> addresses = (List<UIAddress>) sess.getAttribute("addresses");
		for (int i = 0; i < addresses.size(); i++) {
			if (address.getUuid().equals(addresses.get(i).getUuid())) {
				addresses.set(i, address);
				Executions.sendRedirect("/index.zul");
				return;
			}
		}
		
		addresses.add(address);
		Executions.sendRedirect("/index.zul");
	}
	
}
