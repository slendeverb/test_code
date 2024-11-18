package swtapp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class RegisterWindow {

	protected Shell shellRegister;
	private Text textUsernameInput;
	private Text textPasswordInput;
	private Label lblInfo;
	private Text textRepeatPasswordInput;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegisterWindow registerWindow = new RegisterWindow();
			registerWindow.open();
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
		shellRegister.open();
		shellRegister.layout();
		while (!shellRegister.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellRegister = new Shell();
		shellRegister.setSize(450, 300);
		shellRegister.setText("学生管理系统");

		Label lblUsername = new Label(shellRegister, SWT.NONE);
		lblUsername.setBounds(81, 55, 53, 20);
		lblUsername.setText("用户名: ");

		Label lblPassword = new Label(shellRegister, SWT.NONE);
		lblPassword.setBounds(81, 99, 53, 20);
		lblPassword.setText("密码: ");

		textUsernameInput = new Text(shellRegister, SWT.BORDER);
		textUsernameInput.setBounds(154, 52, 182, 26);

		textPasswordInput = new Text(shellRegister, SWT.BORDER);
		textPasswordInput.setBounds(154, 96, 182, 26);

		Button btnRegister = new Button(shellRegister, SWT.NONE);
		btnRegister.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				String username = textUsernameInput.getText();
				String password = textPasswordInput.getText();
				String repeatPassword = textRepeatPasswordInput.getText();
				if (!username.isEmpty() && !password.isEmpty() && password.equals(repeatPassword)) {
					final Color textColor = new Color(0, 0, 0);
					lblInfo.setForeground(textColor);
					lblInfo.setText("注册成功!");
					try {
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					shellRegister.dispose();
					LoginWindow loginWindow = new LoginWindow();
					loginWindow.open();
				} else if (username.isEmpty() || password.isEmpty()) {
					final Color textColor = new Color(255, 0, 0);
					lblInfo.setForeground(textColor);
					lblInfo.setText("用户名或密码不能为空!");
					textUsernameInput.setText("");
					textPasswordInput.setText("");
					textRepeatPasswordInput.setText("");
					textUsernameInput.setFocus();
				} else if (!password.equals(repeatPassword)) {
					final Color textColor = new Color(255, 0, 0);
					lblInfo.setForeground(textColor);
					lblInfo.setText("两个密码不一致!");
					textRepeatPasswordInput.setText("");
					textRepeatPasswordInput.setFocus();
				}
			}
		});
		btnRegister.setBounds(167, 194, 98, 30);
		btnRegister.setText("注册");

		lblInfo = new Label(shellRegister, SWT.NONE);
		lblInfo.setBounds(10, 10, 312, 20);

		Label lblRepeatPassword = new Label(shellRegister, SWT.NONE);
		lblRepeatPassword.setBounds(81, 141, 68, 20);
		lblRepeatPassword.setText("重复密码: ");

		textRepeatPasswordInput = new Text(shellRegister, SWT.BORDER);
		textRepeatPasswordInput.setBounds(154, 138, 182, 26);

	}

}
