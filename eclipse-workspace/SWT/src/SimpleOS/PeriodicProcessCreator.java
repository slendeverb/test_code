package SimpleOS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import SimpleOS.Simulation.OS;
import SimpleOS.Simulation.Process.PeriodicProcess;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PeriodicProcessCreator {
	public OS os;
	protected Shell shell;
	private Text textProcessName;
	private Text textCycle;
	private Text textCPUTimeNeed;
	
	public PeriodicProcessCreator(OS os) {
		this.os=os;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PeriodicProcessCreator window = new PeriodicProcessCreator(OS.getInstance());
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
		shell.setText("创建周期进程");
		shell.setLocation(Display.getCurrent().getClientArea().width/2-shell.getSize().x/2,
				Display.getCurrent().getClientArea().height/2-shell.getSize().y/2);
		
		Label lblProcessName = new Label(shell, SWT.NONE);
		lblProcessName.setBounds(44, 41, 92, 20);
		lblProcessName.setText("进程名称");
		lblProcessName.setAlignment(SWT.CENTER);
		
		Label lblCPUTimeNeed = new Label(shell, SWT.NONE);
		lblCPUTimeNeed.setBounds(44, 170, 92, 20);
		lblCPUTimeNeed.setText("所需CPU时间");
		lblCPUTimeNeed.setAlignment(SWT.CENTER);
		
		Label lblCycle = new Label(shell, SWT.NONE);
		lblCycle.setBounds(44, 107, 92, 20);
		lblCycle.setText("周期");
		lblCycle.setAlignment(SWT.CENTER);
		
		textProcessName = new Text(shell, SWT.BORDER);
		textProcessName.setBounds(142, 41, 238, 26);
		
		textCycle = new Text(shell, SWT.BORDER);
		textCycle.setBounds(142, 104, 238, 26);
		
		textCPUTimeNeed = new Text(shell, SWT.BORDER);
		textCPUTimeNeed.setBounds(142, 167, 238, 26);
		
		Button btnConfirm = new Button(shell, SWT.NONE);
		btnConfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String name=textProcessName.getText();
					Integer cycle=Integer.valueOf(textCycle.getText());
					Integer task=Integer.valueOf(textCPUTimeNeed.getText());
					PeriodicProcess periodicProcess=new PeriodicProcess(os.processID++, name, 0, Integer.MAX_VALUE, task, 0, null, cycle, os);
					os.periodicProcesses.add(periodicProcess);
					shell.dispose();
				} catch (Exception e2) {
					RightValuePlease rightValuePlease=new RightValuePlease();
					rightValuePlease.open();
				}
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
