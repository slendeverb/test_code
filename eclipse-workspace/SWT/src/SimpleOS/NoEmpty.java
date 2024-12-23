package SimpleOS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NoEmpty {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NoEmpty window = new NoEmpty();
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
		shell.setSize(362,158);
		shell.setText("错误");
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
		
		Label lblNoEmpty = new Label(shell, SWT.NONE);
		lblNoEmpty.setBounds(108, 35, 120, 20);
		lblNoEmpty.setText("输入内容不能为空");
		
		Button btnNoEmpty = new Button(shell, SWT.NONE);
		btnNoEmpty.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNoEmpty.setBounds(236, 71, 98, 30);
		btnNoEmpty.setText("确定");

	}

}
