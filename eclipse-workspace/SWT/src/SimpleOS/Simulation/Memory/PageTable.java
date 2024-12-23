package SimpleOS.Simulation.Memory;

import java.util.ArrayList;
import SimpleOS.Simulation.OS;
import SimpleOS.Simulation.Process.Process;
import SimpleOS.Simulation.Process.State;
import SimpleOS.Simulation.Register.TLB;

public class PageTable {
	public static int MinPageNum=6;
	public ArrayList<Page> pages;
	
	public PageTable(int memoryNeed,Process process,OS os) {
		int pageNumber=(int) Math.ceil((double)memoryNeed/Page.PageSize);
		pages=new ArrayList<Page>();
		Memory memory=os.memory;
		Storage storage=os.storage;
		boolean isActive=false;
		for(int i=0;i<pageNumber;i++) {
			if(i<MinPageNum && memory.hasFree()) {
				int frameNumber=memory.alloc(process.pcb.id);
				Page page=new Page(i,frameNumber,true,0,false,-1,process.pcb.id);
				pages.add(page);
				isActive=true;
			}else {
				try {
					Integer storageAddress=storage.alloc(i, Page.PageSize);
					if(storageAddress==null) {
						throw new Exception("Swap Area is full.");
					}
					Page page=new Page(i, -1, false, 0, false, storageAddress,process.pcb.id);
					pages.add(page);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}
			}
		}
		if(isActive || memory.hasFree()) {
			process.pcb.state=State.READYA;
			os.readyQueueA.add(process);
		}else {
			process.pcb.state=State.READYS;
			os.readyQueueS.add(process);
		}
		TLB tlb=os.tlb;
		int minSize=Math.min(MinPageNum,Math.min(TLB.Size, pageNumber));
		for(int i=0;i<minSize;i++) {
			Page page=pages.get(i);
			tlb.add(page);
		}
	}
}
