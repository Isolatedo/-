package cs.three.show.UI;

import cs.three.function.Control.ContactControl;

import java.util.Scanner;

public class 菜单选项界面 {
    public static void display() {
        System.out.print("|************个人通讯录管理系统************\n");
        System.out.print("| 请选择操作菜单序号(0-5) \n");
        System.out.print("|......................................\n");
        System.out.print("| 1------ 查询全部联系人 |\n");
        System.out.print("| 2------ 根据名称查询联系人 |\n");
        System.out.print("| 3------ 添加联系人 |\n");
        System.out.print("| 4------ 删除联系人 |\n");
        System.out.print("| 5------ 修改联系人 |\n");
        System.out.print("| 0------ 退出 |\n");
        System.out.print("|*************************************\n");
        System.out.print("请输入菜单序号：");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        ContactControl contactControl = new ContactControl(op);
        contactControl.executeOp();
    }
}
