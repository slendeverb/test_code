package SimpleOS.Simulation.Process;

import java.util.Comparator;

public class LaxityProcessComparator implements Comparator<Process> {
	@Override
	public int compare(Process o1, Process o2) {
		return (o1.pcb.deadline-o1.pcb.task)-(o2.pcb.deadline-o2.pcb.task);
	}
}
