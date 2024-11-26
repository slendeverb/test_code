package SimpleOS.Simulation.Memory;

public class Page {
	public static int PageSize=1024;
	
	public int id;
	public int frameNumber;
	public boolean inMemory;
	public int accessField;
	public boolean modified;
	public int storageNumber;
	
	public Page(int id,int frameNumber,boolean inMemory,int accessField,boolean modified,int storageNumber) {
		this.id=id;
		this.frameNumber=frameNumber;
		this.inMemory=inMemory;
		this.accessField=accessField;
		this.modified=modified;
		this.storageNumber=storageNumber;
	}
}
