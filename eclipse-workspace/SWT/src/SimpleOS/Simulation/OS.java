package SimpleOS.Simulation;
	
import java.util.ArrayList;
import java.util.PriorityQueue;

import SimpleOS.Simulation.Memory.Memory;
import SimpleOS.Simulation.Memory.Page;
import SimpleOS.Simulation.Memory.PageTable;
import SimpleOS.Simulation.Memory.Storage;
import SimpleOS.Simulation.Process.DefaultProcessComparator;
import SimpleOS.Simulation.Process.PCB;
import SimpleOS.Simulation.Process.PeriodicProcess;
import SimpleOS.Simulation.Process.Process;
import SimpleOS.Simulation.Process.State;
import SimpleOS.Simulation.Register.PTR;
import SimpleOS.Simulation.Register.TLB;
import SimpleOS.Simulation.Source.Source;
import SimpleOS.Simulation.Sync.Monitor;

public class OS {
	private OS() {
		this.systemTime=(long) 0;
		this.processID=0;
		this.ptr=new PTR();
		this.memory=new Memory(16*Page.PageSize);
		this.storage=new Storage(16*Page.PageSize,16*Page.PageSize);
		this.tlb=new TLB(memory,storage);
		this.monitor=new Monitor(new Source(16, "消息队列"));
		this.sources=new ArrayList<Source>();
		this.sources.add(new Source(10, "A"));
		this.sources.add(new Source(5, "B"));
		this.sources.add(new Source(7, "C"));
		this.sourceAvailable=new ArrayList<Integer>(sources.size());
		for(int i=0;i<this.sourceAvailable.size();i++) {
			this.sourceAvailable.set(i, this.sources.get(i).num);
		}
		this.periodicProcesses=new ArrayList<PeriodicProcess>();
		this.readyQueueA=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.readyQueueS=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueA=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueAEmpty=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueAFull=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueS=new PriorityQueue<Process>(new DefaultProcessComparator());
	}
	
	private OS(OS os) {}
	
	public static OS getInstance() {
		return Singleton.INSTANCE.getInstance();
	}
	
	private enum Singleton{
		INSTANCE;
		private OS instance;
		Singleton() {
			instance=new OS();
		}
		public OS getInstance() {
			return instance;
		}
	}
	
	public String start() {
		return "Operating System Running...";
	}
	
	public String stop() {
		return "Operating System Stopping...";
	}
	
	public void reset() {
		this.systemTime=(long) 0;
		this.processID=0;
		this.ptr=new PTR();
		this.memory=new Memory(16*Page.PageSize);
		this.storage=new Storage(16*Page.PageSize,16*Page.PageSize);
		this.tlb=new TLB(memory,storage);
		this.monitor=new Monitor(new Source(16, "消息队列"));
		this.sources=new ArrayList<Source>();
		this.sources.add(new Source(10, "A"));
		this.sources.add(new Source(5, "B"));
		this.sources.add(new Source(7, "C"));
		this.sourceAvailable=new ArrayList<Integer>(sources.size());
		for(int i=0;i<this.sourceAvailable.size();i++) {
			this.sourceAvailable.set(i, this.sources.get(i).num);
		}
		this.periodicProcesses=new ArrayList<PeriodicProcess>();
		this.readyQueueA=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.readyQueueS=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueA=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueAEmpty=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueAFull=new PriorityQueue<Process>(new DefaultProcessComparator());
		this.blockQueueS=new PriorityQueue<Process>(new DefaultProcessComparator());
	}
	
	public void block(Process process,PriorityQueue<Process> blockQueue) {
		process.block(blockQueue);
		readyQueueA.remove(process);
	}
	
	public void wakeupA(Process process, PriorityQueue<Process> blockQueue) {
		if(!blockQueue.contains(process)) {
			return;
		}
		process.pcb.state=State.READYA;
		blockQueue.remove(process);
		if(!readyQueueA.contains(process)) {
			readyQueueA.add(process);
		}
	}
	
	public void wakeupS(Process process, PriorityQueue<Process> blockQueue){
		if(!blockQueue.contains(process)) {
			return;
		}
		process.pcb.state=State.READYS;
		blockQueue.remove(process);
		if(!readyQueueS.contains(process)) {
			readyQueueS.add(process);
		}
	}
	
	public void suspendR(Process process) {
		if(!readyQueueA.contains(process)) {
			return;
		}
		process.pcb.state=State.READYS;
		readyQueueA.remove(process);
		if(!readyQueueS.contains(process)) {
			readyQueueS.add(process);
		}
		suspend(process);
	}
	
	public void suspendB(Process process) {
		if(!blockQueueA.contains(process)) {
			return;
		}
		process.pcb.state=State.BLOCKEDS;
		blockQueueA.remove(process);
		if(!blockQueueS.contains(process)) {
			blockQueueS.add(process);
		}
		suspend(process);
	}
	
	public void activeR(Process process) {
		if(!readyQueueS.contains(process)) {
			return;
		}
		process.pcb.state=State.READYA;
		readyQueueS.remove(process);
		if(!readyQueueA.contains(process)) {
			readyQueueA.add(process);
		}
		active(process);
	}
	
	public void activeB(Process process) {
		if(!blockQueueS.contains(process)) {
			return;
		}
		process.pcb.state=State.BLOCKEDA;
		blockQueueS.remove(process);
		if(!blockQueueA.contains(process)) {
			blockQueueA.add(process);
		}
		active(process);
	}
	
	private void suspend(Process process) {
		PCB pcb=process.pcb;
		PageTable pageTable=process.pageTable;
		ArrayList<Page> pages=pageTable.pages;
		for(int i=0;i<pages.size();i++) {
			Page page=pages.get(i);
			if(!page.inMemory) {
				continue;
			}
			try {
				Integer storageAddress=storage.alloc(pcb.id, Page.PageSize);
				if(storageAddress==null) {
					throw new Exception("Swap Area is full.");
				}
				memory.recycle(page.frameNumber);
				page.inMemory=false;
				page.frameNumber=-1;
				page.storageAddress=storageAddress;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}
	}
	
	private void active(Process process) {
		PCB pcb=process.pcb;
		PageTable pageTable=process.pageTable;
		ArrayList<Page> pages=pageTable.pages;
		Page page=null;
		for(int i=0;i<PageTable.MinPageNum;i++) {
			if(!memory.hasFree()) {
				break;
			}
			int idx=i+pcb.position;
			if(idx>=pages.size()) {
				break;
			}
			page=pages.get(idx);
			int frameNumber=memory.alloc(pcb.id);
			page.inMemory=true;
			page.frameNumber=frameNumber;
			page.storageAddress=-1;
		}
	}

	public Long systemTime;
	public Integer processID;
	public PTR ptr;
	public TLB tlb;
	public Memory memory;
	public Storage storage;
	public Monitor monitor;
	public ArrayList<Source> sources;
	public ArrayList<Integer> sourceAvailable;
	public ArrayList<PeriodicProcess> periodicProcesses;
	public PriorityQueue<Process> readyQueueA;
	public PriorityQueue<Process> readyQueueS;
	public PriorityQueue<Process> blockQueueA;
	public PriorityQueue<Process> blockQueueAEmpty;
	public PriorityQueue<Process> blockQueueAFull;
	public PriorityQueue<Process> blockQueueS;
}
