package com.ippon.formation.gwt.client.domain.bindery;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("welcome")
public interface WelcomeRPC extends RemoteService {

    String findWelcomeMessage();
}
