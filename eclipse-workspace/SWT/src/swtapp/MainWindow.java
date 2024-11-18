package swtapp;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWindow {

    protected Shell shellMain;

    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            MainWindow mainWindow = new MainWindow();
            mainWindow.open();
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
        shellMain.open();
        shellMain.layout();
        while (!shellMain.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shellMain = new Shell();
        shellMain.setSize(932, 532);
        shellMain.setText("学生管理系统");

        Menu menuAll = new Menu(shellMain, SWT.BAR);
        shellMain.setMenuBar(menuAll);

        MenuItem menuItemUserManagement = new MenuItem(menuAll, SWT.CASCADE);
        menuItemUserManagement.setText("用户管理");

        Menu menuUserManagement = new Menu(menuItemUserManagement);
        menuItemUserManagement.setMenu(menuUserManagement);

        MenuItem menuItem = new MenuItem(menuUserManagement, SWT.NONE);
        menuItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shellMain.dispose();
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.open();
            }
        });
        menuItem.setText("退出");

    }
}
