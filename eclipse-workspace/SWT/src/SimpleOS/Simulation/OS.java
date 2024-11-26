package SimpleOS.Simulation;
	
import java.util.LinkedList;
import java.util.PriorityQueue;

import SimpleOS.Simulation.Memory.Memory;
import SimpleOS.Simulation.Memory.Storage;
import SimpleOS.Simulation.Process.Process;
import SimpleOS.Simulation.Process.State;

public class OS {
	private OS() {}
	
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
	
	public void suspendR() {
		
	}
	
	public void suspendB() {
		
	}
	
	public void activeR() {
		
	}
	
	public void activeB() {
		
	}

	public long systemTime;
	public Memory memory;
	public Storage storage;
	public PriorityQueue<Process> readyQueueA;
	public PriorityQueue<Process> readyQueueS;
	public LinkedList<Process> blockQueueA;
	public LinkedList<Process> blockQueueS;
	public LinkedList<Process> suspendQueueR;
	public LinkedList<Process> suspendQueueB;
}
