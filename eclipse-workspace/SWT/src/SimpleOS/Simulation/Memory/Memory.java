package SimpleOS.Simulation.Memory;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Memory {
	public Memory(int memorySize) {
		frames=new ArrayList<Frame>(memorySize);
		for(int i=0;i<frames.size();i++) {
			Frame frame=frames.get(i);
			frame.id=i;
			frame.isAlloc=false;
			frame.length=Page.PageSize;
			frame.startAddress=frame.id*frame.length;
		}
		freeFrames=new PriorityQueue<Frame>(new SegmentComparator());
		for(Frame frame:frames) {
			if(frame.isAlloc) {
				continue;
			}
			freeFrames.add(frame);
		}
	}
	
	public ArrayList<Frame> frames;
	public PriorityQueue<Frame> freeFrames;
}
