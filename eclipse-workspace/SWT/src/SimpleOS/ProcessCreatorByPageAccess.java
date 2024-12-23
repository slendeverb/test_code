package SimpleOS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ProcessCreatorByPageAccess {

	protected Shell shell;
	private Text textProcessCreatorByPageAccess;
	public StringBuffer pageAccessSequence;
	
	public ProcessCreatorByPageAccess(StringBuffer pageAccessSequence) {
		this.pageAccessSequence=pageAccessSequence;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProcessCreatorByPageAccess window = new ProcessCreatorByPageAccess(new StringBuffer());
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
		shell.setSize(453, 257);
		shell.setText("创建进程");
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);
		
		Label lblProcessCreatorByPageAccess = new Label(shell, SWT.NONE);
		lblProcessCreatorByPageAccess.setBounds(50, 41, 270, 20);
		lblProcessCreatorByPageAccess.setText("请输入页面访问序列（使用空格分隔）：");
		
		textProcessCreatorByPageAccess = new Text(shell, SWT.BORDER);
		textProcessCreatorByPageAccess.setBounds(86, 94, 270, 26);
		
		Button btnConfirm = new Button(shell, SWT.NONE);
		btnConfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String text=textProcessCreatorByPageAccess.getText();
					if(text.isBlank()) {
						throw new Exception("Empty Input.");
					}
					pageAccessSequence.append(text);
					shell.dispose();
				} catch (Exception e2) {
					NoEmpty noEmpty=new NoEmpty();
					noEmpty.open();
				}
			}
		});
		btnConfirm.setBounds(72, 157, 98, 30);
		btnConfirm.setText("确定");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnCancel.setBounds(271, 157, 98, 30);
		btnCancel.setText("取消");
	}

}
