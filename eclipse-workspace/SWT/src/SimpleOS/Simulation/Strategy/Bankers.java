package SimpleOS.Simulation.Strategy;

import java.util.Scanner;

public class Bankers {
        static int processNum; // 进程数
        static int resourceNum; // 资源种类

        static int num = 1; // 需要分配资源的进程序号

        public static void initialization(){
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入进程数:");
            processNum=sc.nextInt();
            System.out.print("请输入资源种类数:");
            resourceNum=sc.nextInt();
            System.out.println();
            sc.close();
        }

        public static void initRequest(int[] request) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入请求进程的序号:");
            num = sc.nextInt();
            System.out.println("请输入请求的各资源数量:");
            for (int i = 0; i < resourceNum; i++) {
                request[i] = sc.nextInt();
            }
            sc.close();
        }

        public static void initMatrix(int[][] allocation, int[][] need, int[] available) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入Allocation矩阵:");
            for (int i = 0; i < processNum; i++) {
                for (int j = 0; j < resourceNum; j++) {
                    allocation[i][j] = sc.nextInt();
                }
            }
            System.out.println("请输入Need矩阵:");
            for (int i = 0; i < processNum; i++) {
                for (int j = 0; j < resourceNum; j++) {
                    need[i][j] = sc.nextInt();
                }
            }

            System.out.println("请输入可利用的各资源数:");
            for (int i = 0; i < resourceNum; i++) {
                available[i] = sc.nextInt();
            }
            sc.close();
        }

        public static void display(int[][] allocation, int[][] need, int[] available) {
            System.out.println("\n     " + "Allocation" + "     Need" + "        Available");
            for (int i = 0; i < processNum; i++) {
                System.out.print("P" + i + ":");

                for (int j = 0; j < resourceNum; j++) {
                    System.out.print(allocation[i][j] + " ");
                }
                System.out.print("     ");
                for (int j = 0; j < resourceNum; j++) {
                    System.out.print(need[i][j] + " ");
                }
                if (i == 0) {
                    System.out.print("     ");
                    for (int k = 0; k < resourceNum; k++) {
                        System.out.print(available[k] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        public static boolean compare(int[] need, int[] work) {
            for (int i = 0; i < resourceNum; i++) {
                if (need[i] > work[i])
                    return false;
            }
            return true;
        }

        //安全性检查
        public static boolean checkSecurity(int[][] allocation, int[][] need, int[] available) {
            int[] finish = new int[processNum]; // 初始化finish向量
            int[] work = new int[resourceNum]; // 拷贝available
            int[] lis = new int[processNum]; // 用来记录安全时的队列
            int cnt = 0;
            for (int i = 0; i < resourceNum; i++) {
                work[i] = available[i]; // 初始化work向量
            }
            // 序列分配
            // 循环p次
            for (int m = 0; m < processNum; m++) {
                for (int i = 0; i < processNum; i++) {
                    // 如果当前进程执行完成，跳过
                    if (finish[i] == 1) {
                        continue;
                    }
                    // 如果Need[i,j]<=Work[j]
                    if (compare(need[i], work)) {
                        for (int j = 0; j < resourceNum; j++)
                            work[j] += allocation[i][j];
                        finish[i] = 1;
                        lis[cnt++] = i; // 将该状态放入安全状态队列中
                        break;
                    }
                }
            }
            boolean flag = true;
            for (int i = 0; i < processNum; i++) {
                if (finish[i] == 0) {
                    flag = false;
                    break; // 如果存在F的进程，表明系统处于不安全状态
                }
            }
            if (flag) {
                System.out.println("系统处于安全状态！");
                System.out.print("安全序列为:");
                for (int i = 0; i < processNum; i++) {
                    System.out.print(lis[i] + " ");
                }
                System.out.println();
            } else {
                System.out.println("系统处于不安全状态！");
            }
            return flag;
        }

        //算法主体
        public static void banker(int[][] allocation, int[][] need, int[] available, int[] request, int n) {
            if (!compare(request, need[n])) {
                // 如果存在Request[i][j]>Need[i][j],认为出错
                System.out.println("出错！所需资源已超过所宣布的最大值！");
                return;
            } else {
                // 银行家算法(1)没有出错
                if (!compare(request, available)) {
                    // 如果存在Request[i][j]>Available[j]，认为出错
                    System.out.println("尚无足够资源，必须等待！");
                    return;
                } else {
                    for (int j = 0; j < resourceNum; j++) {
                        available[j] -= request[j];
                        allocation[n][j] += request[j];
                        need[n][j] -= request[j];
                    }
                    if (checkSecurity(allocation, need, available)) {
                        System.out.println("安全！将资源正式分配");
                    } else {
                        System.out.println("不安全！资源分配作废！恢复以前状态");
                        for (int j = 0; j < resourceNum; j++) {
                            need[n][j] += request[j];
                            allocation[n][j] -= request[j];
                            available[j] += request[j];
                        }
                    }
                }
            }
            display(allocation, need, available);
        }

/**
        public static void main(String[] args) {
            initialization();
            int[][] allocation = new int[processNum][resourceNum];
            int[][] need = new int[processNum][resourceNum];
            int[] available = new int[resourceNum];
            int[] request = new int[resourceNum];
            initMatrix(allocation, need, available);
            System.out.println("\n检查T0时刻系统是否处于安全状态...");
            checkSecurity(allocation, need, available);
            int flag = 1;
            Scanner sc = new Scanner(System.in);
            while (flag != 0) {
                System.out.println("\n对请求资源进行银行家算法检查...");
                initRequest(request); // 初始化request矩阵
                banker(allocation, need, available, request, num);
                System.out.print("是否继续输入？(输入0退出):");
                flag = sc.nextInt();
            }
            sc.close();
        }
/*
 5 4

0 0 3 2
1 0 0 0
1 3 5 4
0 3 3 2
0 0 1 4

0 0 1 2
1 7 5 0
2 3 5 6
0 6 5 2
1 6 5 6

1 6 2 2

**/



}
