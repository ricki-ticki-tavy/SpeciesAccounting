package org.ricki.catalog.system;

import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

public class SystemServlet extends VaadinServlet {
  @Override
  protected VaadinServletService getService() {
    final VaadinServletService service = super.getService();
    // service.setSystemMessagesProvider(new ApplicationMessagesProvider());
    return service;
  }

}