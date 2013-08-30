package com.ippon.formation.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.ippon.formation.gwt.client.ui.resources.CssResources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IppContacts implements EntryPoint {

	/**
	 * Create the eventBus
	 */
	@SuppressWarnings("unused")
	private static final HandlerManager eventBus = new HandlerManager(null); 
	
	/**
	 * Create a remote service proxy to talk to the server-side Contacts service.
	 */
	@SuppressWarnings("unused")
	private final ContactsServiceAsync contactService = GWT.create(ContactsService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		CssResources.INSTANCE.myCss().ensureInjected();
		FlowPanel container = new FlowPanel();

		// When starting TP delete from here ... 
		container.setWidth("100%");
		container.setHeight("100%");
		Image logo = new Image("images/ippon_logo.png");
		logo.addStyleName("centered");
		
		FlowPanel text = new FlowPanel();
		text.setWidth("30%");
		text.getElement().setInnerHTML(new SafeHtmlBuilder().appendEscapedLines("MVP à la sauce GWT\n\nCe TP a pour but de valider la mise en place du pattern MVP sur un exemple simple\ndéjà abordé dans la présentation, une application de gestion de contacts.\n\n1: Créer les évenements EditContactCancelledEvent et EditContactEvent ainsi que leurs handlers.\n\n2: Le modèle vous est fourni dans le package fr.ippon.gwt.shared ainsi que le service ContactService.\n   - Implémentez l'AppController\n   - Implémentez le presenter et la vue pour la présentation en liste\n").toSafeHtml().asString());
		text.addStyleName("centered");
		text.addStyleName("textContainer");

		Image liste = new Image("images/liste.png");
		liste.addStyleName("centered");

		FlowPanel text2 = new FlowPanel();
		text2.setWidth("30%");
		text2.getElement().setInnerHTML(new SafeHtmlBuilder().appendEscapedLines("   - Implémentez le presenteur et la vue pour la présentation du détails et la création d'un contact.").toSafeHtml().asString());
		text2.addStyleName("centered");
		text2.addStyleName("textContainer");

		Image detail = new Image("images/detail.png");
		detail.addStyleName("centered");

		container.add(logo);
		container.add(text);
		container.add(liste);
		container.add(text2);
		container.add(detail);
		
		// ... to here
		RootPanel.get().add(container);
	}
}
