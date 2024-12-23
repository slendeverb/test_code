package SimpleOS.Simulation.Strategy;

public class ProcessScheduler {
	public EDFScheduler EDF;
	public LLFScheduler LLF;
	
	public ProcessScheduler(int maxTime) {
		this.EDF=new EDFScheduler(maxTime);
		this.LLF=new LLFScheduler(maxTime);
	}

	public void schedulProcess(int choice) {
		switch (choice) {
			case 0: {
				EDF.runScheduler();
				break;
			}
			case 1: {
				LLF.runScheduler();
				break;
			}
		}
	}
	
	public void addTask(int choice,int period, int cputime) {
		switch (choice) {
			case 0: {
				EDF.canRun=false;
				break;
			}
			case 1: {
				LLF.canRun=false;
				break;
			}
		}
	}
}
