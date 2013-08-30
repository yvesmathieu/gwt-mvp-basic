package com.ippon.formation.gwt.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.ippon.formation.gwt.client.event.AddContactEvent;
import com.ippon.formation.gwt.client.event.AddContactEventHandler;
import com.ippon.formation.gwt.client.event.ContactUpdatedEvent;
import com.ippon.formation.gwt.client.event.ContactUpdatedEventHandler;
import com.ippon.formation.gwt.client.event.EditContactCancelledEvent;
import com.ippon.formation.gwt.client.event.EditContactCancelledEventHandler;
import com.ippon.formation.gwt.client.event.EditContactEvent;
import com.ippon.formation.gwt.client.event.EditContactEventHandler;
import com.ippon.formation.gwt.client.presenter.ContactsPresenter;
import com.ippon.formation.gwt.client.presenter.EditContactPresenter;
import com.ippon.formation.gwt.client.presenter.Presenter;
import com.ippon.formation.gwt.client.ui.view.ContactsView;
import com.ippon.formation.gwt.client.ui.view.EditContactView;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final ContactsServiceAsync contactService;
	private final HandlerManager eventBus;
	private HasWidgets container;
	/**
	 * Navigation history handler
	 */
	History history;

	// Can be injected or externalized in ClientFactory singleton.
	ContactsPresenter contactsPresenter;
	ContactsView contactView;
	EditContactPresenter editContactPresenter;
	EditContactView editContactView;

	public AppController(ContactsServiceAsync contactService,
			HandlerManager eventbus) {
		this.contactService = contactService;
		this.eventBus = eventbus;
		bind();
	}

	public void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(AddContactEvent.TYPE, new AddContactEventHandler() {
			public void onAddContact(AddContactEvent event) {
				doAddNewContact();
			}
		});

		eventBus.addHandler(EditContactEvent.TYPE,
				new EditContactEventHandler() {
					public void onEditContact(EditContactEvent event) {
						doEditContact(event.getId());
					}
				});

		eventBus.addHandler(EditContactCancelledEvent.TYPE,
				new EditContactCancelledEventHandler() {
					public void onEditContactCancelled(
							EditContactCancelledEvent event) {
						doEditContactCancelled();
					}
				});

		eventBus.addHandler(ContactUpdatedEvent.TYPE,
				new ContactUpdatedEventHandler() {
					public void onContactUpdated(ContactUpdatedEvent event) {
						doContactUpdated();
					}
				});
	}

	protected void doEditContact(String id) {
		// We handle redirection directly so we prevent history from firing ValueChanged event.
		History.newItem("edit", false);
		EditContactPresenter presenter = getEditContactPresenter(id);
		presenter.go(container);
	}

	private void doAddNewContact() {
		History.newItem("add");
	}

	private void doEditContactCancelled() {
		History.newItem("list");
	}

	private void doContactUpdated() {
		History.newItem("list");
	}

	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("list");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	private ContactsPresenter getContactsPresenter() {
		if (contactsPresenter == null) {
			contactView = new ContactsView();
			contactsPresenter = new ContactsPresenter(eventBus, contactService,
					contactView);
		}

		return contactsPresenter;
	}

	private EditContactPresenter getEditContactPresenter(String id) {
		if (editContactPresenter == null) {
			editContactView = new EditContactView();
			editContactPresenter = new EditContactPresenter(eventBus,
					contactService, editContactView);
		}
		editContactPresenter.setActiveContact(id);

		return editContactPresenter;
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("list")) {
				presenter = getContactsPresenter();
			} else if (token.equals("add")) {
				presenter = getEditContactPresenter(null);
			} else if (token.equals("edit")) {
				presenter = getEditContactPresenter(null);
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
