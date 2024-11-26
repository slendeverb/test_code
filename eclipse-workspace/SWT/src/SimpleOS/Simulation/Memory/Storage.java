package SimpleOS.Simulation.Memory;

public class Storage {
	public SwapArea swapArea;
	public FileArea fileArea;
	
	public Storage(int swapAreaSize,int fileAreaSize) {
		swapArea=new SwapArea(0,swapAreaSize);
		fileArea=new FileArea(swapAreaSize,fileAreaSize);
	}
}
