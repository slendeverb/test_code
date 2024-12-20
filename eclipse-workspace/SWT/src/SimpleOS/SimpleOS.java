package SimpleOS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import SimpleOS.Simulation.OS;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StyledText;

public class SimpleOS {
	protected OS os=OS.getInstance();

	protected Shell shell;
	private Table tableProcess;
	private TableColumn tblclmnProcessID;
	private TableColumn tblclmnPriority;
	private TableColumn tblclmnMemoryNeed;
	private TableColumn tblclmnProcessStatus;
	private Table tableReady;
	private Label lblBlockQueue;
	private Table tableBlock;
	private Table tablePageTable;
	private Label lblMemory;
	private Label lblStorage;
	private TableColumn tblclmnPageNumber;
	private TableColumn tblclmnFrameNumber;
	private StyledText styledTextLog;
	private Table tableSuspend;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimpleOS window = new SimpleOS();
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
		shell.setSize(1800, 993);
		shell.setText("操作系统模拟系统");
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getSize().x / 2,
				Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);

		Label lblReadyQueue = new Label(shell, SWT.NONE);
		lblReadyQueue.setBounds(10, 295, 76, 20);
		lblReadyQueue.setText("就绪队列");

		tableProcess = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableProcess.setBounds(10, 73, 410, 196);
		tableProcess.setHeaderVisible(true);
		tableProcess.setLinesVisible(true);

		tblclmnProcessID = new TableColumn(tableProcess, SWT.NONE);
		tblclmnProcessID.setWidth(100);
		tblclmnProcessID.setText("进程ID");

		tblclmnPriority = new TableColumn(tableProcess, SWT.NONE);
		tblclmnPriority.setWidth(100);
		tblclmnPriority.setText("优先级");

		tblclmnMemoryNeed = new TableColumn(tableProcess, SWT.NONE);
		tblclmnMemoryNeed.setWidth(100);
		tblclmnMemoryNeed.setText("所需内存(K)");

		tblclmnProcessStatus = new TableColumn(tableProcess, SWT.NONE);
		tblclmnProcessStatus.setWidth(100);
		tblclmnProcessStatus.setText("进程状态");

		Button btnCreateProcess = new Button(shell, SWT.NONE);
		btnCreateProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				ProcessCreator createProcessShell = new ProcessCreator();
				createProcessShell.open();
			}
		});
		btnCreateProcess.setBounds(127, 23, 98, 30);
		btnCreateProcess.setText("创建进程");

		tableReady = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableReady.setBounds(10, 321, 110, 196);
		tableReady.setHeaderVisible(true);
		tableReady.setLinesVisible(true);

		TableColumn tblclmnReadyProcessID = new TableColumn(tableReady, SWT.NONE);
		tblclmnReadyProcessID.setWidth(100);
		tblclmnReadyProcessID.setText("进程ID");

		lblBlockQueue = new Label(shell, SWT.NONE);
		lblBlockQueue.setText("阻塞队列");
		lblBlockQueue.setBounds(140, 295, 76, 20);

		tableBlock = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableBlock.setBounds(140, 321, 110, 196);
		tableBlock.setHeaderVisible(true);
		tableBlock.setLinesVisible(true);

		TableColumn tblclmnBlockProcessID = new TableColumn(tableBlock, SWT.NONE);
		tblclmnBlockProcessID.setWidth(100);
		tblclmnBlockProcessID.setText("进程ID");

		Label lblLog = new Label(shell, SWT.NONE);
		lblLog.setBounds(10, 544, 76, 20);
		lblLog.setText("运行日志");

		Button btnStart = new Button(shell, SWT.NONE);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String newtext=os.start();
				styledTextLog.setCaretOffset(styledTextLog.getText().length());
				styledTextLog.insert(newtext+"\n");
				styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);
			}
		});
		btnStart.setBounds(10, 23, 98, 30);
		btnStart.setText("启动");

		Button btnRun = new Button(shell, SWT.NONE);
		btnRun.setBounds(245, 23, 98, 30);
		btnRun.setText("开始运行");

		tablePageTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tablePageTable.setBounds(526, 321, 442, 243);
		tablePageTable.setHeaderVisible(true);
		tablePageTable.setLinesVisible(true);

		tblclmnPageNumber = new TableColumn(tablePageTable, SWT.NONE);
		tblclmnPageNumber.setWidth(100);
		tblclmnPageNumber.setText("虚拟页号");

		tblclmnFrameNumber = new TableColumn(tablePageTable, SWT.NONE);
		tblclmnFrameNumber.setWidth(100);
		tblclmnFrameNumber.setText("内存框号");

		Label lblPageTable = new Label(shell, SWT.NONE);
		lblPageTable.setBounds(526, 295, 76, 20);
		lblPageTable.setText("页表");

		lblMemory = new Label(shell, SWT.NONE);
		lblMemory.setBounds(1216, 58, 76, 20);
		lblMemory.setText("内存");

		lblStorage = new Label(shell, SWT.NONE);
		lblStorage.setBounds(1216, 371, 76, 20);
		lblStorage.setText("外存");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmSystem = new MenuItem(menu, SWT.CASCADE);
		mntmSystem.setText("系统");
		
		Menu menuSystem = new Menu(mntmSystem);
		mntmSystem.setMenu(menuSystem);
		
		MenuItem mntmStart = new MenuItem(menuSystem, SWT.NONE);
		mntmStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String newtext=os.start();
				styledTextLog.setCaretOffset(styledTextLog.getText().length());
				styledTextLog.insert(newtext+"\n");
				styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);
			}
		});
		mntmStart.setText("启动");
		
		MenuItem mntmStop = new MenuItem(menuSystem, SWT.NONE);
		mntmStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String newtext=os.stop();
				styledTextLog.setCaretOffset(styledTextLog.getText().length());
				styledTextLog.insert(newtext+"\n");
				styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);
			}
		});
		mntmStop.setText("停止");
		
		MenuItem mntmProcess = new MenuItem(menu, SWT.CASCADE);
		mntmProcess.setText("进程");
		
		Menu menuProcess = new Menu(mntmProcess);
		mntmProcess.setMenu(menuProcess);
		
		MenuItem mntmAddProcess = new MenuItem(menuProcess, SWT.NONE);
		mntmAddProcess.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProcessCreator createProcessShell = new ProcessCreator();
				createProcessShell.open();
			}
		});
		mntmAddProcess.setText("添加进程");
		
		MenuItem mntmPresetProcess = new MenuItem(menuProcess, SWT.NONE);
		mntmPresetProcess.setText("预设进程");
		
		MenuItem mntmSetting = new MenuItem(menu, SWT.CASCADE);
		mntmSetting.setText("设置");
		
		Menu menuSetting = new Menu(mntmSetting);
		mntmSetting.setMenu(menuSetting);
		
		styledTextLog = new StyledText(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		styledTextLog.setBounds(10, 570, 442, 328);
		
		Label lblSuspend = new Label(shell, SWT.NONE);
		lblSuspend.setBounds(278, 295, 76, 20);
		lblSuspend.setText("挂起队列");
		
		tableSuspend = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableSuspend.setBounds(278, 321, 110, 196);
		tableSuspend.setHeaderVisible(true);
		tableSuspend.setLinesVisible(true);
		
		TableColumn tblclmnSuspendProcessID = new TableColumn(tableSuspend, SWT.NONE);
		tblclmnSuspendProcessID.setWidth(100);
		tblclmnSuspendProcessID.setText("进程ID");

	}
}
