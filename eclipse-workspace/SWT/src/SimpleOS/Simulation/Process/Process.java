package SimpleOS.Simulation.Process;

import java.util.ArrayList;
import java.util.PriorityQueue;

import SimpleOS.Simulation.OS;
import SimpleOS.Simulation.Memory.PageTable;

public class Process {
	public Process(int id,String name,int priority,int deadline,int task,int memoryNeed,ArrayList<Integer> sourceNeed,OS os) {
		pcb=new PCB(id, name, priority, deadline, task, memoryNeed, sourceNeed, os);
		pageTable=new PageTable(memoryNeed,this,os);
	}
	
	public void block(PriorityQueue<Process> blockList) {
		pcb.state=State.BLOCKEDA;
		if(!blockList.contains(this)) {
			blockList.add(this);
		}
	}
	
	public PCB pcb;
	public PageTable pageTable;
}
