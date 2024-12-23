package SimpleOS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import SimpleOS.Simulation.OS;
import SimpleOS.Simulation.Memory.Node;
import SimpleOS.Simulation.Memory.Page;
import SimpleOS.Simulation.Memory.PageTable;
import SimpleOS.Simulation.Process.Process;
import SimpleOS.Simulation.Process.State;
import SimpleOS.Simulation.Process.Thread;
import SimpleOS.Simulation.Register.ReplaceStrategy;
import SimpleOS.Simulation.Source.Source;
import SimpleOS.Simulation.Process.DeadlineProcessComparator;
import SimpleOS.Simulation.Process.LaxityProcessComparator;
import SimpleOS.Simulation.Process.PCB;
import SimpleOS.Simulation.Process.PeriodicProcess;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public class SimpleOS {
    public OS os=OS.getInstance();
    protected Shell shell;
    private Text textSystemTime;
    private Table tableProcessSchedulingProcess;
    private Table tableProcessSchedulingCurrentProcess;
    private Table tableProcessSchedulingReadyQueue;
    private Table tableAllocationStatus;
    private Table tableSafetyCheck;
    private Table tableMemoryAllocationProcess;
    private Table tableMemoryAllocationCurrentProcess;
    private Text textMaxTime;
    private Table tableProcessSchedulingCompleteQueue;
    public String[] states=new String[] {"创建","活动就绪","静止就绪","执行","活动阻塞","静止阻塞","终止"};
    private Text textResourceA;
    private Text textResourceB;
    private Text textResourceC;
    private Text textRequestA;
    private Text textRequestB;
    private Text textRequestC;
    private Table tableMemory;
    private Table tableStorage;
    private Table tableTLB;
    private Table tableReplacement;
    private Table tableMessageQueue;
    private Table tableReadyQueue;
    private Table tableBlockQueueEmpty;
    private Table tableBlockQueueFull;

    /**
     * Launch the application.
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
        shell.setSize(1280,800);
        shell.setText("TestForm");
        shell.setLayout(new FillLayout(SWT.HORIZONTAL));
        shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getSize().x / 2,
                Display.getCurrent().getClientArea().height / 2 - shell.getSize().y / 2);

        TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

        TabItem tbtmProcessScheduling = new TabItem(tabFolder, SWT.NONE);
        tbtmProcessScheduling.setText("进程调度");

        Composite compositeProcessScheduling = new Composite(tabFolder, SWT.NONE);
        tbtmProcessScheduling.setControl(compositeProcessScheduling);

        tableProcessSchedulingCurrentProcess = new Table(compositeProcessScheduling, SWT.BORDER | SWT.FULL_SELECTION);
        tableProcessSchedulingCurrentProcess.setBounds(508, 39, 115, 120);
        tableProcessSchedulingCurrentProcess.setHeaderVisible(true);
        tableProcessSchedulingCurrentProcess.setLinesVisible(true);

        TableColumn tblclmnProcessSchedulingCurrentProcessID = new TableColumn(tableProcessSchedulingCurrentProcess, SWT.NONE);
        tblclmnProcessSchedulingCurrentProcessID.setWidth(100);
        tblclmnProcessSchedulingCurrentProcessID.setText("ID");

        Label lblSystemTime = new Label(compositeProcessScheduling, SWT.NONE);
        lblSystemTime.setBounds(1017, 16, 76, 20);
        lblSystemTime.setText("系统时间：");

        Label lblProcessSchedulingCurrentProcess = new Label(compositeProcessScheduling, SWT.NONE);
        lblProcessSchedulingCurrentProcess.setBounds(508, 16, 105, 20);
        lblProcessSchedulingCurrentProcess.setText("当前运行：");

        textSystemTime= new Text(compositeProcessScheduling, SWT.BORDER | SWT.READ_ONLY | SWT.RIGHT);
        textSystemTime.setBounds(1099, 13, 73, 26);
        textSystemTime.setText(String.valueOf(os.systemTime));

        tableProcessSchedulingProcess = new Table(compositeProcessScheduling, SWT.BORDER | SWT.FULL_SELECTION);
        tableProcessSchedulingProcess.setBounds(26, 39, 445, 249);
        tableProcessSchedulingProcess.setHeaderVisible(true);
        tableProcessSchedulingProcess.setLinesVisible(true);

        TableColumn tblclmnProcessSchedulingProcessID = new TableColumn(tableProcessSchedulingProcess, SWT.NONE);
        tblclmnProcessSchedulingProcessID.setWidth(70);
        tblclmnProcessSchedulingProcessID.setText("ID");

        TableColumn tblclmnProcessName = new TableColumn(tableProcessSchedulingProcess, SWT.NONE);
        tblclmnProcessName.setWidth(75);
        tblclmnProcessName.setText("名称");

        TableColumn tblclmnCPUTimeNeed = new TableColumn(tableProcessSchedulingProcess, SWT.NONE);
        tblclmnCPUTimeNeed.setWidth(75);
        tblclmnCPUTimeNeed.setText("CPU时间");

        TableColumn tblclmnDeadline = new TableColumn(tableProcessSchedulingProcess, SWT.NONE);
        tblclmnDeadline.setWidth(120);
        tblclmnDeadline.setText("截止时间");

        TableColumn tblclmnCycle = new TableColumn(tableProcessSchedulingProcess, SWT.NONE);
        tblclmnCycle.setWidth(80);
        tblclmnCycle.setText("周期");

        Label lblProcessSchedulingProcessOrThread = new Label(compositeProcessScheduling, SWT.NONE);
        lblProcessSchedulingProcessOrThread.setBounds(26, 16, 76, 20);
        lblProcessSchedulingProcessOrThread.setText("进程/线程：");

        Label lblProcessSchedulingReadyQueue = new Label(compositeProcessScheduling, SWT.NONE);
        lblProcessSchedulingReadyQueue.setBounds(670, 39, 76, 20);
        lblProcessSchedulingReadyQueue.setText("就绪队列：");

        tableProcessSchedulingReadyQueue = new Table(compositeProcessScheduling, SWT.BORDER | SWT.FULL_SELECTION);
        tableProcessSchedulingReadyQueue.setBounds(670, 65, 281, 534);
        tableProcessSchedulingReadyQueue.setHeaderVisible(true);
        tableProcessSchedulingReadyQueue.setLinesVisible(true);

        TableColumn tblclmnProcessSchedulingReadyQueueProcessID = new TableColumn(tableProcessSchedulingReadyQueue, SWT.NONE);
        tblclmnProcessSchedulingReadyQueueProcessID.setWidth(70);
        tblclmnProcessSchedulingReadyQueueProcessID.setText("ID");

        TableColumn tblclmnLeftCPUTime = new TableColumn(tableProcessSchedulingReadyQueue, SWT.NONE);
        tblclmnLeftCPUTime.setWidth(105);
        tblclmnLeftCPUTime.setText("剩余CPU时间");

        TableColumn tblclmnLaxity = new TableColumn(tableProcessSchedulingReadyQueue, SWT.NONE);
        tblclmnLaxity.setWidth(80);
        tblclmnLaxity.setText("松弛度");

        Label lblLog = new Label(compositeProcessScheduling, SWT.NONE);
        lblLog.setBounds(26, 304, 76, 20);
        lblLog.setText("运行日志：");

        StyledText styledTextLog = new StyledText(compositeProcessScheduling, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);
        styledTextLog.setBounds(26, 330, 445, 269);

        Combo comboSchedulingStrategy = new Combo(compositeProcessScheduling, SWT.READ_ONLY);
        comboSchedulingStrategy.setBounds(1099, 120, 92, 28);
        String[] processSchedulingStrategy=new String[] {"EDF","LLF"};
        for(String strategy:processSchedulingStrategy) {
            comboSchedulingStrategy.add(strategy);
        }
        comboSchedulingStrategy.select(0);

        Label lblSchedulingStrategy = new Label(compositeProcessScheduling, SWT.NONE);
        lblSchedulingStrategy.setBounds(1017, 123, 76, 20);
        lblSchedulingStrategy.setText("调度算法：");

        Label lblSystemTimeMillisecond = new Label(compositeProcessScheduling, SWT.NONE);
        lblSystemTimeMillisecond.setText("ms");
        lblSystemTimeMillisecond.setBounds(1178, 16, 39, 20);

        ArrayList<Integer> numberX=new ArrayList<Integer>();
        AtomicInteger maxTime=new AtomicInteger();
        AtomicInteger currentStep=new AtomicInteger();
        AtomicInteger t=new AtomicInteger();
        AtomicInteger forwardTime=new AtomicInteger();

        Button btnProcessSchedulingStart = new Button(compositeProcessScheduling, SWT.NONE);
        btnProcessSchedulingStart.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textSystemTime.setText(String.valueOf(os.systemTime));
                ArrayList<PeriodicProcess> periodicProcesses=os.periodicProcesses;
                String strategy=comboSchedulingStrategy.getText();
                if(strategy.equals(processSchedulingStrategy[0])) {
                    os.readyQueueA=new PriorityQueue<Process>(new DeadlineProcessComparator());
                }else if(strategy.equals(processSchedulingStrategy[1])) {
                    os.readyQueueA=new PriorityQueue<Process>(new LaxityProcessComparator());
                }
                for(int i=0;i<periodicProcesses.size();i++) {
                    numberX.add(1);
                }
                try {
                    maxTime.set(Integer.valueOf(textMaxTime.getText()));
                } catch (Exception e2) {
                    RightMaxTimePlease rightMaxTimePlease=new RightMaxTimePlease();
                    rightMaxTimePlease.open();
                }
            }
        });
        btnProcessSchedulingStart.setBounds(1017, 219, 98, 30);
        btnProcessSchedulingStart.setText("开始");

        Button btnProcessSchedulingNext = new Button(compositeProcessScheduling, SWT.NONE);
        btnProcessSchedulingNext.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String newText=null;
                String strategy=comboSchedulingStrategy.getText();
                ArrayList<PeriodicProcess> periodicProcesses=os.periodicProcesses;
                PriorityQueue<Process> readQueueA=os.readyQueueA;
                try {
                    maxTime.set(Integer.valueOf(textMaxTime.getText()));
                } catch (Exception e2) {
                    RightMaxTimePlease rightMaxTimePlease=new RightMaxTimePlease();
                    rightMaxTimePlease.open();
                }
                if(strategy.equals(processSchedulingStrategy[0])) {
                    if(os.systemTime<maxTime.get()) {
                        if(currentStep.get()==0) {
                            for(int i=0;i<periodicProcesses.size();i++) {
                                PeriodicProcess p=periodicProcesses.get(i);
                                if(os.systemTime%p.cycle==0) {
                                    Integer x=numberX.get(i);
                                    Thread thread=new Thread(os.processID++, p.pcb.name+String.valueOf(x),
                                            0, (int) (os.systemTime+p.cycle), p.pcb.task, 0, null, os);
                                    p.threads.add(thread);

                                    newText=os.systemTime+"ms: 进程"+p.pcb.name+"创建了线程"+thread.pcb.name;
                                    styledTextLog.setCaretOffset(styledTextLog.getText().length());
                                    styledTextLog.insert(newText+"\n");
                                    styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);

                                    numberX.set(i, x+1);
                                    TableItem tableItem=new TableItem(tableProcessSchedulingProcess, 0);
                                    PCB pcb=thread.pcb;
                                    tableItem.setText(0,String.valueOf(pcb.id));
                                    tableItem.setText(1, pcb.name);
                                    tableItem.setText(2, String.valueOf(pcb.task));
                                    tableItem.setText(3, String.valueOf(pcb.deadline));
                                    tableProcessSchedulingProcess.setTopIndex(tableProcessSchedulingProcess.getItemCount()-1);

                                    TableItem tableItem2=new TableItem(tableProcessSchedulingReadyQueue, 0);
                                    tableItem2.setText(0, String.valueOf(pcb.id));
                                    tableItem2.setText(1, String.valueOf(pcb.task));
                                    tableItem2.setText(2, String.valueOf(pcb.deadline-pcb.task-os.systemTime));
                                    tableProcessSchedulingReadyQueue.setTopIndex(tableProcessSchedulingReadyQueue.getItemCount()-1);
                                }
                            }
                            currentStep.getAndAdd(1);
                            currentStep.getAndAccumulate(3, (x,y)->x%y);
                        }else if(currentStep.get()==1) {
                            if(!readQueueA.isEmpty()) {
                                Process runNow=readQueueA.peek();
                                TableItem[] tableItems=tableProcessSchedulingCurrentProcess.getItems();
                                for(int i=0;i<tableItems.length;i++) {
                                    tableItems[i].dispose();
                                }
                                TableItem tableItem=new TableItem(tableProcessSchedulingCurrentProcess, 0);
                                tableItem.setText(0, String.valueOf(runNow.pcb.id));
                            }
                            currentStep.getAndAdd(1);
                            currentStep.getAndAccumulate(3, (x,y)->x%y);
                        }else if(currentStep.get()==2) {
                            if(!readQueueA.isEmpty()) {
                                TableItem[] tableItems=null;
                                TableItem tableItem=null;
                                Process runNow=readQueueA.peek();
                                if(os.systemTime>=runNow.pcb.deadline) {
                                    readQueueA.poll();
                                    tableItems=tableProcessSchedulingReadyQueue.getItems();
                                    for(int i=0;i<tableItems.length;i++) {
                                        if(tableItems[i].getText(0).equals(String.valueOf(runNow.pcb.id))) {
                                            tableItems[i].dispose();
                                            break;
                                        }
                                    }
                                }
                                t.set(Integer.MAX_VALUE);
                                t.set(Math.min(t.get(), runNow.pcb.task));
                                for(int i=0;i<periodicProcesses.size();i++) {
                                    t.set((int) Math.min(t.get(), (numberX.get(i)-1)*periodicProcesses.get(i).cycle-os.systemTime));
                                }
                                runNow.pcb.task-=t.get();

                                newText=os.systemTime+"ms: 线程"+runNow.pcb.name+"执行了"+t.get()+"ms";
                                styledTextLog.setCaretOffset(styledTextLog.getText().length());
                                styledTextLog.insert(newText+"\n");
                                styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);

                                tableItems=tableProcessSchedulingReadyQueue.getItems();
                                for(int i=0;i<tableItems.length;i++) {
                                    if(tableItems[i].getText(0).equals(String.valueOf(runNow.pcb.id))) {
                                        tableItems[i].setText(1, String.valueOf(runNow.pcb.task));
                                        tableItems[i].setText(2,String.valueOf(runNow.pcb.deadline-runNow.pcb.task-os.systemTime));
                                        break;
                                    }
                                }
                                if(runNow.pcb.task==0) {
                                    newText=os.systemTime+t.get()+"ms: 线程"+runNow.pcb.name+"执行完毕";
                                    styledTextLog.setCaretOffset(styledTextLog.getText().length());
                                    styledTextLog.insert(newText+"\n");
                                    styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);

                                    readQueueA.poll();
                                    tableItems=tableProcessSchedulingReadyQueue.getItems();
                                    for(int i=0;i<tableItems.length;i++) {
                                        if(tableItems[i].getText(0).equals(String.valueOf(runNow.pcb.id))) {
                                            tableItems[i].dispose();
                                            break;
                                        }
                                    }
                                    tableItem=new TableItem(tableProcessSchedulingCompleteQueue, 0);
                                    tableItem.setText(0, String.valueOf(runNow.pcb.id));
                                    tableProcessSchedulingCompleteQueue.setTopIndex(tableProcessSchedulingCompleteQueue.getItemCount()-1);
                                }
                            }
                            os.systemTime+=t.get();
                            textSystemTime.setText(String.valueOf(os.systemTime));
                            TableItem[] tableItems=tableProcessSchedulingCurrentProcess.getItems();
                            for(int i=0;i<tableItems.length;i++) {
                                tableItems[i].dispose();
                            }
                            tableItems=tableProcessSchedulingReadyQueue.getItems();
                            for(int i=0;i<tableItems.length;i++) {
                                tableItems[i].setText(2, String.valueOf(Integer.valueOf(tableItems[i].getText(2))-t.get()));
                            }
                            currentStep.getAndAdd(1);
                            currentStep.getAndAccumulate(3, (x,y)->x%y);
                        }
                    }
                }else if(strategy.equals(processSchedulingStrategy[1])) {
                    if(os.systemTime+t.get()<maxTime.get()) {
                        if(currentStep.get()==0) {
                            do {
                                forwardTime.set(Integer.MAX_VALUE);
                                forwardTime.set(Math.min(forwardTime.get(), t.get()));
                                for(int i=0;i<periodicProcesses.size();i++) {
                                    forwardTime.set((int) Math.min(forwardTime.get(),(numberX.get(i)-1)*periodicProcesses.get(i).cycle-os.systemTime));
                                }
                                os.systemTime+=forwardTime.get();
                                t.getAndAdd(-forwardTime.get());
                                for(int i=0;i<periodicProcesses.size();i++) {
                                    PeriodicProcess p=periodicProcesses.get(i);
                                    if(os.systemTime%p.cycle==0) {
                                        Integer x=numberX.get(i);
                                        Thread thread=new Thread(os.processID++, p.pcb.name+String.valueOf(x),
                                                0, (int) (os.systemTime+p.cycle), p.pcb.task, 0, null, os);
                                        p.threads.add(thread);

                                        newText=os.systemTime+"ms: 进程"+p.pcb.name+"创建了线程"+thread.pcb.name;
                                        styledTextLog.setCaretOffset(styledTextLog.getText().length());
                                        styledTextLog.insert(newText+"\n");
                                        styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);

                                        numberX.set(i, x+1);
                                        TableItem tableItem=new TableItem(tableProcessSchedulingProcess, 0);
                                        PCB pcb=thread.pcb;
                                        tableItem.setText(0,String.valueOf(pcb.id));
                                        tableItem.setText(1, pcb.name);
                                        tableItem.setText(2, String.valueOf(pcb.task));
                                        tableItem.setText(3, String.valueOf(pcb.deadline));
                                        tableProcessSchedulingProcess.setTopIndex(tableProcessSchedulingProcess.getItemCount()-1);

                                        TableItem tableItem2=new TableItem(tableProcessSchedulingReadyQueue, 0);
                                        tableItem2.setText(0, String.valueOf(pcb.id));
                                        tableItem2.setText(1, String.valueOf(pcb.task));
                                        tableItem2.setText(2, String.valueOf(pcb.deadline-pcb.task-os.systemTime));
                                        tableProcessSchedulingReadyQueue.setTopIndex(tableProcessSchedulingReadyQueue.getItemCount()-1);
                                    }
                                }
                            }while (t.get()>0);
                            currentStep.getAndAdd(1);
                            currentStep.getAndAccumulate(3, (x,y)->x%y);
                        }else if(currentStep.get()==1) {
                            if(!readQueueA.isEmpty()) {
                                Process runNow=readQueueA.peek();
                                TableItem[] tableItems=tableProcessSchedulingCurrentProcess.getItems();
                                for(int i=0;i<tableItems.length;i++) {
                                    tableItems[i].dispose();
                                }
                                TableItem tableItem=new TableItem(tableProcessSchedulingCurrentProcess, 0);
                                tableItem.setText(0, String.valueOf(runNow.pcb.id));
                            }
                            currentStep.getAndAdd(1);
                            currentStep.getAndAccumulate(3, (x,y)->x%y);
                        }else if(currentStep.get()==2) {
                            if(!readQueueA.isEmpty()) {
                                TableItem[] tableItems=null;
                                TableItem tableItem=null;
                                Process runNow=readQueueA.peek();
                                if(os.systemTime>=runNow.pcb.deadline) {
                                    readQueueA.poll();
                                    tableItems=tableProcessSchedulingReadyQueue.getItems();
                                    for(int i=0;i<tableItems.length;i++) {
                                        if(tableItems[i].getText(0).equals(String.valueOf(runNow.pcb.id))) {
                                            tableItems[i].dispose();
                                            break;
                                        }
                                    }
                                }
                                t.set(Integer.MAX_VALUE);
                                t.set(Math.min(t.get(), runNow.pcb.task));
                                for(int i=0;i<periodicProcesses.size();i++) {
                                    PeriodicProcess p=periodicProcesses.get(i);
                                    t.set((int) Math.min(t.get(),numberX.get(i)*p.cycle-p.pcb.task-os.systemTime));
                                }
                                ArrayList<Process> temp=new ArrayList<Process>();
                                while(!readQueueA.isEmpty()) {
                                    temp.add(readQueueA.poll());
                                }
                                for(int i=0;i<temp.size();i++) {
                                    Process p=temp.get(i);
                                    if(p!=runNow) {
                                        t.set((int) Math.min(t.get(),p.pcb.deadline-p.pcb.task-os.systemTime));
                                    }
                                    readQueueA.add(p);
                                }
                                temp.clear();
                                runNow.pcb.task-=t.get();

                                newText=os.systemTime+"ms: 线程"+runNow.pcb.name+"执行了"+t.get()+"ms";
                                styledTextLog.setCaretOffset(styledTextLog.getText().length());
                                styledTextLog.insert(newText+"\n");
                                styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);

                                tableItems=tableProcessSchedulingReadyQueue.getItems();
                                for(int i=0;i<tableItems.length;i++) {
                                    if(tableItems[i].getText(0).equals(String.valueOf(runNow.pcb.id))) {
                                        tableItems[i].setText(1, String.valueOf(runNow.pcb.task));
                                        tableItems[i].setText(2,String.valueOf(runNow.pcb.deadline-runNow.pcb.task-os.systemTime));
                                        break;
                                    }
                                }
                                if(runNow.pcb.task==0) {
                                    newText=os.systemTime+t.get()+"ms: 线程"+runNow.pcb.name+"执行完毕";
                                    styledTextLog.setCaretOffset(styledTextLog.getText().length());
                                    styledTextLog.insert(newText+"\n");
                                    styledTextLog.setTopIndex(styledTextLog.getLineCount()-1);

                                    readQueueA.poll();
                                    tableItems=tableProcessSchedulingReadyQueue.getItems();
                                    for(int i=0;i<tableItems.length;i++) {
                                        if(tableItems[i].getText(0).equals(String.valueOf(runNow.pcb.id))) {
                                            tableItems[i].dispose();
                                            break;
                                        }
                                    }
                                    tableItem=new TableItem(tableProcessSchedulingCompleteQueue, 0);
                                    tableItem.setText(0, String.valueOf(runNow.pcb.id));
                                    tableProcessSchedulingCompleteQueue.setTopIndex(tableProcessSchedulingCompleteQueue.getItemCount()-1);
                                }
                            }
                            textSystemTime.setText(String.valueOf(os.systemTime+t.get()));
                            TableItem[] tableItems=tableProcessSchedulingCurrentProcess.getItems();
                            for(int i=0;i<tableItems.length;i++) {
                                tableItems[i].dispose();
                            }
                            tableItems=tableProcessSchedulingReadyQueue.getItems();
                            for(int i=0;i<tableItems.length;i++) {
                                tableItems[i].setText(2, String.valueOf(Integer.valueOf(tableItems[i].getText(2))-t.get()));
                            }
                            currentStep.getAndAdd(1);
                            currentStep.getAndAccumulate(3, (x,y)->x%y);
                        }
                    }
                }
            }
        });
        btnProcessSchedulingNext.setBounds(1074, 268, 98, 30);
        btnProcessSchedulingNext.setText("下一步");

        Button btnProcessSchedulingReset = new Button(compositeProcessScheduling, SWT.NONE);
        btnProcessSchedulingReset.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                os.reset();
                for(int i=0;i<numberX.size();i++) {
                    numberX.set(i, 1);
                }
                maxTime.set(0);
                currentStep.set(0);
                t.set(0);
                forwardTime.set(0);
                textSystemTime.setText(String.valueOf(os.systemTime));
                textMaxTime.setText("");
                comboSchedulingStrategy.select(0);
                styledTextLog.setText("");
                TableItem[] tableItems=tableProcessSchedulingProcess.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
                tableItems=tableProcessSchedulingReadyQueue.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
                tableItems=tableProcessSchedulingCurrentProcess.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
                tableItems=tableProcessSchedulingCompleteQueue.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
            }
        });
        btnProcessSchedulingReset.setBounds(1132, 219, 98, 30);
        btnProcessSchedulingReset.setText("重置");

        Label lblProcessSchedulingMaxTime = new Label(compositeProcessScheduling, SWT.NONE);
        lblProcessSchedulingMaxTime.setBounds(1017, 70, 76, 20);
        lblProcessSchedulingMaxTime.setText("结束时间：");

        textMaxTime = new Text(compositeProcessScheduling, SWT.BORDER | SWT.RIGHT);
        textMaxTime.setBounds(1099, 67, 73, 26);

        Label lblMaxTimeMillisecond = new Label(compositeProcessScheduling, SWT.NONE);
        lblMaxTimeMillisecond.setText("ms");
        lblMaxTimeMillisecond.setBounds(1178, 70, 39, 20);

        Label lblProcessSchedulingCompleteQueue = new Label(compositeProcessScheduling, SWT.NONE);
        lblProcessSchedulingCompleteQueue.setText("完成队列：");
        lblProcessSchedulingCompleteQueue.setBounds(508, 177, 76, 20);

        tableProcessSchedulingCompleteQueue = new Table(compositeProcessScheduling, SWT.BORDER | SWT.FULL_SELECTION);
        tableProcessSchedulingCompleteQueue.setLinesVisible(true);
        tableProcessSchedulingCompleteQueue.setHeaderVisible(true);
        tableProcessSchedulingCompleteQueue.setBounds(509, 203, 115, 393);

        TableColumn tblclmnProcessSchedulingCompleteQueueProcessID = new TableColumn(tableProcessSchedulingCompleteQueue, SWT.NONE);
        tblclmnProcessSchedulingCompleteQueueProcessID.setWidth(100);
        tblclmnProcessSchedulingCompleteQueueProcessID.setText("ID");

        Button btnCreatePeriodicProcess = new Button(compositeProcessScheduling, SWT.NONE);
        btnCreatePeriodicProcess.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                PeriodicProcessCreator periodicProcessCreator=new PeriodicProcessCreator(os);
                periodicProcessCreator.open();
                TableItem[] tableItems=tableProcessSchedulingProcess.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
                for(PeriodicProcess periodicProcess:os.periodicProcesses) {
                    PCB pcb=periodicProcess.pcb;
                    TableItem tableItem=new TableItem(tableProcessSchedulingProcess, 0);
                    tableItem.setText(0,String.valueOf(pcb.id));
                    tableItem.setText(1, pcb.name);
                    tableItem.setText(2, String.valueOf(pcb.task));
                    tableItem.setText(3, "");
                    tableItem.setText(4, String.valueOf(periodicProcess.cycle));
                }
                tableProcessSchedulingProcess.setTopIndex(tableProcessSchedulingProcess.getItemCount()-1);
            }
        });
        btnCreatePeriodicProcess.setBounds(1057, 172, 134, 30);
        btnCreatePeriodicProcess.setText("创建周期进程");

        TabItem tbtmProcessCommunication = new TabItem(tabFolder, SWT.NONE);
        tbtmProcessCommunication.setText("进程通信");

        Composite compositeProcessCommunication = new Composite(tabFolder, SWT.NONE);
        tbtmProcessCommunication.setControl(compositeProcessCommunication);
        
        Label lblProcess1 = new Label(compositeProcessCommunication, SWT.NONE);
        lblProcess1.setBounds(124, 129, 76, 20);
        lblProcess1.setText("进程1：");
        
        StyledText styledTextProcess1 = new StyledText(compositeProcessCommunication, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);
        styledTextProcess1.setBounds(124, 170, 197, 243);
        
        Label lblProcess2 = new Label(compositeProcessCommunication, SWT.NONE);
        lblProcess2.setText("进程2：");
        lblProcess2.setBounds(805, 129, 76, 20);
        
        StyledText styledTextProcess2 = new StyledText(compositeProcessCommunication, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);
        styledTextProcess2.setBounds(805, 170, 197, 243);
        
        Label lblMessageQueue = new Label(compositeProcessCommunication, SWT.NONE);
        lblMessageQueue.setBounds(469, 36, 159, 20);
        lblMessageQueue.setText("消息队列（长度为16）：");
        
        tableMessageQueue = new Table(compositeProcessCommunication, SWT.BORDER | SWT.FULL_SELECTION);
        tableMessageQueue.setBounds(469, 64, 179, 461);
        tableMessageQueue.setHeaderVisible(true);
        tableMessageQueue.setLinesVisible(true);
        
        TableColumn tblclmnMessage = new TableColumn(tableMessageQueue, SWT.NONE);
        tblclmnMessage.setWidth(154);
        tblclmnMessage.setText("消息");
        
        ArrayList<Process> communicationProcesses=new ArrayList<Process>();
        Random random=new Random();
        
        Button btnProcess1SendMessage = new Button(compositeProcessCommunication, SWT.NONE);
        btnProcess1SendMessage.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		Integer item=random.nextInt();
        		if(os.monitor.put(item, communicationProcesses.get(0), os)) {
        			String newText=communicationProcesses.get(0).pcb.name+"发送了消息: "+item;
        			styledTextProcess1.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess1.insert(newText+"\n");
        			styledTextProcess1.setTopIndex(styledTextLog.getLineCount()-1);
        		}else {
        			String newText="消息队列已满 "+communicationProcesses.get(0).pcb.name+" 被阻塞";
        			styledTextProcess1.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess1.insert(newText+"\n");
        			styledTextProcess1.setTopIndex(styledTextLog.getLineCount()-1);
        		}
        		
        		TableItem[] tableItems=tableMessageQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		for(int i=0;i<os.monitor.list.size();i++) {
        			TableItem tableItem=new TableItem(tableMessageQueue, 0);
        			tableItem.setText(0, String.valueOf(os.monitor.list.get(i)));
        		}
        		
        		tableItems=tableReadyQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		ArrayList<Process> temp=new ArrayList<Process>(os.readyQueueA);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableReadyQueue, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueEmpty.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAEmpty);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueEmpty, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueFull.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAFull);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueFull, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        	}
        });
        btnProcess1SendMessage.setBounds(347, 252, 98, 30);
        btnProcess1SendMessage.setText("发消息");
        
        Button btnProcess1TakeMessage = new Button(compositeProcessCommunication, SWT.NONE);
        btnProcess1TakeMessage.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		AtomicInteger item=new AtomicInteger();
        		if(os.monitor.take(item, communicationProcesses.get(0), os)) {
        			String newText=communicationProcesses.get(0).pcb.name+"获取了消息: "+item;
        			styledTextProcess1.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess1.insert(newText+"\n");
        			styledTextProcess1.setTopIndex(styledTextLog.getLineCount()-1);
        		}else {
        			String newText="消息队列为空 "+communicationProcesses.get(0).pcb.name+" 被阻塞";
        			styledTextProcess1.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess1.insert(newText+"\n");
        			styledTextProcess1.setTopIndex(styledTextLog.getLineCount()-1);
        		}
        		
        		TableItem[] tableItems=tableMessageQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		for(int i=0;i<os.monitor.list.size();i++) {
        			TableItem tableItem=new TableItem(tableMessageQueue, 0);
        			tableItem.setText(0, String.valueOf(os.monitor.list.get(i)));
        		}
        		
        		tableItems=tableReadyQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		ArrayList<Process> temp=new ArrayList<Process>(os.readyQueueA);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableReadyQueue, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueEmpty.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAEmpty);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueEmpty, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueFull.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAFull);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueFull, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        	}
        });
        btnProcess1TakeMessage.setBounds(347, 311, 98, 30);
        btnProcess1TakeMessage.setText("取消息");
        
        Button btnProcess2SendMessage = new Button(compositeProcessCommunication, SWT.NONE);
        btnProcess2SendMessage.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		Integer item=random.nextInt();
        		if(os.monitor.put(item, communicationProcesses.get(1), os)) {
        			String newText=communicationProcesses.get(1).pcb.name+"发送了消息: "+item;
        			styledTextProcess2.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess2.insert(newText+"\n");
        			styledTextProcess2.setTopIndex(styledTextLog.getLineCount()-1);
        		}else {
        			String newText="消息队列已满 "+communicationProcesses.get(1).pcb.name+" 被阻塞";
        			styledTextProcess2.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess2.insert(newText+"\n");
        			styledTextProcess2.setTopIndex(styledTextLog.getLineCount()-1);
        		}
        		
        		TableItem[] tableItems=tableMessageQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		for(int i=0;i<os.monitor.list.size();i++) {
        			TableItem tableItem=new TableItem(tableMessageQueue, 0);
        			tableItem.setText(0, String.valueOf(os.monitor.list.get(i)));
        		}
        		
        		tableItems=tableReadyQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		ArrayList<Process> temp=new ArrayList<Process>(os.readyQueueA);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableReadyQueue, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueEmpty.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAEmpty);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueEmpty, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueFull.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAFull);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueFull, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        	}
        });
        btnProcess2SendMessage.setText("发消息");
        btnProcess2SendMessage.setBounds(674, 252, 98, 30);
        
        Button btnProcess2TakeMessage = new Button(compositeProcessCommunication, SWT.NONE);
        btnProcess2TakeMessage.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		AtomicInteger item=new AtomicInteger();
        		if(os.monitor.take(item, communicationProcesses.get(1), os)) {
        			String newText=communicationProcesses.get(1).pcb.name+"获取了消息: "+item;
        			styledTextProcess2.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess2.insert(newText+"\n");
        			styledTextProcess2.setTopIndex(styledTextLog.getLineCount()-1);
        		}else {
        			String newText="消息队列为空 "+communicationProcesses.get(1).pcb.name+" 被阻塞";
        			styledTextProcess2.setCaretOffset(styledTextLog.getText().length());
        			styledTextProcess2.insert(newText+"\n");
        			styledTextProcess2.setTopIndex(styledTextLog.getLineCount()-1);
        		}
        		
        		TableItem[] tableItems=tableMessageQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		for(int i=0;i<os.monitor.list.size();i++) {
        			TableItem tableItem=new TableItem(tableMessageQueue, 0);
        			tableItem.setText(0, String.valueOf(os.monitor.list.get(i)));
        		}
        		
        		tableItems=tableReadyQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		ArrayList<Process> temp=new ArrayList<Process>(os.readyQueueA);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableReadyQueue, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueEmpty.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAEmpty);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueEmpty, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueFull.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAFull);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueFull, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        	}
        });
        btnProcess2TakeMessage.setText("取消息");
        btnProcess2TakeMessage.setBounds(674, 311, 98, 30);
        
        Label lblReadyQueue = new Label(compositeProcessCommunication, SWT.NONE);
        lblReadyQueue.setBounds(1074, 36, 76, 20);
        lblReadyQueue.setText("就绪队列：");
        
        tableReadyQueue = new Table(compositeProcessCommunication, SWT.BORDER | SWT.FULL_SELECTION);
        tableReadyQueue.setBounds(1074, 64, 114, 154);
        tableReadyQueue.setHeaderVisible(true);
        tableReadyQueue.setLinesVisible(true);
        
        TableColumn tblclmnReadyQueueName = new TableColumn(tableReadyQueue, SWT.NONE);
        tblclmnReadyQueueName.setWidth(100);
        tblclmnReadyQueueName.setText("名称");
        
        Label lblBlockQueueEmpty = new Label(compositeProcessCommunication, SWT.NONE);
        lblBlockQueueEmpty.setText("Empty阻塞队列：");
        lblBlockQueueEmpty.setBounds(1074, 252, 114, 20);
        
        tableBlockQueueEmpty = new Table(compositeProcessCommunication, SWT.BORDER | SWT.FULL_SELECTION);
        tableBlockQueueEmpty.setLinesVisible(true);
        tableBlockQueueEmpty.setHeaderVisible(true);
        tableBlockQueueEmpty.setBounds(1074, 280, 114, 154);
        
        TableColumn tblclmnBlockQueueEmptyName = new TableColumn(tableBlockQueueEmpty, SWT.NONE);
        tblclmnBlockQueueEmptyName.setWidth(100);
        tblclmnBlockQueueEmptyName.setText("名称");
        
        Label lblBlockQueueFull = new Label(compositeProcessCommunication, SWT.NONE);
        lblBlockQueueFull.setText("Full阻塞队列：");
        lblBlockQueueFull.setBounds(1074, 469, 114, 20);
        
        tableBlockQueueFull = new Table(compositeProcessCommunication, SWT.BORDER | SWT.FULL_SELECTION);
        tableBlockQueueFull.setLinesVisible(true);
        tableBlockQueueFull.setHeaderVisible(true);
        tableBlockQueueFull.setBounds(1074, 497, 114, 154);
        
        TableColumn tblclmnBlockQueueFullName = new TableColumn(tableBlockQueueFull, SWT.NONE);
        tblclmnBlockQueueFullName.setWidth(100);
        tblclmnBlockQueueFullName.setText("名称");
        
        Button btnProcessCommunicationReset = new Button(compositeProcessCommunication, SWT.NONE);
        btnProcessCommunicationReset.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		os.reset();
                
        		communicationProcesses.clear();
        		Process process1=new Process(0, "进程1", 0, 0, 0, 0, null, os);
                Process process2=new Process(1, "进程2", 0, 0, 0, 0, null, os);
                communicationProcesses.add(process1);
                communicationProcesses.add(process2);
        		
                styledTextProcess1.setText("");
                styledTextProcess2.setText("");
                
                TableItem[] tableItems=tableMessageQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		for(int i=0;i<os.monitor.list.size();i++) {
        			TableItem tableItem=new TableItem(tableMessageQueue, 0);
        			tableItem.setText(0, String.valueOf(os.monitor.list.get(i)));
        		}
        		
        		tableItems=tableReadyQueue.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		ArrayList<Process> temp=new ArrayList<Process>(os.readyQueueA);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableReadyQueue, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueEmpty.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAEmpty);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueEmpty, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        		
        		tableItems=tableBlockQueueFull.getItems();
        		for(int i=0;i<tableItems.length;i++) {
        			tableItems[i].dispose();
        		}
        		temp=new ArrayList<Process>(os.blockQueueAFull);
        		for(int i=0;i<temp.size();i++) {
        			TableItem tableItem=new TableItem(tableBlockQueueFull, 0);
        			tableItem.setText(0, temp.get(i).pcb.name);
        		}
        	}
        });
        btnProcessCommunicationReset.setBounds(923, 36, 98, 30);
        btnProcessCommunicationReset.setText("重置");

        TabItem tbtmAvoidDeadLock = new TabItem(tabFolder, SWT.NONE);
        tbtmAvoidDeadLock.setText("死锁避免");

        final Integer resourceNumber=3;
        Source[] sources=new Source[resourceNumber];
        sources[0]=new Source(10, "A");
        sources[1]=new Source(5, "B");
        sources[2]=new Source(7, "C");
        StringBuffer sourceStringBuffer=new StringBuffer();
        for(int i=0;i<sources.length;i++) {
            if(i==0) {
                sourceStringBuffer.append(sources[i].name);
            }else {
                sourceStringBuffer.append("     "+sources[i].name);
            }
        }
        String sourceString=sourceStringBuffer.toString();
        String[] processNames=new String[] {"P0","P1","P2","P3","P4"};
        Integer[][] Max=new Integer[][] {{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};
        Integer[][] Allocation=new Integer[][] {{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
        Integer[][] Need=new Integer[][] {{7,4,3},{1,2,2},{6,0,0},{0,1,1},{4,3,1}};
        Integer[] Available=new Integer[] {3,3,2};

        Composite compositeAvoidDeadLock = new Composite(tabFolder, SWT.NONE);
        tbtmAvoidDeadLock.setControl(compositeAvoidDeadLock);

        tableAllocationStatus = new Table(compositeAvoidDeadLock, SWT.BORDER | SWT.FULL_SELECTION);
        tableAllocationStatus.setBounds(87, 53, 525, 220);
        tableAllocationStatus.setHeaderVisible(true);
        tableAllocationStatus.setLinesVisible(true);

        TableColumn tblclmnAllocationStatusProcess = new TableColumn(tableAllocationStatus, SWT.NONE);
        tblclmnAllocationStatusProcess.setWidth(100);
        tblclmnAllocationStatusProcess.setText("Process");

        TableColumn tblclmnAllocationStatusMax = new TableColumn(tableAllocationStatus, SWT.NONE);
        tblclmnAllocationStatusMax.setWidth(100);
        tblclmnAllocationStatusMax.setText("Max");

        TableColumn tblclmnAllocationStatusAllocation = new TableColumn(tableAllocationStatus, SWT.NONE);
        tblclmnAllocationStatusAllocation.setWidth(100);
        tblclmnAllocationStatusAllocation.setText("Allocation");

        TableColumn tblclmnAllocationStatusNeed = new TableColumn(tableAllocationStatus, SWT.NONE);
        tblclmnAllocationStatusNeed.setWidth(100);
        tblclmnAllocationStatusNeed.setText("Need");

        TableColumn tblclmnAllocationStatusAvailable = new TableColumn(tableAllocationStatus, SWT.NONE);
        tblclmnAllocationStatusAvailable.setWidth(100);
        tblclmnAllocationStatusAvailable.setText("Available");

        TableItem tableItemAllocationStatusResources = new TableItem(tableAllocationStatus, SWT.NONE);
        tableItemAllocationStatusResources.setText(1,sourceString);
        tableItemAllocationStatusResources.setText(2,sourceString);
        tableItemAllocationStatusResources.setText(3,sourceString);
        tableItemAllocationStatusResources.setText(4,sourceString);

        for(int i=0;i<processNames.length;i++) {
            TableItem tableItem=new TableItem(tableAllocationStatus, 0);
            tableItem.setText(0, processNames[i]);

            StringBuffer nums=new StringBuffer();
            for(int j=0;j<Max[i].length;j++) {
                if(j==0) {
                    nums.append(String.valueOf(Max[i][j]));
                }else {
                    nums.append("     "+String.valueOf(Max[i][j]));
                }
            }
            tableItem.setText(1, nums.toString());

            nums.setLength(0);
            for(int j=0;j<Allocation[i].length;j++) {
                if(j==0) {
                    nums.append(String.valueOf(Allocation[i][j]));
                }else {
                    nums.append("     "+String.valueOf(Allocation[i][j]));
                }
            }
            tableItem.setText(2, nums.toString());

            nums.setLength(0);
            for(int j=0;j<Need[i].length;j++) {
                if(j==0) {
                    nums.append(String.valueOf(Need[i][j]));
                }else {
                    nums.append("     "+String.valueOf(Need[i][j]));
                }
            }
            tableItem.setText(3, nums.toString());

            nums.setLength(0);
            if(i==0) {
                for(int j=0;j<Available.length;j++) {
                    if(j==0) {
                        nums.append(String.valueOf(Available[j]));
                    }else {
                        nums.append("     "+String.valueOf(Available[j]));
                    }
                }
                tableItem.setText(4, nums.toString());
            }
        }

        Label lblAllocationStatus = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblAllocationStatus.setBounds(87, 22, 105, 20);
        lblAllocationStatus.setText("资源分配情况：");

        Label lblSafetyCheck = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblSafetyCheck.setBounds(87, 297, 90, 20);
        lblSafetyCheck.setText("安全性算法：");

        tableSafetyCheck = new Table(compositeAvoidDeadLock, SWT.BORDER | SWT.FULL_SELECTION);
        tableSafetyCheck.setBounds(88, 328, 663, 220);
        tableSafetyCheck.setHeaderVisible(true);
        tableSafetyCheck.setLinesVisible(true);

        TableColumn tblclmnSafetyCheckProcess = new TableColumn(tableSafetyCheck, SWT.NONE);
        tblclmnSafetyCheckProcess.setWidth(100);
        tblclmnSafetyCheckProcess.setText("Process");

        TableColumn tblclmnSafetyCheckWork = new TableColumn(tableSafetyCheck, SWT.NONE);
        tblclmnSafetyCheckWork.setWidth(100);
        tblclmnSafetyCheckWork.setText("Work");

        TableColumn tblclmnSafetyCheckNeed = new TableColumn(tableSafetyCheck, SWT.NONE);
        tblclmnSafetyCheckNeed.setWidth(100);
        tblclmnSafetyCheckNeed.setText("Need");

        TableColumn tblclmnSafetyCheckAllocation = new TableColumn(tableSafetyCheck, SWT.NONE);
        tblclmnSafetyCheckAllocation.setWidth(100);
        tblclmnSafetyCheckAllocation.setText("Allocation");

        TableColumn tblclmnSafetyCheckWorkPlusAllocation = new TableColumn(tableSafetyCheck, SWT.NONE);
        tblclmnSafetyCheckWorkPlusAllocation.setWidth(138);
        tblclmnSafetyCheckWorkPlusAllocation.setText("Work+Allocation");

        TableColumn tblclmnSafetyCheckFinish = new TableColumn(tableSafetyCheck, SWT.NONE);
        tblclmnSafetyCheckFinish.setWidth(100);
        tblclmnSafetyCheckFinish.setText("Finish");

        TableItem tableItemSafetyCheckResources = new TableItem(tableSafetyCheck, 0);
        tableItemSafetyCheckResources.setText(1,sourceString);
        tableItemSafetyCheckResources.setText(2,sourceString);
        tableItemSafetyCheckResources.setText(3,sourceString);
        tableItemSafetyCheckResources.setText(4,sourceString);

        Label lblResourceA = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblResourceA.setBounds(974, 22, 86, 20);
        lblResourceA.setText("资源A数量：");

        textResourceA = new Text(compositeAvoidDeadLock, SWT.BORDER | SWT.READ_ONLY);
        textResourceA.setBounds(1066, 19, 73, 26);
        textResourceA.setText(String.valueOf(sources[0].num));

        Label lblResourceB = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblResourceB.setText("资源B数量：");
        lblResourceB.setBounds(974, 56, 86, 20);

        textResourceB = new Text(compositeAvoidDeadLock, SWT.BORDER | SWT.READ_ONLY);
        textResourceB.setText(String.valueOf(sources[1].num));
        textResourceB.setBounds(1066, 53, 73, 26);

        Label lblResourceC = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblResourceC.setText("资源C数量：");
        lblResourceC.setBounds(974, 88, 86, 20);

        textResourceC = new Text(compositeAvoidDeadLock, SWT.BORDER | SWT.READ_ONLY);
        textResourceC.setText(String.valueOf(sources[2].num));
        textResourceC.setBounds(1066, 85, 73, 26);

        Combo comboChooseProcess = new Combo(compositeAvoidDeadLock, SWT.NONE | SWT.READ_ONLY);
        comboChooseProcess.setBounds(1066, 157, 92, 28);
        for(int i=0;i<processNames.length;i++) {
            comboChooseProcess.add(processNames[i]);
        }
        comboChooseProcess.select(0);

        Label lblChooseProcess = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblChooseProcess.setBounds(974, 160, 76, 20);
        lblChooseProcess.setText("选择进程：");

        Label lblRequest = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblRequest.setBounds(974, 208, 76, 20);
        lblRequest.setText("请求资源：");

        Label lblRequestA = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblRequestA.setBounds(974, 234, 86, 20);
        lblRequestA.setText("RequestA: ");

        textRequestA = new Text(compositeAvoidDeadLock, SWT.BORDER);
        textRequestA.setBounds(1066, 231, 73, 26);

        textRequestB = new Text(compositeAvoidDeadLock, SWT.BORDER);
        textRequestB.setBounds(1066, 263, 73, 26);

        Label lblRequestB = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblRequestB.setText("RequestB: ");
        lblRequestB.setBounds(974, 266, 86, 20);

        textRequestC = new Text(compositeAvoidDeadLock, SWT.BORDER);
        textRequestC.setBounds(1066, 297, 73, 26);

        Label lblRequestC = new Label(compositeAvoidDeadLock, SWT.NONE);
        lblRequestC.setText("RequestC: ");
        lblRequestC.setBounds(974, 300, 86, 20);

        Integer[][] AllocationLocal=new Integer[][] {{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
        Integer[][] NeedLocal=new Integer[][] {{7,4,3},{1,2,2},{6,0,0},{0,1,1},{4,3,1}};
        Integer[] AvailableLocal=new Integer[] {3,3,2};

        Button btnTryAlloc = new Button(compositeAvoidDeadLock, SWT.NONE);
        btnTryAlloc.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] tableItems=tableSafetyCheck.getItems();
                for(int i=1;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }

                Integer requestA=null;
                Integer requestB=null;
                Integer requestC=null;
                try {
                    requestA=Integer.valueOf(textRequestA.getText());
                    requestB=Integer.valueOf(textRequestB.getText());
                    requestC=Integer.valueOf(textRequestC.getText());

                    ArrayList<String> processNamesList=new ArrayList<String>(Arrays.asList(processNames));
                    Integer processNum=processNamesList.indexOf(comboChooseProcess.getText());

                    Integer[] request=new Integer[] {requestA,requestB,requestC};

                    boolean lessThanOrEqual=true;
                    for(int j=0;j<resourceNumber;j++) {
                        if(request[j]>NeedLocal[processNum][j]) {
                            lessThanOrEqual=false;
                            break;
                        }
                    }
                    if(lessThanOrEqual) {
                        for(int j=0;j<resourceNumber;j++) {
                            if(request[j]>AvailableLocal[j]) {
                                lessThanOrEqual=false;
                                break;
                            }
                        }

                        if(lessThanOrEqual) {
                            for(int j=0;j<resourceNumber;j++) {
                                AvailableLocal[j]=AvailableLocal[j]-request[j];
                                AllocationLocal[processNum][j]=AllocationLocal[processNum][j]+request[j];
                                NeedLocal[processNum][j]=NeedLocal[processNum][j]-request[j];
                            }

                            ArrayList<Integer> Work=new ArrayList<Integer>(Arrays.asList(AvailableLocal));
                            ArrayList<Boolean> Finish=new ArrayList<Boolean>();
                            for(int i=0;i<processNames.length;i++) {
                                Finish.add(false);
                            }
                            for(int k=0;k<processNames.length;k++) {
                                for(int i=0;i<processNames.length;i++) {
                                    lessThanOrEqual=true;
                                    if(Finish.get(i)) {
                                        continue;
                                    }
                                    for(int j=0;j<resourceNumber;j++) {
                                        if(NeedLocal[i][j]>Work.get(j)) {
                                            lessThanOrEqual=false;
                                            break;
                                        }
                                    }
                                    if(!lessThanOrEqual) {
                                        continue;
                                    }

                                    TableItem tableItem=new TableItem(tableSafetyCheck, 0);
                                    tableItem.setText(0,processNames[i]);

                                    StringBuffer nums=new StringBuffer();
                                    for(int j=0;j<Work.size();j++) {
                                        if(j==0) {
                                            nums.append(String.valueOf(Work.get(j)));
                                        }else {
                                            nums.append("     "+String.valueOf(Work.get(j)));
                                        }
                                    }
                                    tableItem.setText(1, nums.toString());

                                    nums.setLength(0);
                                    for(int j=0;j<NeedLocal[i].length;j++) {
                                        if(j==0) {
                                            nums.append(String.valueOf(NeedLocal[i][j]));
                                        }else {
                                            nums.append("     "+String.valueOf(NeedLocal[i][j]));
                                        }
                                    }
                                    tableItem.setText(2, nums.toString());

                                    nums.setLength(0);
                                    for(int j=0;j<AllocationLocal[i].length;j++) {
                                        if(j==0) {
                                            nums.append(String.valueOf(AllocationLocal[i][j]));
                                        }else {
                                            nums.append("     "+String.valueOf(AllocationLocal[i][j]));
                                        }
                                    }
                                    tableItem.setText(3, nums.toString());

                                    for(int j=0;j<resourceNumber;j++) {
                                        Work.set(j, Work.get(j)+AllocationLocal[i][j]);
                                    }
                                    nums.setLength(0);
                                    for(int j=0;j<Work.size();j++) {
                                        if(j==0) {
                                            nums.append(String.valueOf(Work.get(j)));
                                        }else {
                                            nums.append("     "+String.valueOf(Work.get(j)));
                                        }
                                    }
                                    tableItem.setText(4, nums.toString());

                                    Finish.set(i,true);
                                    tableItem.setText(5,"true");
                                }
                            }
                            boolean allSet=true;
                            for(int i=0;i<Finish.size();i++) {
                                if(!Finish.get(i)) {
                                    allSet=false;
                                    TableItem tableItem=new TableItem(tableSafetyCheck, 0);
                                    tableItem.setText(0, processNames[i]);
                                    tableItem.setText(5, "false");
                                }
                            }
                            if(allSet) {
                                tableItems=tableAllocationStatus.getItems();
                                TableItem tableItem=tableItems[processNum+1];

                                StringBuffer nums=new StringBuffer();
                                nums.setLength(0);
                                for(int j=0;j<AllocationLocal[processNum].length;j++) {
                                    if(j==0) {
                                        nums.append(String.valueOf(AllocationLocal[processNum][j]));
                                    }else {
                                        nums.append("     "+String.valueOf(AllocationLocal[processNum][j]));
                                    }
                                }
                                tableItem.setText(2, nums.toString());

                                nums.setLength(0);
                                for(int j=0;j<NeedLocal[processNum].length;j++) {
                                    if(j==0) {
                                        nums.append(String.valueOf(NeedLocal[processNum][j]));
                                    }else {
                                        nums.append("     "+String.valueOf(NeedLocal[processNum][j]));
                                    }
                                }
                                tableItem.setText(3, nums.toString());

                                tableItem=tableItems[1];
                                nums.setLength(0);
                                for(int j=0;j<AvailableLocal.length;j++) {
                                    if(j==0) {
                                        nums.append(String.valueOf(AvailableLocal[j]));
                                    }else {
                                        nums.append("     "+String.valueOf(AvailableLocal[j]));
                                    }
                                }
                                tableItem.setText(4, nums.toString());

                                AllocSuccess allocSuccess=new AllocSuccess();
                                allocSuccess.open();
                            }else {
                                DeadLockHappens deadLockHappens=new DeadLockHappens();
                                deadLockHappens.open();
                            }
                        }else {
                            LargerThanAvailable largerThanAvailable=new LargerThanAvailable();
                            largerThanAvailable.open();
                        }
                    }else {
                        LargerThanNeed largerThanNeed=new LargerThanNeed();
                        largerThanNeed.open();
                    }
                } catch (Exception e2) {
                    RightRequestValuePlease rightRequestValuePlease=new RightRequestValuePlease();
                    rightRequestValuePlease.open();
                }
            }
        });
        btnTryAlloc.setBounds(974, 340, 98, 30);
        btnTryAlloc.setText("分配");

        Button btnAvoidDeadLockReset = new Button(compositeAvoidDeadLock, SWT.NONE);
        btnAvoidDeadLockReset.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                for(int i=0;i<resourceNumber;i++) {
                    AvailableLocal[i]=Available[i];
                }
                for(int i=0;i<processNames.length;i++) {
                    for(int j=0;j<resourceNumber;j++) {
                        AllocationLocal[i][j]=Allocation[i][j];
                    }
                }
                for(int i=0;i<processNames.length;i++) {
                    for(int j=0;j<resourceNumber;j++) {
                        NeedLocal[i][j]=Need[i][j];
                    }
                }

                comboChooseProcess.select(0);
                textRequestA.setText("");
                textRequestB.setText("");
                textRequestC.setText("");

                TableItem[] tableItems=tableAllocationStatus.getItems();
                for(int i=1;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }

                for(int i=0;i<processNames.length;i++) {
                    TableItem tableItem=new TableItem(tableAllocationStatus, 0);
                    tableItem.setText(0, processNames[i]);

                    StringBuffer nums=new StringBuffer();
                    for(int j=0;j<Max[i].length;j++) {
                        if(j==0) {
                            nums.append(String.valueOf(Max[i][j]));
                        }else {
                            nums.append("     "+String.valueOf(Max[i][j]));
                        }
                    }
                    tableItem.setText(1, nums.toString());

                    nums.setLength(0);
                    for(int j=0;j<Allocation[i].length;j++) {
                        if(j==0) {
                            nums.append(String.valueOf(Allocation[i][j]));
                        }else {
                            nums.append("     "+String.valueOf(Allocation[i][j]));
                        }
                    }
                    tableItem.setText(2, nums.toString());

                    nums.setLength(0);
                    for(int j=0;j<Need[i].length;j++) {
                        if(j==0) {
                            nums.append(String.valueOf(Need[i][j]));
                        }else {
                            nums.append("     "+String.valueOf(Need[i][j]));
                        }
                    }
                    tableItem.setText(3, nums.toString());

                    nums.setLength(0);
                    if(i==0) {
                        for(int j=0;j<Available.length;j++) {
                            if(j==0) {
                                nums.append(String.valueOf(Available[j]));
                            }else {
                                nums.append("     "+String.valueOf(Available[j]));
                            }
                        }
                        tableItem.setText(4, nums.toString());
                    }
                }

                tableItems=tableSafetyCheck.getItems();
                for(int i=1;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
            }
        });
        btnAvoidDeadLockReset.setBounds(1088, 340, 98, 30);
        btnAvoidDeadLockReset.setText("重置");

        TabItem tbtmMemoryAllocation = new TabItem(tabFolder, SWT.NONE);
        tbtmMemoryAllocation.setText("内存分配和外存访问");

        Composite compositeMemoryAllocation = new Composite(tabFolder, SWT.NONE);
        tbtmMemoryAllocation.setControl(compositeMemoryAllocation);

        Label lblMemoryAllocationProcess = new Label(compositeMemoryAllocation, SWT.NONE);
        lblMemoryAllocationProcess.setBounds(91, 10, 45, 20);
        lblMemoryAllocationProcess.setText("进程：");

        tableMemoryAllocationProcess = new Table(compositeMemoryAllocation, SWT.BORDER | SWT.FULL_SELECTION);
        tableMemoryAllocationProcess.setBounds(91, 36, 259, 249);
        tableMemoryAllocationProcess.setHeaderVisible(true);
        tableMemoryAllocationProcess.setLinesVisible(true);

        TableColumn tblclmnMemoryAllocationProcessID = new TableColumn(tableMemoryAllocationProcess, SWT.NONE);
        tblclmnMemoryAllocationProcessID.setWidth(61);
        tblclmnMemoryAllocationProcessID.setText("PID");

        TableColumn tblclmnMemoryNeed = new TableColumn(tableMemoryAllocationProcess, SWT.NONE);
        tblclmnMemoryNeed.setWidth(100);
        tblclmnMemoryNeed.setText("所需内存(K)");

        TableColumn tblclmnMemoryAllocationStatus = new TableColumn(tableMemoryAllocationProcess, SWT.NONE);
        tblclmnMemoryAllocationStatus.setWidth(81);
        tblclmnMemoryAllocationStatus.setText("进程状态");

        Label lblMemoryAllocationCurrentProcess = new Label(compositeMemoryAllocation, SWT.NONE);
        lblMemoryAllocationCurrentProcess.setBounds(384, 10, 105, 20);
        lblMemoryAllocationCurrentProcess.setText("当前运行进程：");

        tableMemoryAllocationCurrentProcess = new Table(compositeMemoryAllocation, SWT.BORDER | SWT.FULL_SELECTION);
        tableMemoryAllocationCurrentProcess.setBounds(384, 36, 98, 249);
        tableMemoryAllocationCurrentProcess.setHeaderVisible(true);
        tableMemoryAllocationCurrentProcess.setLinesVisible(true);

        TableColumn tblclmnMemoryAllocationCurrentProcessID = new TableColumn(tableMemoryAllocationCurrentProcess, SWT.NONE);
        tblclmnMemoryAllocationCurrentProcessID.setWidth(78);
        tblclmnMemoryAllocationCurrentProcessID.setText("PID");

        Label lblMemory = new Label(compositeMemoryAllocation, SWT.NONE);
        lblMemory.setBounds(707, 10, 45, 20);
        lblMemory.setText("内存：");

        tableMemory = new Table(compositeMemoryAllocation, SWT.BORDER | SWT.FULL_SELECTION);
        tableMemory.setBounds(707, 36, 115, 601);
        tableMemory.setHeaderVisible(true);
        tableMemory.setLinesVisible(true);

        TableColumn tblclmnMemoryList = new TableColumn(tableMemory, SWT.NONE);
        tblclmnMemoryList.setWidth(100);
        tblclmnMemoryList.setText("内存");

        Label lblStorage = new Label(compositeMemoryAllocation, SWT.NONE);
        lblStorage.setBounds(864, 10, 45, 20);
        lblStorage.setText("外存：");

        tableStorage = new Table(compositeMemoryAllocation, SWT.BORDER | SWT.FULL_SELECTION);
        tableStorage.setBounds(864, 36, 115, 601);
        tableStorage.setHeaderVisible(true);
        tableStorage.setLinesVisible(true);

        TableColumn tblclmnSwapAreaList = new TableColumn(tableStorage, SWT.NONE);
        tblclmnSwapAreaList.setWidth(100);
        tblclmnSwapAreaList.setText("交换区");

        Label lblTLB = new Label(compositeMemoryAllocation, SWT.NONE);
        lblTLB.setBounds(91, 312, 76, 20);
        lblTLB.setText("快表：");

        tableTLB = new Table(compositeMemoryAllocation, SWT.BORDER | SWT.FULL_SELECTION);
        tableTLB.setBounds(91, 338, 532, 299);
        tableTLB.setHeaderVisible(true);
        tableTLB.setLinesVisible(true);

        TableColumn tblclmnPageNumber = new TableColumn(tableTLB, SWT.NONE);
        tblclmnPageNumber.setWidth(62);
        tblclmnPageNumber.setText("页号");

        TableColumn tblclmnFrameNumber = new TableColumn(tableTLB, SWT.NONE);
        tblclmnFrameNumber.setWidth(77);
        tblclmnFrameNumber.setText("物理块号");

        TableColumn tblclmnImMemory = new TableColumn(tableTLB, SWT.NONE);
        tblclmnImMemory.setWidth(60);
        tblclmnImMemory.setText("状态位");

        TableColumn tblclmnAccessField = new TableColumn(tableTLB, SWT.NONE);
        tblclmnAccessField.setWidth(73);
        tblclmnAccessField.setText("访问字段");

        TableColumn tblclmnModified = new TableColumn(tableTLB, SWT.NONE);
        tblclmnModified.setWidth(60);
        tblclmnModified.setText("修改位");

        TableColumn tblclmnStorageAddress = new TableColumn(tableTLB, SWT.NONE);
        tblclmnStorageAddress.setWidth(100);
        tblclmnStorageAddress.setText("外存地址");

        TableColumn tblclmnProcessID = new TableColumn(tableTLB, SWT.NONE);
        tblclmnProcessID.setWidth(84);
        tblclmnProcessID.setText("进程ID");

        Combo comboAllocFrames = new Combo(compositeMemoryAllocation, SWT.READ_ONLY);
        comboAllocFrames.setBounds(1127, 83, 92, 28);
        for(int i=1;i<=6;i++) {
            comboAllocFrames.add(String.valueOf(i));
        }
        comboAllocFrames.select(2);

        String[] pageReplacementStrategy=new String[] {"FIFO","LRU"};

        Combo comboPageReplacement = new Combo(compositeMemoryAllocation, SWT.NONE | SWT.READ_ONLY);
        comboPageReplacement.setBounds(1127, 33, 92, 28);
        for(int i=0;i<pageReplacementStrategy.length;i++) {
            comboPageReplacement.add(pageReplacementStrategy[i]);
        }
        comboPageReplacement.select(0);

        for(int i=0;i<os.memory.frames.size();i++) {
            TableItem tableItem=new TableItem(tableMemory, 0);
            if(os.memory.frames.get(i).isAlloc) {
                tableItem.setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
            }else {
                tableItem.setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
            }
        }

        for(int i=0;i<os.storage.swapArea.size/Page.PageSize;i++) {
            TableItem tableItem=new TableItem(tableStorage, 0);
            tableItem.setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
        }
        TableItem[] tableItems=tableStorage.getItems();
        for(int j=0;j<os.storage.freeNodes.get(10).size();j++) {
            ArrayList<Node> nodes=os.storage.freeNodes.get(10);
            Node node=nodes.get(j);
            tableItems[node.startAddress/Page.PageSize].setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
        }

        ArrayList<Process> processes=new ArrayList<Process>();
        ArrayList<ArrayList<Integer>> processPages=new ArrayList<ArrayList<Integer>>();
        StringBuffer replacementStrategy=new StringBuffer();
        AtomicInteger currentProcessNum=new AtomicInteger();
        HashMap<Integer,ArrayList<Integer>> processReplacement=new HashMap<>();
        HashMap<Integer,LinkedList<Integer>> processFIFOMemory=new HashMap<>();
        HashMap<Integer,LinkedList<Integer>> processLRUMemory=new HashMap<>();

        Button btnCreateProcess = new Button(compositeMemoryAllocation, SWT.NONE);
        btnCreateProcess.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                PageTable.MinPageNum=Integer.valueOf(comboAllocFrames.getText());

                StringBuffer pageAccessSequence=new StringBuffer();
                ProcessCreatorByPageAccess processCreatorByPageAccess=new ProcessCreatorByPageAccess(pageAccessSequence);
                processCreatorByPageAccess.open();
                String[] pageAccess=pageAccessSequence.toString().split(" ");
                ArrayList<Integer> pages=new ArrayList<Integer>();
                Integer maxPage=Integer.MIN_VALUE;
                try {
                    for(int i=0;i<pageAccess.length;i++) {
                        pages.add(Integer.valueOf(pageAccess[i]));
                        maxPage=Math.max(maxPage, pages.get(i));
                    }
                } catch (Exception e2) {
                    RightValuePlease rightValuePlease=new RightValuePlease();
                    rightValuePlease.open();
                }
                Integer memoryNeed=(maxPage+1)*Page.PageSize;
                Process process=new Process(os.processID++, null, 0, 0, 0, memoryNeed, null, os);
                processes.add(process);
                processPages.add(pages);
                processReplacement.put(process.pcb.id,new ArrayList<>());
                if(replacementStrategy.toString().equals("FIFO")) {
                    processFIFOMemory.put(process.pcb.id,new LinkedList<>());
                }else if(replacementStrategy.toString().equals("LRU")) {
                    processLRUMemory.put(process.pcb.id,new LinkedList<>());
                }

                for(int i=0;i<PageTable.MinPageNum;i++) {
                    if(!process.pageTable.pages.get(i).inMemory) {
                        break;
                    }
                    processReplacement.get(process.pcb.id).add(process.pageTable.pages.get(i).id);
                    if(replacementStrategy.toString().equals("FIFO")) {
                        processFIFOMemory.get(process.pcb.id).add(process.pageTable.pages.get(i).id);
                    }else if(replacementStrategy.toString().equals("LRU")) {
                        processLRUMemory.get(process.pcb.id).add(process.pageTable.pages.get(i).id);
                    }
                }

                for(int i=0;i<processReplacement.get(process.pcb.id).size();i++) {
                    TableItem tableItem=new TableItem(tableReplacement, 0);
                    tableItem.setText(0, String.valueOf(processReplacement.get(process.pcb.id).get(i)));
                }

                TableItem tableItem=new TableItem(tableMemoryAllocationProcess, 0);
                tableItem.setText(0, String.valueOf(process.pcb.id));
                tableItem.setText(1, String.valueOf(process.pcb.memoryNeed/Page.PageSize));
                tableItem.setText(2, states[new ArrayList<State>(Arrays.asList(State.values())).indexOf(process.pcb.state)]);

                TableItem[] tableItems=tableStorage.getItems();
                for(int i=0;i<os.storage.swapArea.size/Page.PageSize;i++) {
                    tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
                }
                for(int j=0;j<os.storage.freeNodes.get(10).size();j++) {
                    ArrayList<Node> nodes=os.storage.freeNodes.get(10);
                    Node node=nodes.get(j);
                    if(node.processId!=-1) {
                        tableItems[node.startAddress/Page.PageSize].setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
                    }
                }

                tableItems=tableMemory.getItems();
                for(int i=0;i<os.memory.frames.size();i++) {
                    if(os.memory.frames.get(i).isAlloc) {
                        tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
                    }else {
                        tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
                    }
                }

                for(int i=0;i<os.tlb.pages.size();i++) {
                    Page page=os.tlb.pages.get(i);
                    tableItem=new TableItem(tableTLB, 0);
                    tableItem.setText(0, String.valueOf(page.id));
                    tableItem.setText(1, String.valueOf(page.frameNumber));
                    tableItem.setText(2, String.valueOf(page.inMemory));
                    tableItem.setText(3, String.valueOf(page.accessField));
                    tableItem.setText(4, String.valueOf(page.modified));
                    tableItem.setText(5, String.valueOf(page.storageAddress));
                    tableItem.setText(6, String.valueOf(page.processID));
                }
            }
        });
        btnCreateProcess.setBounds(1070, 140, 98, 30);
        btnCreateProcess.setText("创建进程");

        Button btnMemoryAccessNextStep = new Button(compositeMemoryAllocation, SWT.NONE);
        btnMemoryAccessNextStep.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ArrayList<Integer> pages=processPages.get(currentProcessNum.get());
                if(pages.size()==0) {
                    return;
                }
                Integer page=pages.get(0);
                pages.remove(page);

                Integer removeNum=null;
                if(processReplacement.get(currentProcessNum.get()).contains(page)) {
                    if(replacementStrategy.toString().equals("LRU")) {
                        processLRUMemory.get(currentProcessNum.get()).remove(page);
                        processLRUMemory.get(currentProcessNum.get()).add(page);
                    }
                }else {
                    if(processReplacement.get(currentProcessNum.get()).size()>=PageTable.MinPageNum) {
                        if (replacementStrategy.toString().equals("FIFO")) {
                            removeNum=processFIFOMemory.get(currentProcessNum.get()).pollFirst();
                        }else if(replacementStrategy.toString().equals("LRU")) {
                            removeNum=processLRUMemory.get(currentProcessNum.get()).pollFirst();
                        }
                        processReplacement.get(currentProcessNum.get()).remove(removeNum);
                    }
                    processReplacement.get(currentProcessNum.get()).add(page);
                    if (replacementStrategy.toString().equals("FIFO")) {
                        processFIFOMemory.get(currentProcessNum.get()).add(page);
                    }else if(replacementStrategy.toString().equals("LRU")) {
                        processLRUMemory.get(currentProcessNum.get()).add(page);
                    }
                }

                for(int i=0;i<os.tlb.pages.size();i++) {
                    os.tlb.pages.get(i).accessField>>=1;
                    os.tlb.pages.get(i).accessField=Math.max(os.tlb.pages.get(i).accessField, 0);
                }
                Optional<Page> pageOptional=os.tlb.find(page,currentProcessNum.get());
                if(pageOptional.isPresent()) {
                    if(os.tlb.strategy==ReplaceStrategy.LRU) {
                        os.tlb.LRUQueue.remove(pageOptional.get());
                        os.tlb.LRUQueue.add(pageOptional.get());
                    }
                    pageOptional.get().modified=true;
                    pageOptional.get().accessField+=10;
                }else {
                    for(int i=0;i<processes.get(currentProcessNum.get()).pageTable.pages.size();i++) {
                        if(processes.get(currentProcessNum.get()).pageTable.pages.get(i).id!=page) {
                            continue;
                        }
                        if(processes.get(currentProcessNum.get()).pageTable.pages.get(i).inMemory==true) {
                            os.tlb.add(processes.get(currentProcessNum.get()).pageTable.pages.get(i));
                            processes.get(currentProcessNum.get()).pageTable.pages.get(i).modified=true;
                            processes.get(currentProcessNum.get()).pageTable.pages.get(i).accessField+=10;
                        }else {
                            Integer storageAddress=processes.get(currentProcessNum.get()).pageTable.pages.get(i).storageAddress;
                            os.storage.recycle(storageAddress);
                            for(int j=0;j<processes.get(currentProcessNum.get()).pageTable.pages.size();j++) {
                                if(processes.get(currentProcessNum.get()).pageTable.pages.get(j).id!=removeNum) {
                                    continue;
                                }
                                os.memory.recycle(processes.get(currentProcessNum.get()).pageTable.pages.get(j).frameNumber);
                                try {
                                    Integer address = os.storage.alloc(currentProcessNum.get(), Page.PageSize);
                                    if (address == null) {
                                        throw new Exception("Swap Area is full.");
                                    }
                                    processes.get(currentProcessNum.get()).pageTable.pages.get(j).inMemory=false;
                                    processes.get(currentProcessNum.get()).pageTable.pages.get(j).frameNumber=-1;
                                    processes.get(currentProcessNum.get()).pageTable.pages.get(j).storageAddress=address;
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            int frameNumber=os.memory.alloc(currentProcessNum.get());
                            processes.get(currentProcessNum.get()).pageTable.pages.get(i).inMemory=true;
                            processes.get(currentProcessNum.get()).pageTable.pages.get(i).frameNumber=frameNumber;
                            processes.get(currentProcessNum.get()).pageTable.pages.get(i).storageAddress=-1;
                            os.tlb.add(processes.get(currentProcessNum.get()).pageTable.pages.get(i));
                            processes.get(currentProcessNum.get()).pageTable.pages.get(i).modified=true;
                            processes.get(currentProcessNum.get()).pageTable.pages.get(i).accessField+=10;
                        }
                    }
                }
                TableItem[] tableItems= tableTLB.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
                TableItem tableItem=null;
                for(int i=0;i<os.tlb.pages.size();i++) {
                    Page p=os.tlb.pages.get(i);
                    tableItem=new TableItem(tableTLB, 0);
                    tableItem.setText(0, String.valueOf(p.id));
                    tableItem.setText(1, String.valueOf(p.frameNumber));
                    tableItem.setText(2, String.valueOf(p.inMemory));
                    tableItem.setText(3, String.valueOf(p.accessField));
                    tableItem.setText(4, String.valueOf(p.modified));
                    tableItem.setText(5, String.valueOf(p.storageAddress));
                    tableItem.setText(6, String.valueOf(p.processID));
                }

                tableItems=tableReplacement.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
                for(int i=0;i<processReplacement.get(currentProcessNum.get()).size();i++) {
                    Integer x=processReplacement.get(currentProcessNum.get()).get(i);
                    tableItem=new TableItem(tableReplacement, 0);
                    tableItem.setText(0,String.valueOf(x));
                }

                tableItems= tableMemoryAllocationCurrentProcess.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }
                tableItem=new TableItem(tableMemoryAllocationCurrentProcess, 0);
                tableItem.setText(0,String.valueOf(currentProcessNum.get()));

                tableItems=tableStorage.getItems();
                for(int i=0;i<os.storage.swapArea.size/Page.PageSize;i++) {
                    tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
                }
                for(int j=0;j<os.storage.freeNodes.get(10).size();j++) {
                    ArrayList<Node> nodes=os.storage.freeNodes.get(10);
                    Node node=nodes.get(j);
                    if(node.processId!=-1) {
                        tableItems[node.startAddress/Page.PageSize].setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
                    }
                }

                tableItems=tableMemory.getItems();
                for(int i=0;i<os.memory.frames.size();i++) {
                    if(os.memory.frames.get(i).isAlloc) {
                        tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
                    }else {
                        tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
                    }
                }

                currentProcessNum.getAndAdd(1);
                currentProcessNum.getAndAccumulate(processes.size(),(x,y)->x%y);
            }
        });
        btnMemoryAccessNextStep.setBounds(1070, 255, 98, 30);
        btnMemoryAccessNextStep.setText("下一步");

        Button btnMemoryAccessReset = new Button(compositeMemoryAllocation, SWT.NONE);
        btnMemoryAccessReset.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                os.reset();

                processes.clear();
                processPages.clear();
                processFIFOMemory.clear();
                processLRUMemory.clear();
                replacementStrategy.setLength(0);
                processReplacement.clear();

                TableItem[] tableItems=tableStorage.getItems();
                for(int i=0;i<os.storage.swapArea.size/Page.PageSize;i++) {
                    tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
                }
                for(int j=0;j<os.storage.freeNodes.get(10).size();j++) {
                    ArrayList<Node> nodes=os.storage.freeNodes.get(10);
                    Node node=nodes.get(j);
                    if(node.processId!=-1) {
                        tableItems[node.startAddress/Page.PageSize].setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
                    }
                }

                tableItems=tableMemory.getItems();
                for(int i=0;i<os.memory.frames.size();i++) {
                    if(os.memory.frames.get(i).isAlloc) {
                        tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(255, 0, 0)));
                    }else {
                        tableItems[i].setBackground(new Color(Display.getCurrent(),new RGB(0, 255, 0)));
                    }
                }

                tableItems=tableMemoryAllocationProcess.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }

                tableItems=tableMemoryAllocationCurrentProcess.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }

                tableItems=tableTLB.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }

                tableItems= tableReplacement.getItems();
                for(int i=0;i<tableItems.length;i++) {
                    tableItems[i].dispose();
                }

                comboAllocFrames.select(2);
                comboPageReplacement.select(0);
            }
        });
        btnMemoryAccessReset.setBounds(1127, 197, 98, 30);
        btnMemoryAccessReset.setText("重置");

        Label lblPageReplacement = new Label(compositeMemoryAllocation, SWT.NONE);
        lblPageReplacement.setBounds(1023, 36, 98, 20);
        lblPageReplacement.setText("页面置换算法：");

        Button btnMemoryAccessStart = new Button(compositeMemoryAllocation, SWT.NONE);
        btnMemoryAccessStart.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                replacementStrategy.setLength(0);
                replacementStrategy.append(comboPageReplacement.getText());
                if(replacementStrategy.toString().equals("FIFO")) {
                    os.tlb.strategy=ReplaceStrategy.FIFO;
                }else if(replacementStrategy.toString().equals("LRU")) {
                    os.tlb.strategy=ReplaceStrategy.LRU;
                }
            }
        });
        btnMemoryAccessStart.setBounds(1023, 197, 98, 30);
        btnMemoryAccessStart.setText("开始");

        Label lblAllocFrames = new Label(compositeMemoryAllocation, SWT.NONE);
        lblAllocFrames.setBounds(1004, 86, 117, 20);
        lblAllocFrames.setText("分配的物理块数");

        Label lblReplacement = new Label(compositeMemoryAllocation, SWT.NONE);
        lblReplacement.setBounds(513, 10, 76, 20);
        lblReplacement.setText("置换图：");

        tableReplacement = new Table(compositeMemoryAllocation, SWT.BORDER | SWT.FULL_SELECTION);
        tableReplacement.setBounds(513, 36, 115, 249);
        tableReplacement.setHeaderVisible(true);
        tableReplacement.setLinesVisible(true);

        TableColumn tblclmnReplacement = new TableColumn(tableReplacement, SWT.NONE);
        tblclmnReplacement.setWidth(100);
        tblclmnReplacement.setText("页面");
    }
}
