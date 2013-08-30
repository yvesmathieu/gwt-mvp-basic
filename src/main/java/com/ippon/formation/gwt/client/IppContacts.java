package com.ippon.formation.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IppContacts implements EntryPoint {

	/**
	 * Create the eventBus
	 */
	private static final HandlerManager eventBus = new HandlerManager(null); 
	
	/**
	 * Create a remote service proxy to talk to the server-side Contacts service.
	 */
	private final ContactsServiceAsync contactService = GWT
			.create(ContactsService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		AppController appViewer = new AppController(contactService, eventBus);
		appViewer.go(RootPanel.get());
	}
}
