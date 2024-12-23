package SimpleOS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class LargerThanNeed {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LargerThanNeed window = new LargerThanNeed();
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
		
		Button btnLargerThanNeed = new Button(shell, SWT.NONE);
		btnLargerThanNeed.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnLargerThanNeed.setBounds(236, 71, 98, 30);
		btnLargerThanNeed.setText("确定");
		
		Label lblLargerThanNeed = new Label(shell, SWT.NONE);
		lblLargerThanNeed.setBounds(65, 32, 210, 20);
		lblLargerThanNeed.setText("需要的资源超过所宣布的最大值");

	}

}
