package cs.two.UI;

import cs.two.Service.ConnectService;
import cs.two.entity.Contact;

import java.util.List;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class 菜单选项界面 {
    private static ConnectService connectService;
    private static Scanner sc;
    static  {
        sc = new Scanner(System.in);
        connectService = new ConnectService();
    }
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
        if(op==1) {
            queryAllContact();
        } else if (op==2) {
            queryByName();
        } else if(op==3) {
            addContact();
        } else if(op==4) {
            deleteContact();
        } else if(op==5) {
            updateContact();
        } else if(op==0) {
            exit();
        }
    }
    public static void queryAllContact() {
        List<Contact> contacts = connectService.queryAll();
        展示联系人 show = new 展示联系人();
        show.display(contacts);
        菜单选项界面.display();
    }
    public static void queryByName() {
        System.out.println("\n\n\n*****************************");
        System.out.print("请输入联系人姓名：");
        String name = sc.next();
        List<Contact> contacts = connectService.queryByName(name);
        展示联系人 show = new 展示联系人();
        show.display(contacts);
        菜单选项界面.display();
    }

    public static void addContact() {
        Contact contact = new Contact();
        System.out.println("\n\n\n*****************************");
        System.out.print("请输入联系人姓名：");
        contact.setCname(sc.next());
        contact.setCaddress(sc.nextLine());
        System.out.print("请输入联系人地址：（选填）");
        contact.setCaddress(sc.nextLine());
        System.out.print("请输入联系人电话：");
        contact.setCphone(sc.next());
        boolean b = connectService.addConnect(contact);
        if(!b) {
            System.out.print("\n\n\n\n添加成功！！         1查看联系人           2返回主菜单：");
            int reOp = sc.nextInt();
            if(reOp == 1) queryAllContact();
            else 菜单选项界面.display();
        }else {
            System.out.print("\n\n\n\n添加失败，请检查输入           1重新添加          2返回主菜单：");
            int reOp = sc.nextInt();
            if(reOp == 1) addContact();
            else 菜单选项界面.display();
        }
    }
    public static void deleteContact() {
        System.out.println("\n\n\n*****************************");
        System.out.print("请输入要删除的联系人名称：");
        boolean b = connectService.deleteConnectByName(sc.next());
        if(!b) {
            System.out.print("\n\n删除成功！！         1查看联系人          2返回主菜单：");
            int reOp = sc.nextInt();
            if(reOp == 1) queryAllContact();
            else 菜单选项界面.display();
        }else {
            System.out.print("\n\n删除失败，请检查输入！           1重新删除          2返回主菜单：");
            int reOp = sc.nextInt();
            if(reOp == 1) deleteContact();
            else 菜单选项界面.display();
        }
    }

    public static void updateContact() {
        Contact contact = new Contact();
        System.out.println("\n\n\n*****************************");
        System.out.print("请输入你要修改的联系人的姓名：");
        contact.setCname(sc.next());
        System.out.println("请选择要修改的信息：        1.姓名          2.地址          3.电话号码");
        int i = sc.nextInt();
        if(i==1) {
            System.out.print("请输入新的姓名：");
            contact.setCname(sc.next());
        }else if (i==2) {
            System.out.print("请输入新的地址：");
            contact.setCaddress(sc.next());
        }else if(i==3) {
            System.out.print("请输入新的电话号码：");
            contact.setCphone(sc.next());
        }
        boolean b = connectService.updateConnect(contact);
        if(!b) {
            System.out.println("\n\n\n\n修改成功！！         1查看联系人          2返回主菜单：");
            int reOp = sc.nextInt();
            if(reOp == 1) queryAllContact();
            else 菜单选项界面.display();
        }else {
            System.out.print("\n\n修改失败，请检查输入！           1重新修改         2返回主菜单：");
            int reOp = sc.nextInt();
            if(reOp == 1) updateContact();
            else 菜单选项界面.display();
        }
    }


}
