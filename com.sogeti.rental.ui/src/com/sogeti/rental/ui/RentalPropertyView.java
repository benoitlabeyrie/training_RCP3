package com.sogeti.rental.ui;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	private Label rentedObjectLabel;
	private Label rentedCustomerLabel;
	private Label startDateLabel;
	private Label endDateLabel;

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		Label rentedCustomerTextLabel = new Label(infoGroup, SWT.NONE);
		GridData gd2 = new GridData();
		gd2.horizontalSpan = 1;
		gd2.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		rentedCustomerTextLabel.setText("Loué à :");
		
		rentedCustomerLabel = new Label(infoGroup, SWT.NONE);
		GridData gd3 = new GridData();
		gd3.horizontalSpan = 1;
		gd3.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		Group locationDatesGroup = new Group(parent, SWT.NONE);
		locationDatesGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		locationDatesGroup.setText("Dates de location");
		locationDatesGroup.setLayout(new GridLayout(2, false));
		
		Label startDateTextLabel = new Label(locationDatesGroup, SWT.NONE);
		startDateTextLabel.setText("du : ");
		
		startDateLabel = new Label(locationDatesGroup, SWT.NONE);
		startDateLabel.setText("New Label");
		
		Label endDateTextLabel = new Label(locationDatesGroup, SWT.NONE);
		endDateTextLabel.setText("au :");
		
		endDateLabel = new Label(locationDatesGroup, SWT.NONE);
		endDateLabel.setAlignment(SWT.CENTER);
		endDateLabel.setText("New Label");
		
	
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		rentedCustomerLabel.setText(r.getCustomer().getDisplayName());
		startDateLabel.setText(r.getStartDate().toString());
		endDateLabel.setText(r.getEndDate().toString());
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (selected instanceof Rental) {
				setRental((Rental) selected);
			}
		}
		
	}
}
