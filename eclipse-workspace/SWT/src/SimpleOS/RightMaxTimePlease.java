package SimpleOS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RightMaxTimePlease {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RightMaxTimePlease window = new RightMaxTimePlease();
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
		shell.setText("错误");
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
		
		Label lblRightMaxTimePlease = new Label(shell, SWT.NONE);
		lblRightMaxTimePlease.setBounds(77, 33, 180, 20);
		lblRightMaxTimePlease.setText("请输入符合要求的结束时间");
		
		Button btnRightMaxTimePlease = new Button(shell, SWT.NONE);
		btnRightMaxTimePlease.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnRightMaxTimePlease.setBounds(236, 71, 98, 30);
		btnRightMaxTimePlease.setText("确定");

	}
}
