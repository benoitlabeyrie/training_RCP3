package com.sogeti.rental.ui;

import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			return ((Collection<?>) inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			
			RentalAgency agency = (RentalAgency) parentElement;
			
			return new Node[] {
					new Node(Node.CUSTOMERS, agency),
					new Node(Node.RENTALS, agency),
					new Node(Node.OBJECTS, agency),
			};
		} else if (parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof RentalAgency) {
			return true;
		}
		else if (element instanceof Node) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		} else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		return super.getText(element);
	}
	
	class Node {
		private static final String OBJECTS = "Objets à louer";
		private static final String RENTALS = "Locations";
		private static final String CUSTOMERS = "Customers";
		private String label;
		private RentalAgency agency;
		
		public Node(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}
		
		
		Object[] getChildren() {
			if (label == CUSTOMERS) {
				return agency.getCustomers().toArray();
			} else if (label == RENTALS) {
				return agency.getRentals().toArray();
			} else if (label == OBJECTS) {
				return agency.getObjectsToRent().toArray();
			}
			return null;
		}
		
		@Override
		public String toString() {
			return label;
		}
		
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		} else if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		} else if (element instanceof Rental) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		} else if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
		} else {
			return null;
		}
		
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
