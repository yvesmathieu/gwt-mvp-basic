package com.ippon.formation.gwt.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Interface for all presenters.
 */
public interface Presenter {

	/**
	 * Switch to this presenter's view.
	 * 
	 * @param container : container where the view will be injected.
	 */
	public abstract void go(final HasWidgets container);
	
	/**
	 * Bind the controller with his view.
	 */
	public abstract void bind();
	
}
