package com.sogeti.rental.ui.prefs;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.RentalUIActivator;

public class RentalPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String PREFS_OBJECTS = "PREFS_OBJECTS";
	public static final String PREFS_RENTALS = "PREFS_RENTALS";
	public static final String PREFS_CUSTOMERS = "PREFS_CUSTOMERS";

	/**
	 * Create the preference page.
	 */
	public RentalPreferences() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("a preferences page for selecting colors");
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(IWorkbench workbench) {
		// Initialize the preference page
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREFS_CUSTOMERS, "Customers", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREFS_RENTALS, "Rentals", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREFS_OBJECTS, "Objects", getFieldEditorParent()));
	}

}
