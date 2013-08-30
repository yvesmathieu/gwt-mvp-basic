package com.ippon.formation.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface ContactsServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see com.ippon.formation.gwt.client.ContactsService
     */
    void addContact( com.ippon.formation.gwt.shared.Contact contact, AsyncCallback<com.ippon.formation.gwt.shared.Contact> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see com.ippon.formation.gwt.client.ContactsService
     */
    void deleteContact( java.lang.String id, AsyncCallback<java.lang.Boolean> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see com.ippon.formation.gwt.client.ContactsService
     */
    void deleteContacts( java.util.ArrayList<java.lang.String> ids, AsyncCallback<java.util.ArrayList<com.ippon.formation.gwt.shared.ContactLigth>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see com.ippon.formation.gwt.client.ContactsService
     */
    void getContactDetails( AsyncCallback<java.util.ArrayList<com.ippon.formation.gwt.shared.ContactLigth>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see com.ippon.formation.gwt.client.ContactsService
     */
    void getContact( java.lang.String id, AsyncCallback<com.ippon.formation.gwt.shared.Contact> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see com.ippon.formation.gwt.client.ContactsService
     */
    void updateContact( com.ippon.formation.gwt.shared.Contact contact, AsyncCallback<com.ippon.formation.gwt.shared.Contact> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static ContactsServiceAsync instance;

        public static final ContactsServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (ContactsServiceAsync) GWT.create( ContactsService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
