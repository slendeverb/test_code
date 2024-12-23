package SimpleOS.Simulation.Memory;

import java.util.Comparator;

public class FrameComparator implements Comparator<Frame> {
	@Override
	public int compare(Frame o1, Frame o2) {
		return o1.id-o2.id;
	}
}
