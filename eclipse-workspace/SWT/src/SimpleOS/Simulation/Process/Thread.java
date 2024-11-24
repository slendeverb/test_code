package SimpleOS.Simulation.Process;

public class Thread extends Process implements Runnable {
	public Thread(int id, int priority, int deadline, int task, int memoryNeed) {
		super(id, priority, deadline, task, memoryNeed);
	}

	@Override
	public void run() {
		
	}
}
