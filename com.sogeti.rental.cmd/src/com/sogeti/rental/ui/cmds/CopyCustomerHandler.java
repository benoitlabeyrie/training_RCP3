package com.sogeti.rental.ui.cmds;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;

public class CopyCustomerHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws org.eclipse.core.commands.ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
	
		System.out.println("code de copy Customer.");
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection isel = (IStructuredSelection) currentSelection;
			Object obj = isel.getFirstElement();
			if (obj instanceof Customer) {
				Clipboard clipboard = new Clipboard(Display.getCurrent());
				String textData = ((Customer) obj).getDisplayName();
				String rtfData = String.format("{\\rtf1\\b\\i %s}", textData);
				TextTransfer textTransfer = TextTransfer.getInstance();
				RTFTransfer rtfTransfer = RTFTransfer.getInstance();
				Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
				Object[] data = new Object[]{textData, rtfData};
				clipboard.setContents(data, transfers);
				clipboard.dispose();
			}
		}
		return null;
	}
}
