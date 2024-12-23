package SimpleOS.Simulation.Process;

import java.util.ArrayList;

import SimpleOS.Simulation.OS;

public class PCB{
	public PCB(int id,String name,int priority,int deadline,int task,int memoryNeed,ArrayList<Integer> sourceNeed,OS os) {
		this.id=id;
		this.name=name;
		this.priority=priority;
		this.task=task;
		this.deadline=deadline;
		this.memoryNeed=memoryNeed;
		this.sourceNeed=sourceNeed;
	}
	
	public int id;
	public String name;
	public int priority;
	public int memoryNeed;
	public int arriveTime=0;
	public int task;
	public int deadline;
	public State state=State.CREATE;
	public int position=0;
	public ArrayList<Integer> sourceNeed;
}
