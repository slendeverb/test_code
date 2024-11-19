package SimpleOS.Simulation;

public class PeriodicProcess extends Process {
	public PeriodicProcess(int id, int priority, int deadline, int task, int memoryNeed,int cycle) {
		super(id, priority, deadline, task, memoryNeed);
		this.cycle=cycle;
	}
	
	public int cycle;
}
