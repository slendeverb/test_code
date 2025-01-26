import java.util.Scanner;
import java.util.Stack;

public class Test {
    private static String Str = null; // 输入串
    private static String Sub = null; //输入串的待处理部分
    private static boolean acc = false;// 是否已处理完输入串
    private static boolean bResult = false;// 是否出错

    // Goto表，即每个状态遇见非终结符时的动作
    private static int[][] Goto = new int[][]{{1, 2, 3}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {8, 2, 3},
            {0, 0, 0}, {0, 9, 3}, {0, 0, 10}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

    private static Stack<String> stack = new Stack<String>();// 状态栈，栈中元素都是String类型

    /**
     * @param state 状态
     * @param fzjf  遇见的非终结符
     * fzjf == 'E'代表是Goto表的第一列
     * fzjf == 'T'代表是Goto表的第二列
     * fzjf == 'F'代表是Goto表的第三列
     */
    public static void Gto(int state, char fzjf) {
        int i = -1;
        if (fzjf == 'E')
            i = 0;
        if (fzjf == 'T')
            i = 1;
        if (fzjf == 'F')
            i = 2;

        stack.push(fzjf + ""); //将非终结符压入栈中，字符+空串转化为字符串
        stack.push(Goto[state][i] + "");//将Goto表中对应的状态压入栈中
    }

    /**
     * e1代表缺少运算对象 将“id”压入栈中，随即将相应的状态压入栈中
     */
    public static void e1() {
        System.out.println("缺少运算对象");
        stack.push("id");
        stack.push(String.valueOf(3));
        //  bResult = true;
        //若该行不注释掉，程序遇到输入错误就会立即停止 ；若注释掉，程序不会立即停止，而是会恢复错误
    }

    public static void e3() {
        System.out.println("缺少算符");
        stack.push("+");
        stack.push("" + 6);
        //  bResult = true;
    }

    public static void e3_() {
        System.out.println("缺少算符");
        stack.push("*");
        stack.push("" + 7);
        //  bResult = true;
    }

    public static void e2() {
        System.out.println("不匹配的右括号");
        Sub = Sub.substring(1, Sub.length());
        //  bResult = true;
    }

    public static void e4() {
        System.out.println("缺少右括号");
        stack.push("");
        stack.push(11 + "");
        //  bResult = true;
    }

    public static void e5() {
        System.out.println("算符错误");
        stack.push("+");
        stack.push(6 + "");
        Sub = Sub.substring(1, Sub.length());
        //  bResult = true;
    }

    public static void main(String[] args) {
        System.out.println("请输入串：");
        Scanner in = new Scanner(System.in);
        Str = in.nextLine();
        Sub = Str;   //初始时Sub = Str
        in.close();

        int y;
        boolean flag = false;

        // 0状态先进栈
        stack.push(String.valueOf(0));
        while (!bResult && acc == false) {
            switch (stack.peek().charAt(0)) {
                case '0':
                    if (Sub.substring(0, 2).equals("id")) {
                        stack.push("id");
                        stack.push("" + 5);
                        Sub = Sub.substring(2, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals("(")) {
                        stack.push("(");
                        stack.push("" + 4);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else {
                        e1();
                    }
                    break;
                case '1':
                    if ("1".equals(stack.peek())) {
                        if (Sub.substring(0, 1).equals("+")) {
                            stack.push("id");
                            stack.push("" + 6);
                            Sub = Sub.substring(1, Sub.length());
                            System.out.println("移进");
                        } else if (Sub.substring(0, 1).equals("#")) {
                            acc = true;
                            System.out.println("接受");
                        } else {
                            e3();
                        }
                        break;
                    } else if (stack.peek().charAt(1) == '0') {
                        if (Sub.substring(0, 1).equals("+")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                            System.out.println("按T->T*F归约");
                        } else if (Sub.substring(0, 1).equals(")")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                            System.out.println("按T->T*F归约");
                        } else if (Sub.substring(0, 1).equals("#")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                            System.out.println("按T->T*F归约");
                        } else if (Sub.substring(0, 1).equals("*")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                            System.out.println("按T->T*F归约");
                        } else {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                            System.out.println("按T->T*F归约");
                        }
                        break;
                    } else {
                        if (Sub.substring(0, 1).equals("+")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                            System.out.println("按F->(E)归约");
                        } else if (Sub.substring(0, 1).equals(")")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                            System.out.println("按F->(E)归约");
                        } else if (Sub.substring(0, 1).equals("#")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                            System.out.println("按F->(E)归约");
                        } else if (Sub.substring(0, 1).equals("*")) {
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            stack.pop();
                            Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                            System.out.println("按F->(E)归约");
                        }
                        break;
                    }
                case '2':
                    if (Sub.substring(0, 1).equals("*")) {
                        stack.push("*");
                        stack.push("" + 7);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals("+")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'E');
                        System.out.println("按E->T归约");
                    } else if (Sub.substring(0, 1).equals(")")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'E');
                        System.out.println("按E->T归约");
                    } else if (Sub.substring(0, 1).equals("#")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'E');
                        System.out.println("按E->T归约");
                    } else {
                        e3_();
                    }
                    break;
                case '3':
                    if (Sub.substring(0, 1).equals("+")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                        System.out.println("按T->F归约");
                    } else if (Sub.substring(0, 1).equals(")")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                        System.out.println("按T->F归约");
                    } else if (Sub.substring(0, 1).equals("#")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                        System.out.println("按T->F归约");
                    } else if (Sub.substring(0, 1).equals("*")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                        System.out.println("按T->F归约");
                    } else {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'T');
                        System.out.println("按T->F归约");
                    }
                    break;
                case '4':
                    if (Sub.substring(0, 2).equals("id")) {
                        stack.push("id");
                        stack.push("" + 5);
                        Sub = Sub.substring(2, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 2).equals("(")) {
                        stack.push("(");
                        stack.push("" + 4);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals(")")) {
                        e2();
                    } else
                        e1();
                    break;
                case '5':
                    if (Sub.substring(0, 1).equals("+")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                        System.out.println("按F->id归约");
                    } else if (Sub.substring(0, 1).equals(")")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                        System.out.println("按F->id归约");
                    } else if (Sub.substring(0, 1).equals("#")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                        System.out.println("按F->id归约");
                    } else if (Sub.substring(0, 1).equals("*")) {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                        System.out.println("按F->id归约");
                    } else {
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'F');
                        System.out.println("按F->id归约");
                    }
                    break;
                case '6':
                    if (Sub.length() > 1) {
                        if (Sub.substring(0, 1).equals("(")) {
                            stack.push("(");
                            stack.push("" + 4);
                            Sub = Sub.substring(1, Sub.length());
                            System.out.println("移进");
                        } else if (Sub.substring(0, 2).equals("id")) {
                            stack.push("id");
                            stack.push("" + 5);
                            Sub = Sub.substring(2, Sub.length());
                            System.out.println("移进");
                        } else if (Sub.substring(0, 1).equals(")")) {
                            e2();
                        }
                    } else
                        e1();
                    break;
                case '7':
                    if (Sub.substring(0, 2).equals("id")) {
                        stack.push("id");
                        stack.push("" + 5);
                        Sub = Sub.substring(2, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals("(")) {
                        stack.push("(");
                        stack.push("" + 4);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals("(")) {
                        stack.push("(");
                        stack.push("" + 4);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals(")")) {
                        e2();
                    } else
                        e1();
                    break;
                case '8':
                    if (Sub.substring(0, 1).equals("+")) {
                        stack.push("+");
                        stack.push("" + 6);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals(")")) {
                        stack.push(")");
                        stack.push("" + 11);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals("#")) {
                        e4();
                    } else if (Sub.substring(0, 1).equals("*")) {
                        e5();
                    } else
                        e3();
                    break;
                case '9':
                    if (Sub.substring(0, 1).equals("*")) {
                        stack.push("*");
                        stack.push("" + 7);
                        Sub = Sub.substring(1, Sub.length());
                        System.out.println("移进");
                    } else if (Sub.substring(0, 1).equals("+")) {
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'E');
                        System.out.println("按E->E+T归约");
                    } else if (Sub.substring(0, 1).equals(")")) {
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'E');
                        System.out.println("按E->E+T归约");
                    } else if (Sub.substring(0, 1).equals("#")) {
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'E');
                        System.out.println("按E->E+T归约");
                    } else {
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        Gto((int) stack.peek().charAt(0) - (int) '0', 'E');
                        System.out.println("按E->E+T归约");
                    }
                    break;
            }
        }
        if (acc) {
            System.out.println("匹配成功");
        }
    }
}
