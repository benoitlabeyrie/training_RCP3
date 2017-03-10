package com.sogeti.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.prefs.RentalPreferences;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {

	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
		} else if (element instanceof Customer) {
			return getAColorFromPrefKey(RentalPreferences.PREFS_CUSTOMERS);
		} else if (element instanceof Rental) {
			return getAColorFromPrefKey(RentalPreferences.PREFS_RENTALS);
		} else if (element instanceof RentalObject) {
			return getAColorFromPrefKey(RentalPreferences.PREFS_OBJECTS);
		} else {
			return null;
		}
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}
	
	private Color getAColorFromPrefKey(String prefKey) {
		return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(prefKey));
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

}
