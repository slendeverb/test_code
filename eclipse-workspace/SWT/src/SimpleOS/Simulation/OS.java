package SimpleOS.Simulation;
	
import java.util.LinkedList;
import java.util.PriorityQueue;

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
	
	public void block(Process process,LinkedList<Process> blockQueueA) {
		process.block(blockQueueA);
	}
	
	public void wakeupA(LinkedList<Process> blockQueueA) {
		if(blockQueueA.isEmpty()) {
			return;
		}
		Process process=blockQueueA.removeFirst();
		process.pcb.state=State.READYA;
		readyQueueA.add(process);
	}
	
	public void wakeupS(LinkedList<Process> blockQueueS){
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

	private long systemTime=0;
	private PriorityQueue<Process> readyQueueA=new PriorityQueue<Process>();
	private PriorityQueue<Process> readyQueueS=new PriorityQueue<Process>();
	private LinkedList<Process> blockQueueA=new LinkedList<Process>();
	private LinkedList<Process> blockQueueS=new LinkedList<Process>();
	private LinkedList<Process> suspendQueueR=new LinkedList<Process>();
	private LinkedList<Process> suspendQueueB=new LinkedList<Process>();
}
