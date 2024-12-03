package SimpleOS.Simulation.Register;

import java.util.HashMap;

import SimpleOS.Simulation.Memory.Page;

public class TLB {
	public static int Size=16;
	public HashMap<Integer, Page> pages;
	
	public TLB() {
		pages=new HashMap<Integer, Page>();
	}
}
