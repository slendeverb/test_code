package SimpleOS.Simulation.Memory;

import java.util.ArrayList;
import java.util.HashMap;
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
		allocedFrames=new HashMap<Integer, Frame>();
		for(Frame frame:frames) {
			if(frame.isAlloc) {
				allocedFrames.put(frame.id, frame);
				continue;
			}
			freeFrames.add(frame);
		}
	}
	
	public boolean hasFree() {
		return !freeFrames.isEmpty();
	}
	
	public int alloc() {
		Frame frame=freeFrames.poll();
		frame.isAlloc=true;
		allocedFrames.put(frame.id, frame);
		return frame.id;
	}
	
	public void recycle(int id) {
		Frame frame=allocedFrames.remove(id);
		frame.isAlloc=false;
		freeFrames.add(frame);
	}
	
	public ArrayList<Frame> frames;
	public PriorityQueue<Frame> freeFrames;
	public HashMap<Integer, Frame> allocedFrames;
}
