package com.sogeti.rental.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalAgencyView extends ViewPart implements IPropertyChangeListener {

	public static final String ID = "com.sogeti.rental.ui.RentalAgencyView"; //$NON-NLS-1$
	private TreeViewer tv;

	public RentalAgencyView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);
		RentalProvider provider = new RentalProvider();
		tv.setContentProvider(provider);
		tv.setLabelProvider(provider);
		Collection<RentalAgency> agencies = new ArrayList<>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		tv.expandAll();
		
		getSite().setSelectionProvider(tv);
		
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(tv.getControl());
		tv.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, tv);
	}

	
	
	@Override
	public void setFocus() {
		// Set the focus
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		tv.refresh();
	}
	
	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

}
