package SimpleOS.Simulation.Process;

import java.util.ArrayList;

import SimpleOS.Simulation.OS;

public class PeriodicProcess extends Process {
	public PeriodicProcess(int id,String name, int priority, int deadline, int task, int memoryNeed, ArrayList<Integer> sourceNeed, int cycle,OS os) {
		super(id, name, priority, deadline, task, memoryNeed, sourceNeed, os);
		this.cycle=cycle;
		this.threads=new ArrayList<Thread>();
	}
	
	public int cycle;
	public ArrayList<Thread> threads;
}
