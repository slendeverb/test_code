package SimpleOS.Simulation.Strategy;  
  
import java.util.ArrayList;  
import java.util.List;  
  
public class LLFScheduler {
    private int ENDTIME;
    public int nowtime = 0;
    public boolean canRun=true;
    private List<TCB> tcbList = new ArrayList<>();
    
    public LLFScheduler(int maxTime) {
		this.ENDTIME=maxTime;
	}

    // tcb类  
    private static class TCB {
        int period;
        int num; // 周期序号（已完成周期的数量）  
        int cputime; // CPU时间需求  
        int remain; // 剩余CPU时间  
        int ddl; // 截止时间  
        int laxity; // 松弛度  

        TCB(int period, int cputime) {
            this.period = period;
            this.cputime = cputime;
            this.num = 0;
            this.remain = cputime;
            this.ddl = this.period;
            this.laxity = this.ddl - this.remain;
        }
    }

    // 添加任务到调度器  
    public void addTask(int period, int cputime) {
        tcbList.add(new TCB(period, cputime));
    }

    // 更新任务的DDL和松弛度  
    private void updateLaxity(TCB tcb) {
        tcb.ddl = tcb.num * tcb.period + tcb.period;
        tcb.laxity = tcb.ddl - tcb.remain;
    }

    // 打印任务信息  
    private void printTaskInfo(TCB chosen, int runtime) {
        System.out.printf("任务号=%-3d 周期序号 = %-3d 调度时刻 = %-4d 运行时间长度 = %-3d%n",
                tcbList.indexOf(chosen) + 1, chosen.num + 1, nowtime, runtime);
    }

    // LLF算法主体  
    private void LLF() {
        TCB chosen = null;
        int minLaxity = Integer.MAX_VALUE;

        for (TCB tcb : tcbList) {
            if (tcb.remain > 0) {
                if (tcb.ddl < nowtime)
                    tcb.num++;
                updateLaxity(tcb);
                if (tcb.laxity < minLaxity) {
                    minLaxity = tcb.laxity;
                    chosen = tcb;
                }
            }
        }

        if (chosen != null) {
            int runtime = Math.min(chosen.remain, ENDTIME - nowtime);
            chosen.remain -= runtime;

            printTaskInfo(chosen, runtime);  //打印

            nowtime += runtime;

            if (chosen.remain == 0) {
                chosen.num++;
                chosen.remain = chosen.cputime; // 重置剩余时间为初始CPU时间需求  
            }
        }
    }

    // 运行调度器  
    public void runScheduler() {
        while (nowtime < ENDTIME && canRun) {
            LLF();
        }
    }

//    public static void main(String[] args) {  
//        LLFScheduler scheduler = new LLFScheduler(100);  
//          
//        // 添加任务到调度器  
//        scheduler.addTask(20, 10);  
//        scheduler.addTask(50, 25);
//        // 可以添加更多任务...  
//          
//        // 运行调度器  
//        scheduler.runScheduler();  
//    }
}