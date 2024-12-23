package SimpleOS.Simulation.Memory;

public class Node{
	public int processId;
	public int size;
	public int startAddress;
	
	public Node(int processId,int size,int startAddress) {
		this.processId=processId;
		this.size=size;
		this.startAddress=startAddress;
	}
}