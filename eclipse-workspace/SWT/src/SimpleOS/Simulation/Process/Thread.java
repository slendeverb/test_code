package SimpleOS.Simulation.Process;

import java.util.ArrayList;

import SimpleOS.Simulation.OS;

public class Thread extends Process {
	public Thread(int id,String name, int priority, int deadline, int task, int memoryNeed, ArrayList<Integer> sourceNeed, OS os) {
		super(id, name, priority, deadline, task, memoryNeed, sourceNeed, os);
	}
}
