package SimpleOS.Simulation.Process;

import java.util.LinkedList;

import SimpleOS.Simulation.Strategy.Strategy;

public class Process {
	public Process(int id,int priority,int deadline,int task,int memoryNeed) {
		pcb=new PCB(id, priority, deadline, task, memoryNeed);
	}
	
	public void block(LinkedList<Process> blockList) {
		if(pcb.state!=State.RUNNING) {
			return;
		}
		pcb.state=State.BLOCKEDA;
		blockList.add(this);
	}
	
	public PCB pcb;
	protected Strategy strategy=null;
}
