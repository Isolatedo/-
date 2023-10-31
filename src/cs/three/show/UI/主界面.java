package cs.three.show.UI;

import java.util.Scanner;

public class 主界面 {
    public static void display() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("                         *+--------oOOo-----（❥(^_-)）-----oOOo---------+*\n");
        System.out.print("                                     欢迎来到何源的通讯录管理系统！\n");
        System.out.print("   ********************************************************************************************\n");
        System.out.print("                 █      █      ██▌██▌     █          █         ◢█ ◣      █                                             \n");
        System.out.print("                 █      █      ▌▌         █          █         █   █      █                                        \n");
        System.out.print("                 █▉▉▉▉█ █      ▌▌▌▌▌▌     █          █         █   █      █                                          \n");
        System.out.print("                 █      █      ▌▌         █          █         █   █      █                                         \n");
        System.out.print("                 █      █      ██▌██▌     █████      █████     ◥█ ◤      ●                                                   \n\n");
        System.out.print("按下回车键继续...");
        scanner.nextLine();
        clearScreen();
    }

    public static void clearScreen() {
        // 输出多个空白行来模拟清屏效果
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
