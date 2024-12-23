package SimpleOS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AllocSuccess {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AllocSuccess window = new AllocSuccess();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(362, 158);
		shell.setText("提示");
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
		
		Button btnAllocSuccess = new Button(shell, SWT.NONE);
		btnAllocSuccess.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnAllocSuccess.setBounds(236, 71, 98, 30);
		btnAllocSuccess.setText("确定");
		
		Label lblAllocSuccess = new Label(shell, SWT.NONE);
		lblAllocSuccess.setBounds(123, 37, 90, 20);
		lblAllocSuccess.setText("资源分配成功");
	}

}
