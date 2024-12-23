package SimpleOS.Simulation.Memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Memory {
	public Memory(int memorySize) {
		frames=new ArrayList<Frame>();
		Integer num=memorySize/Page.PageSize;
		for(int i=0;i<num;i++) {
			Frame frame=new Frame();
			frame.id=i;
			frame.isAlloc=false;
			frame.length=Page.PageSize;
			frame.startAddress=i>0?frames.get(i-1).startAddress+frames.get(i-1).length:0;
			frame.processID=-1;
			frames.add(frame);
		}
		freeFrames=new PriorityQueue<Frame>(new FrameComparator());
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
	
	public int alloc(int processID) {
		Frame frame=freeFrames.poll();
		frame.isAlloc=true;
		frame.processID=processID;
		allocedFrames.put(frame.id, frame);
		return frame.id;
	}
	
	public void recycle(int id) {
		Frame frame=allocedFrames.remove(id);
		frame.isAlloc=false;
		frame.processID=-1;
		freeFrames.add(frame);
	}
	
	public ArrayList<Frame> frames;
	public PriorityQueue<Frame> freeFrames;
	public HashMap<Integer, Frame> allocedFrames;
}
