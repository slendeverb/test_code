package SimpleOS.Simulation.Sync;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import SimpleOS.Simulation.OS;
import SimpleOS.Simulation.Source.Source;
import SimpleOS.Simulation.Process.Process;

public class Monitor {
	public Monitor(Source source) {
		this.source=source;
		this.maxSize=source.num;
		this.list=new LinkedList<>();
	}
	
	public boolean put(Object item,Process process,OS os) {
		if(list.size()==maxSize) {
			os.block(process,os.blockQueueAFull);
			return false;
		}
		list.add(item);
		if(!os.blockQueueAEmpty.isEmpty()) {
			os.wakeupA(os.blockQueueAEmpty.peek(), os.blockQueueAEmpty);
		}
		return true;
	}
	
	public boolean take(AtomicInteger item,Process process,OS os) {
		if(list.size()==0) {
			os.block(process,os.blockQueueAEmpty);
			return false;
		}
		item.set((int) list.removeFirst());;
		if(!os.blockQueueAFull.isEmpty()) {
			os.wakeupA(os.blockQueueAFull.peek(), os.blockQueueAFull);
		}
		return true;
	}
	
	public Source source;
	public int maxSize;
	public LinkedList<Object> list;
}
