package com.ippon.formation.gwt.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ippon.formation.gwt.shared.Contact;
import com.ippon.formation.gwt.shared.ContactLigth;


/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("contacts")
public interface ContactsService extends RemoteService {

	Contact addContact(Contact contact);

	Boolean deleteContact(String id);

	ArrayList<ContactLigth> deleteContacts(ArrayList<String> ids);

	ArrayList<ContactLigth> getContactDetails();

	Contact getContact(String id);

	Contact updateContact(Contact contact);

}
