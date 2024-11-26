package SimpleOS.Simulation.Memory;

import java.util.Comparator;

public class SegmentComparator implements Comparator<Segment> {
	@Override
	public int compare(Segment o1, Segment o2) {
		return o1.id-o2.id;
	}
}
