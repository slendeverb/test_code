package SimpleOS.Simulation.Memory;

import java.util.ArrayList;

public class SwapArea extends StorageArea {
	public SwapArea(int startAddress,int size) {
		super(startAddress, size);
		frames=new ArrayList<Frame>(size/Page.PageSize);
	}
	
	public ArrayList<Frame> frames;
}
