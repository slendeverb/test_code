package SimpleOS.Simulation;
	
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import SimpleOS.Simulation.Memory.Memory;
import SimpleOS.Simulation.Memory.Page;
import SimpleOS.Simulation.Memory.PageTable;
import SimpleOS.Simulation.Memory.Storage;
import SimpleOS.Simulation.Process.PCB;
import SimpleOS.Simulation.Process.Process;
import SimpleOS.Simulation.Process.State;
import SimpleOS.Simulation.Register.PTR;
import SimpleOS.Simulation.Register.TLB;

public class OS {
	private OS() {
		this.systemTime=0;
		this.memory=new Memory(4096*Page.PageSize);
		this.storage=new Storage(4096*Page.PageSize, 16*1024*Page.PageSize);
		this.readyQueueA=new PriorityQueue<Process>();
		this.readyQueueS=new PriorityQueue<Process>();
		this.blockQueueA=new LinkedList<Process>();
		this.blockQueueS=new LinkedList<Process>();
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
	
	public void block(Process process) {
		process.block(blockQueueA);
	}
	
	public void wakeupA() {
		if(blockQueueA.isEmpty()) {
			return;
		}
		Process process=blockQueueA.removeFirst();
		process.pcb.state=State.READYA;
		readyQueueA.add(process);
	}
	
	public void wakeupS(){
		if(blockQueueS.isEmpty()) {
			return;
		}
		Process process=blockQueueS.removeFirst();
		process.pcb.state=State.READYS;
		readyQueueS.add(process);
	}
	
	public void suspendR(Process process) {
		if(!readyQueueA.contains(process)) {
			return;
		}
		process.pcb.state=State.READYS;
		readyQueueA.remove(process);
		readyQueueS.add(process);
		suspend(process);
	}
	
	public void suspendB(Process process) {
		if(!blockQueueA.contains(process)) {
			return;
		}
		process.pcb.state=State.BLOCKEDS;
		blockQueueA.remove(process);
		blockQueueS.add(process);
		suspend(process);
	}
	
	public void activeR() {
		
	}
	
	public void activeB() {
		
	}
	
	private void suspend(Process process) {
		PCB pcb=process.pcb;
		PageTable pageTable=process.pageTable;
		ArrayList<Page> pages=pageTable.pages;
		for(int i=0;i<pages.size();i++) {
			Page page=pages.get(i);
			if(page.frameNumber==-1) {
				continue;
			}
			try {
				Integer storageAddress=storage.alloc(pcb.id, Page.PageSize);
				if(storageAddress==null) {
					throw new Exception("Swap Area is full.");
				}
				memory.recycle(page.frameNumber);
				page.frameNumber=-1;
				page.storageAddress=storageAddress;
			} catch (Exception e) {}
		}
	}

	public long systemTime;
	public PTR ptr;
	public TLB tlb;
	public Memory memory;
	public Storage storage;
	public PriorityQueue<Process> readyQueueA;
	public PriorityQueue<Process> readyQueueS;
	public LinkedList<Process> blockQueueA;
	public LinkedList<Process> blockQueueS;
}
