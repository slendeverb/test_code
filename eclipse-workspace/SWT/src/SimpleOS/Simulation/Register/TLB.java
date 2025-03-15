package SimpleOS.Simulation.Register;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import SimpleOS.Simulation.Memory.Memory;
import SimpleOS.Simulation.Memory.Page;
import SimpleOS.Simulation.Memory.PageTable;
import SimpleOS.Simulation.Memory.Storage;

public class TLB {
	public static int Size=8;
	public ArrayList<Page> pages;
	
	public LinkedList<Page> FIFOQueue;
	public LinkedList<Page> LRUQueue;
	
	public ReplaceStrategy strategy=ReplaceStrategy.FIFO;
	
	public Memory memory;
	public Storage storage;
	
	public TLB(Memory memory, Storage storage) {
		pages=new ArrayList<Page>();
		FIFOQueue=new LinkedList<Page>();
		LRUQueue=new LinkedList<Page>();
		this.memory=memory;
		this.storage=storage;
	}
	
	public void add(Page page) {
		if(pages.size()>=PageTable.MinPageNum) {
			Page removePage=null;
			if(strategy==ReplaceStrategy.FIFO) {
				removePage=FIFOQueue.pollFirst();
			}else if(strategy==ReplaceStrategy.LRU) {
				removePage=LRUQueue.pollFirst();
			}
			removePage.modified=false;
			removePage.accessField=0;
			pages.remove(removePage);
		}
		
		pages.add(page);
		if(strategy==ReplaceStrategy.FIFO) {
			FIFOQueue.add(page);
		}else if(strategy==ReplaceStrategy.LRU) {
			if(LRUQueue.contains(page)) {
				LRUQueue.remove(page);
			}
			LRUQueue.add(page);
		}
	}
	
	public Optional<Page> find(int pageNum,int processID) {
		for(int i=0;i<pages.size();i++) {
			Page page=pages.get(i);
			if(page.id==pageNum && page.processID==processID) {
				return Optional.of(page);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Page> find(Page page) {
		int pageNum=page.id;
		int processID=page.processID;
		return find(pageNum, processID);
	}
}
