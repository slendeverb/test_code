package SimpleOS.Simulation.Process;

import java.util.Comparator;

public class DefaultProcessComparator implements Comparator<Process> {
	@Override
	public int compare(Process o1, Process o2) {
		return o1.pcb.id-o2.pcb.id;
	}
}
