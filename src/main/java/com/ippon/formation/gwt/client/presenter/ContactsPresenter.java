package com.ippon.formation.gwt.client.presenter;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.ippon.formation.gwt.client.ContactsServiceAsync;
import com.ippon.formation.gwt.client.event.AddContactEvent;
import com.ippon.formation.gwt.client.event.EditContactEvent;
import com.ippon.formation.gwt.shared.ContactLigth;


/**
 * Presenter for contacts list view. 
 */
public class ContactsPresenter implements Presenter {
	boolean bound = false;
	Display display;
	ContactsServiceAsync contactsService;
	HandlerManager eventBus;
	ArrayList<ContactLigth> contactsLigth;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		void setData(ArrayList<String> data);

		int getClickedRow(ClickEvent event);

		ArrayList<Integer> getSelectedRows();

		Widget asWidget();
	}

	public ContactsPresenter(HandlerManager eventBus,
			ContactsServiceAsync contactsService, Display display) {
		this.display = display;
		this.contactsService = contactsService;
		this.eventBus = eventBus;
	}

	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchContacts();
	}

	public void bind() {
		if (!bound) {
			display.getAddButton().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					eventBus.fireEvent(new AddContactEvent());
				}
			});

			display.getDeleteButton().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					deleteSelectedContacts();
				}
			});

			display.getList().addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					int selectedRow = display.getClickedRow(event);

					if (selectedRow >= 0) {
						String id = contactsLigth.get(selectedRow).getId();
						eventBus.fireEvent(new EditContactEvent(id));
					}
				}
			});

			bound = true;
		}
	}

	private void feedContactsList(ArrayList<ContactLigth> result) {
		contactsLigth = result;
		ArrayList<String> data = new ArrayList<String>();

		for (ContactLigth contact : result) {
			data.add(contact.getDisplayName());
		}

		display.setData(data);
	}
	
	protected void deleteSelectedContacts() {
		ArrayList<Integer> selectedRows = display.getSelectedRows();

		ArrayList<String> selectedIds = new ArrayList<String>();
		
		for (Integer rowNum : selectedRows ) {
			selectedIds.add(contactsLigth.get(rowNum).getId());
		}
		
		contactsService.deleteContacts(selectedIds,
				new AsyncCallback<ArrayList<ContactLigth>>() {
					public void onFailure(Throwable caught) {
						Window.alert("Failed to delete selected contacts");
					}

					public void onSuccess(ArrayList<ContactLigth> result) {
						feedContactsList(result);
					}

				});
	}

	protected void fetchContacts() {
		contactsService
				.getContactDetails(new AsyncCallback<ArrayList<ContactLigth>>() {
					public void onFailure(Throwable caught) {
						Window.alert("Failed to retrieve contacts.");
					}

					public void onSuccess(ArrayList<ContactLigth> result) {
						feedContactsList(result);
					}
				});
	}

}
