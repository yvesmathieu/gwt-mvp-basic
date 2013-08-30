package com.ippon.formation.gwt.client.ui.view;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ippon.formation.gwt.client.presenter.ContactsPresenter;

/**
 * View for contacts list.
 */
public class ContactsView extends Composite implements ContactsPresenter.Display {

	private static final int CHECKBOX_COLUMN = 0;
	private static final int DISPLAY_NAME_COLUMN = 1;

	private final Button addButton = new Button("Add");
	private final Button deleteButton = new Button("Delete");
	private final FlexTable contactsTable = new FlexTable();
	private final FlowPanel content;

	public ContactsView() {
        DecoratorPanel contentDecorator = new DecoratorPanel();
	    initWidget(contentDecorator);
	    contentDecorator.setWidth("18em");
	    contentDecorator.addStyleName("mainContainer");

		content = new FlowPanel();
		contentDecorator.add(content);

		// Menu
		FlowPanel menuContainer = new FlowPanel();
		menuContainer.addStyleName("menuContainer");
		menuContainer.addStyleName("contactsMenuContainer");
		HorizontalPanel menu = new HorizontalPanel();
		menu.setBorderWidth(0);
		menu.setSpacing(0);
		menu.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		menu.add(addButton);
		menu.add(deleteButton);
		menuContainer.add(menu);
		content.add(menuContainer);

		contactsTable.addStyleName("contactsListContainer");
		content.add(contactsTable);
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	public HasClickHandlers getList() {
		return contactsTable;
	}

	public void setData(ArrayList<String> data) {
		contactsTable.removeAllRows();

		for(int i = 0; i < data.size(); ++i) {
			contactsTable.setWidget(i, CHECKBOX_COLUMN, new CheckBox());
			contactsTable.setText(i, DISPLAY_NAME_COLUMN, data.get(i));
		}
	}

	public int getClickedRow(ClickEvent event) {
		int clickedRaw = -1;
		Cell clickedCell = contactsTable.getCellForEvent(event);

		if (clickedCell != null
				&& clickedCell.getCellIndex() > CHECKBOX_COLUMN) {
			clickedRaw = clickedCell.getRowIndex();
		}

		return clickedRaw;
	}

	public ArrayList<Integer> getSelectedRows() {
		ArrayList<Integer> selectedRaws = new ArrayList<Integer>();

		for (int i = 0; i < contactsTable.getRowCount(); ++i) {
			if (((CheckBox)contactsTable.getWidget(i, CHECKBOX_COLUMN)).getValue()) {
				selectedRaws.add(new Integer(i));
			}
		}

		return selectedRaws;
	}

	@Override
	public Widget asWidget() {
		return this;
	}
}
