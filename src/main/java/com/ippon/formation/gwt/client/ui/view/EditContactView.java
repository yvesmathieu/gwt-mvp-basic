package com.ippon.formation.gwt.client.ui.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ippon.formation.gwt.client.presenter.EditContactPresenter;

/**
 * View for contact edition.
 */
public class EditContactView extends Composite implements EditContactPresenter.Display {

	private final Button saveButton = new Button("Save");

	private final Button cancelButton = new Button("Cancel");

	private final TextBox firstName = new TextBox();

	private final TextBox lastName = new TextBox();

	private final TextBox emailAddress = new TextBox();

	public EditContactView() {
        DecoratorPanel contentDecorator = new DecoratorPanel();
	    initWidget(contentDecorator);
	    contentDecorator.setWidth("18em");
	    contentDecorator.addStyleName("mainContainer");

		FlowPanel containerPanel = new FlowPanel();
		contentDecorator.add(containerPanel);

		FlexTable formContainer = new FlexTable();

		// First name row
		formContainer.setWidget(0, 0, new Label("First name"));
		formContainer.setWidget(0, 1, firstName);
		// Last name row
		formContainer.setWidget(1, 0, new Label("Last name"));
		formContainer.setWidget(1, 1, lastName);
		// Email address row
		formContainer.setWidget(2, 0, new Label("Email address"));
		formContainer.setWidget(2, 1, emailAddress);

		containerPanel.add(formContainer);

		// Menu
		FlowPanel menuContainer = new FlowPanel();
		menuContainer.addStyleName("menuContainer");
		menuContainer.addStyleName("editMenuContainer");
		HorizontalPanel menu = new HorizontalPanel();
		menu.add(saveButton);
		menu.add(cancelButton);
		menuContainer.add(menu);

		containerPanel.add(menuContainer);
	}

	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	public HasValue<String> getFirstName() {
		return firstName;
	}

	public HasValue<String> getLastName() {
		return lastName;
	}

	public HasValue<String> getEmailAddress() {
		return emailAddress;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public void clear() {
		firstName.setText("");
		lastName.setText("");
		emailAddress.setText("");
	}

}
