package com.sogeti.rental.ui.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.sogeti.rental.ui.RentalUIActivator;

public class RentalDefaultPreferences extends AbstractPreferenceInitializer {

	public RentalDefaultPreferences() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(RentalPreferences.PREFS_CUSTOMERS, StringConverter.asString(new RGB(92, 120, 10)));
		store.setDefault(RentalPreferences.PREFS_RENTALS, StringConverter.asString(new RGB(80, 20, 70)));
		store.setDefault(RentalPreferences.PREFS_OBJECTS, StringConverter.asString(new RGB(40, 100, 30)));
	}

}
