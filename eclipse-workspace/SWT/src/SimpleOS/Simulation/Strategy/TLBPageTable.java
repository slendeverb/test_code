package SimpleOS.Simulation.Strategy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

enum replaceFunc {
    FIFO,
    LRU
}

class TLB {
    private final Map<Integer, Integer> tlbEntries; // 页号到块号的映射（快表）
    private LinkedList<Integer> LRUQueue; // 跟踪页面访问顺序的队列（用于LRU）
    private LinkedList<Integer> FIFOQueue; // 跟踪页面添加顺序的队列（用于FIFO）
    private final int tlbSize; // 快表的大小
    private final replaceFunc func; // 页面置换算法

    public TLB(int tlbSize, replaceFunc policy) {
        this.tlbEntries = new HashMap<>(tlbSize);
        this.tlbSize = tlbSize;
        this.func = policy;
        if (policy == replaceFunc.FIFO) {
            this.FIFOQueue = new LinkedList<>();
        } else if (policy == replaceFunc.LRU) {
            this.LRUQueue = new LinkedList<>();
        }
    }

    public boolean addEntry(int pageNum, int blockNum) {
        if (tlbEntries.size() >= tlbSize) {
            // 快表已满，根据算法移除页面
            int oldestPageNum;
            if (func == replaceFunc.FIFO) {
                oldestPageNum = FIFOQueue.poll();
            } else if (func == replaceFunc.LRU) {
                oldestPageNum = LRUQueue.pollFirst();
            } else {
                throw new RuntimeException();
            }
            tlbEntries.remove(oldestPageNum);
        }

        tlbEntries.put(pageNum, blockNum);
        if (func == replaceFunc.FIFO) {
            FIFOQueue.add(pageNum);
        } else if (func == replaceFunc.LRU) {
            // 如果页面已经在访问顺序队列中，先移除它
            if (LRUQueue.contains(pageNum)) {
                LRUQueue.remove(Integer.valueOf(pageNum));
            }
            // 将页面添加到队列末尾
            LRUQueue.addLast(pageNum);
        }
        return true;
    }

    public Integer getBlockNum(int pageNumber) {
        if (func == replaceFunc.LRU && LRUQueue.contains(pageNumber)) {
            // 如果页面在LRU队列中，更新其位置到最近访问
            LRUQueue.remove(Integer.valueOf(pageNumber));
            LRUQueue.addLast(pageNumber);
        }
        return tlbEntries.get(pageNumber);
    }

    public void displayTLB() {
        System.out.print("快表中的页号为：");
        for (int pageNum : tlbEntries.keySet()) {
            System.out.print(pageNum + " ");
        }
        System.out.println();
    }
}

class TLBPageTable {
    private final Map<Integer, Integer> pageTable; // 页号到帧号的映射（页表）
    private final TLB tlb; // 快表
    private final int pageSize; // 页大小

    public TLBPageTable(int pageSize, int tlbSize, replaceFunc policy) {
        this.pageTable = new HashMap<>();
        this.tlb = new TLB(tlbSize, policy);
        this.pageSize = pageSize;
    }

    public void addPage(int pageNum, int blockNum) {
        pageTable.put(pageNum, blockNum);
        // 添加到快表中
        tlb.addEntry(pageNum, blockNum);
    }

    public int translateAddress(int logicalAddress) {
        int pageNum = logicalAddress / pageSize;
        int offset = logicalAddress % pageSize;

        Integer blockNum = tlb.getBlockNum(pageNum);
        if (blockNum == null) { // 快表中未找到
            blockNum = pageTable.get(pageNum);

            System.out.println("快表中无该页号");

            if (blockNum != null) {
                // 页表中找到了，但快表中未找到，添加到快表中
                tlb.addEntry(pageNum, blockNum);
            }
            else {
                // 页表和快表中都未找到，抛出异常
                throw new RuntimeException("页表中无该页号，越界中断");
            }
        }

        return blockNum * pageSize + offset; // 计算物理地址
    }
/**
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入页面置换算法（FIFO/LRU）：");
        String choose = sc.next();
        replaceFunc func = replaceFunc.valueOf(choose.toUpperCase());

        TLBPageTable tlbPageTable = new TLBPageTable(1024, 3, func);
        tlbPageTable.addPage(0, 2);
        tlbPageTable.addPage(1, 3);
        tlbPageTable.addPage(2, 4);
        tlbPageTable.addPage(3, 5);
        tlbPageTable.addPage(4, 6);

        while (true) {
            tlbPageTable.tlb.displayTLB();
            System.out.print("请输入逻辑地址：");
            int logicalAddress = sc.nextInt();
            try {
                int physicalAddress = tlbPageTable.translateAddress(logicalAddress);
                System.out.println("逻辑地址: " + logicalAddress + " -> 物理地址: " + physicalAddress);
                System.out.println();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
 **/

}