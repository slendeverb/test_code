package SimpleOS.Simulation.Memory;

import java.util.ArrayList;
import SimpleOS.Simulation.OS;
import SimpleOS.Simulation.Register.TLB;

public class PageTable {
	public static int MinPageNum=6;
	public ArrayList<Page> pages;
	
	public PageTable(int memoryNeed,OS os) {
		int pageNumber=memoryNeed/Page.PageSize;
		pages=new ArrayList<Page>();
		Memory memory=os.memory;
		Storage storage=os.storage;
		for(int i=0;i<pageNumber;i++) {
			if(i<MinPageNum && memory.hasFree()) {
				int frameNumber=memory.alloc();
				Page page=new Page(i,frameNumber,true,0,false,-1);
				pages.add(page);
			}else {
				try {
					Integer storageAddress=storage.alloc(i, Page.PageSize);
					if(storageAddress==null) {
						throw new Exception("Swap Area is full.");
					}
					Page page=new Page(i, -1, false, 0, false, storageAddress);
					pages.add(page);
				} catch (Exception e) {}
			}
		}
		TLB tlb=os.tlb;
		int minSize=Math.min(pageNumber, TLB.Size);
		ArrayList<Page> tlbPages=tlb.pages;
		for(int i=0;i<minSize;i++) {
			tlbPages.add(pages.get(i));
		}
	}
}
