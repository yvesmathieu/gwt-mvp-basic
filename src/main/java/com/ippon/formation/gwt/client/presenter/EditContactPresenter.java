package com.ippon.formation.gwt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.ippon.formation.gwt.client.ContactsServiceAsync;
import com.ippon.formation.gwt.client.event.ContactUpdatedEvent;
import com.ippon.formation.gwt.client.event.EditContactCancelledEvent;
import com.ippon.formation.gwt.shared.Contact;

/**
 * Presenter for contacts edition view.
 */
public class EditContactPresenter implements Presenter {

	boolean bound = false;

	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getFirstName();

		HasValue<String> getLastName();

		HasValue<String> getEmailAddress();

		void clear();

		Widget asWidget();
	}

	private Contact contact;
	private final ContactsServiceAsync contactsService;
	private final HandlerManager eventBus;
	private final Display display;

	public EditContactPresenter(HandlerManager eventBus,
			ContactsServiceAsync contactsService, Display display) {
		this.eventBus = eventBus;
		this.contactsService = contactsService;
		this.display = display;
	}


	public void setActiveContact(final String contactId) {
		if (contactId == null) {
			this.contact = new Contact();
			display.clear();
		} else {
			contactsService.getContact(contactId, new AsyncCallback<Contact>() {

				public void onSuccess(Contact result) {
					contact = result;
					EditContactPresenter.this.display.getFirstName().setValue(result.getFirstName());
					EditContactPresenter.this.display.getLastName().setValue(result.getLastName());
					EditContactPresenter.this.display.getEmailAddress().setValue(result.getEmailAddress());
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error retrieving contact with id : "
							+ contactId);
				}
			});
		}
	}

	public void bind() {
		if (!bound) {
			display.getCancelButton().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					eventBus.fireEvent(new EditContactCancelledEvent());
				}
			});
			display.getSaveButton().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					doSave();
				}
			});
			bound = true;
		}
	}

	protected void doSave() {
		contact.setFirstName(display.getFirstName().getValue());
		contact.setLastName(display.getLastName().getValue());
		contact.setEmailAddress(display.getEmailAddress().getValue());

		contactsService.updateContact(contact, new AsyncCallback<Contact>() {
			public void onSuccess(Contact result) {
				eventBus.fireEvent(new ContactUpdatedEvent(result));
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error updating contact");
			}
		});
	}

	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

}
