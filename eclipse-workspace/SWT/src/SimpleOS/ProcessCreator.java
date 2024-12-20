package SimpleOS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ProcessCreator {

	protected Shell shell;
	private Text textProcessID;
	private Text textPriority;
	private Text textMemoryNeed;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProcessCreator window = new ProcessCreator();
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
		shell.setSize(450, 327);
		shell.setText("创建进程");
		shell.setLocation(Display.getCurrent().getClientArea().width/2-shell.getSize().x/2,
				Display.getCurrent().getClientArea().height/2-shell.getSize().y/2);
		
		Label lblProcessID = new Label(shell, SWT.NONE);
		lblProcessID.setBounds(44, 41, 92, 20);
		lblProcessID.setText("进程ID");
		lblProcessID.setAlignment(SWT.CENTER);
		
		Label lblPriority = new Label(shell, SWT.NONE);
		lblPriority.setBounds(44, 104, 92, 20);
		lblPriority.setText("优先级");
		lblPriority.setAlignment(SWT.CENTER);
		
		Label lblMemoryNeed = new Label(shell, SWT.NONE);
		lblMemoryNeed.setBounds(44, 167, 92, 20);
		lblMemoryNeed.setText("所需内存(K)");
		lblMemoryNeed.setAlignment(SWT.CENTER);
		
		textProcessID = new Text(shell, SWT.BORDER);
		textProcessID.setBounds(142, 41, 238, 26);
		
		textPriority = new Text(shell, SWT.BORDER);
		textPriority.setBounds(142, 104, 238, 26);
		
		textMemoryNeed = new Text(shell, SWT.BORDER);
		textMemoryNeed.setBounds(142, 167, 238, 26);
		
		Button btnConfirm = new Button(shell, SWT.NONE);
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
			}
		});
		btnConfirm.setBounds(80, 227, 98, 30);
		btnConfirm.setText("确定");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
		btnCancel.setBounds(250, 227, 98, 30);
		btnCancel.setText("取消");

	}
}
