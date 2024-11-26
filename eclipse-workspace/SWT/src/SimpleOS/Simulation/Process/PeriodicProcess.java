package SimpleOS.Simulation.Process;

import SimpleOS.Simulation.OS;

public class PeriodicProcess extends Process {
	public PeriodicProcess(int id, int priority, int deadline, int task, int memoryNeed,int cycle,OS os) {
		super(id, priority, deadline, task, memoryNeed, os);
		this.cycle=cycle;
	}
	
	public int cycle;
}
