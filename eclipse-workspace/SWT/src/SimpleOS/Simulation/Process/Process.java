package SimpleOS.Simulation.Process;

import java.util.LinkedList;

import SimpleOS.Simulation.OS;
import SimpleOS.Simulation.Memory.PageTable;

public class Process {
	public Process(int id,int priority,int deadline,int task,int memoryNeed,OS os) {
		pcb=new PCB(id, priority, deadline, task, memoryNeed);
		pageTable=new PageTable(memoryNeed,os);
	}
	
	public void block(LinkedList<Process> blockList) {
		if(pcb.state!=State.RUNNING) {
			return;
		}
		pcb.state=State.BLOCKEDA;
		blockList.add(this);
	}
	
	public PCB pcb;
	public PageTable pageTable;
}
