package SimpleOS.Simulation;

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

enum State{
	CREATE,
	READYA,
	READYS,
	RUNNING,
	BLOCKEDA,
	BLOCKEDS,
	TERMINATE
}

class PCB{
	public PCB(int id,int priority,int deadline,int task,int memoryNeed) {
		this.id=id;
		this.priority=priority;
		this.task=task;
		this.deadline=deadline;
		this.memoryNeed=memoryNeed;
	}
	
	public int id=1;
	public int priority=1;
	public int memoryNeed;
	public int arriveTime=0;
	public int task;
	public int deadline;
	public State state=State.CREATE;
}
