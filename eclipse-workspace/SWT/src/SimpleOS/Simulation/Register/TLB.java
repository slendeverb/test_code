package SimpleOS.Simulation.Register;

import java.util.ArrayList;

import SimpleOS.Simulation.Memory.Page;

public class TLB {
	public static int Size=16;
	public ArrayList<Page> pages;
	
	public TLB() {
		pages=new ArrayList<Page>(Size);
	}
}
