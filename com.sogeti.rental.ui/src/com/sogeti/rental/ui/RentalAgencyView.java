package com.sogeti.rental.ui;

import java.util.*;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalAgencyView extends ViewPart {

	public static final String ID = "com.sogeti.rental.ui.RentalAgencyView"; //$NON-NLS-1$

	public RentalAgencyView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		TreeViewer tv = new TreeViewer(parent);
		RentalProvider provider = new RentalProvider();
		tv.setContentProvider(provider);
		tv.setLabelProvider(provider);
		Collection<RentalAgency> agencies = new ArrayList<>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		tv.expandAll();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
