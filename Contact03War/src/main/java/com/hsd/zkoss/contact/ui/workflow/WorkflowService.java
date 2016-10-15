package com.hsd.zkoss.contact.ui.workflow;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;

import com.hsd.zkoss.contact.ui.model.bean.UIAddress;
import com.hsd.zkoss.contact.ui.model.bean.UIContact;

public class WorkflowService implements Initiator {

	private static Logger logger = Logger.getLogger(WorkflowService.class);
	
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		logger.info("doInit() Executions.getCurrent().getDesktop().getRequestPath():" + Executions.getCurrent().getDesktop().getRequestPath());
		
		Session sess = Sessions.getCurrent();
		UIContact contact = (UIContact) sess.getAttribute("contact");
		List<UIAddress> addresses = (List<UIAddress>) sess.getAttribute("addresses");
		UIAddress address = (UIAddress) sess.getAttribute("address");
		logger.info("doInit() [contact:" + contact + "] [addresses:" + addresses + "] [address:" + address + "]");
		
		if (address != null && !"/address.zul".equals(Executions.getCurrent().getDesktop().getRequestPath())) {
			Executions.sendRedirect("/address.zul");
			return;
		} else if (address == null && !"/index.zul".equals(Executions.getCurrent().getDesktop().getRequestPath())) {
			Executions.sendRedirect("/index.zul");
			return;
		}
	}

}
