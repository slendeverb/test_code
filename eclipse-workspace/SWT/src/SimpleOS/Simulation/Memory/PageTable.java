package SimpleOS.Simulation.Memory;

import java.util.ArrayList;
import java.util.PriorityQueue;

import SimpleOS.Simulation.OS;

public class PageTable {
	public ArrayList<Page> pages;
	
	public PageTable(int memoryNeed,OS os) {
		int pageNumber=memoryNeed/Page.PageSize;
		pages=new ArrayList<Page>();
		PriorityQueue<Frame> freeFrames=os.memory.freeFrames;
		for(int i=0;i<pageNumber;i++) {
			if(!freeFrames.isEmpty()) {
				Frame frame=freeFrames.poll();
				frame.isAlloc=true;
				Page page=new Page(i,frame.id,true,0,false,-1);
				pages.add(page);
			}else {
				
			}
		}
	}
}
