package SimpleOS.Simulation.Process;

public class PCB{
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
