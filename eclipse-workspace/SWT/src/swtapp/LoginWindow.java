package swtapp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginWindow {

	protected Shell shellLogin;
	private Text textUsernameInput;
	private Text textPasswordInput;
	private Label lblInfo;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginWindow loginWindow = new LoginWindow();
			loginWindow.open();
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
		shellLogin.open();
		shellLogin.layout();
		while (!shellLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellLogin = new Shell();
		shellLogin.setSize(450, 300);
		shellLogin.setText("学生管理系统");

		Label lblUsername = new Label(shellLogin, SWT.NONE);
		lblUsername.setBounds(81, 55, 53, 20);
		lblUsername.setText("用户名: ");

		Label lblPassword = new Label(shellLogin, SWT.NONE);
		lblPassword.setBounds(81, 115, 53, 20);
		lblPassword.setText("密码: ");

		textUsernameInput = new Text(shellLogin, SWT.BORDER);
		textUsernameInput.setBounds(154, 52, 182, 26);

		textPasswordInput = new Text(shellLogin, SWT.BORDER);
		textPasswordInput.setBounds(154, 112, 182, 26);

		Button btnLogin = new Button(shellLogin, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String username = textUsernameInput.getText();
				String password = textPasswordInput.getText();
				if (username.equalsIgnoreCase("test") && password.equals("123")) {
					lblInfo.setText("登陆成功!");
					shellLogin.dispose();
					MainWindow mainWindow = new MainWindow();
					mainWindow.open();
				} else {
					lblInfo.setText("用户名或密码不正确，请重新输入!");
					textUsernameInput.setText("");
					textPasswordInput.setText("");
					textUsernameInput.setFocus();
				}
			}
		});
		btnLogin.setBounds(81, 171, 98, 30);
		btnLogin.setText("登录");

		Button btnRegister = new Button(shellLogin, SWT.NONE);
		btnRegister.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shellLogin.dispose();
				RegisterWindow registerWindow = new RegisterWindow();
				registerWindow.open();
			}
		});
		btnRegister.setBounds(238, 171, 98, 30);
		btnRegister.setText("注册");

		lblInfo = new Label(shellLogin, SWT.NONE);
		lblInfo.setBounds(10, 10, 312, 20);

	}
}
