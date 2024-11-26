package SimpleOS.Simulation.Process;

import SimpleOS.Simulation.OS;

public class Thread extends Process implements Runnable {
	public Thread(int id, int priority, int deadline, int task, int memoryNeed,OS os) {
		super(id, priority, deadline, task, memoryNeed, os);
	}

	@Override
	public void run() {
		
	}
}
