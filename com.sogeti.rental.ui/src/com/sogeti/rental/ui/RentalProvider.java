package com.sogeti.rental.ui;

import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {

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
		
		Image getImage() {
			if (label == CUSTOMERS) {
				return RentalUIActivator.getDefault().getImageRegistry().get(IMG_CUSTOMER);
			} else if (label == RENTALS) {
				return RentalUIActivator.getDefault().getImageRegistry().get(IMG_RENTAL);
			} else if (label == OBJECTS) {
				return RentalUIActivator.getDefault().getImageRegistry().get(IMG_OBJECT);
			}
			return null;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}


		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
		
		
	}

	@Override
	public Color getForeground(Object element) {
		String paletteId = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
		PaletteDescriptor p = RentalUIActivator.getDefault().getPaletteManager().get(paletteId);
		return p.getProvider().getForeground(element);
		
	}

	@Override
	public Color getBackground(Object element) {
		String paletteId = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
		PaletteDescriptor p = RentalUIActivator.getDefault().getPaletteManager().get(paletteId);
		return p.getProvider().getBackground(element);
	}
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_AGENCY);
		} else if (element instanceof Customer) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_CUSTOMER);
		} else if (element instanceof Rental) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_RENTAL);
		} else if (element instanceof RentalObject) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_OBJECT);
		} else if (element instanceof Node) {
			return ((Node) element).getImage();
		} else {
			return null;
		}
	}
	
	private Color getAColor(String rgbKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(rgbKey);
		if (col == null) {
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}
	
	private Color getAColorFromPrefKey(String prefKey) {
		return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(prefKey));
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
