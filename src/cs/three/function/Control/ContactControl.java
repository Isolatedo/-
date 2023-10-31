package cs.three.function.Control;

import cs.three.function.Service.ConnectService;
import cs.three.function.Service.ConnectServiceImpl;
import cs.three.show.UI.展示联系人;
import cs.three.show.UI.菜单选项界面;
import cs.three.show.entity.Contact;

import java.util.List;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class ContactControl {
    private int op;
    private ConnectService connectService;
    private Scanner sc;
    public ContactControl(int op) {
        this.op = op;
        connectService = new ConnectServiceImpl();
        sc = new Scanner(System.in);
    }

    public void queryAllContact() {
        List<Contact> contacts = connectService.queryAll();
        展示联系人 show = new 展示联系人();
        show.display(contacts);
        菜单选项界面.display();
    }
    public void queryByName() {
        System.out.println("\n\n\n*****************************");
        System.out.print("请输入联系人姓名：");
        String name = sc.next();
        List<Contact> contacts = connectService.queryByName(name);
        展示联系人 show = new 展示联系人();
        show.display(contacts);
        菜单选项界面.display();
    }

    public void addContact() {
        Contact contact = new Contact();
        System.out.println("\n\n\n*****************************");
        System.out.print("请输入联系人姓名：");
        contact.setCname(sc.next());
        System.out.print("请输入联系人地址：（选填）");
        sc.nextLine();
        contact.setCaddress(sc.next());
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
    public void deleteContact() {
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

    public void updateContact() {
        Contact contact = new Contact();
        System.out.println("\n\n\n*****************************");
        System.out.print("请输入你要修改的联系人的姓名：");
        String oldName = sc.next();
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
        boolean b = connectService.updateConnect(oldName,contact);
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
    public void executeOp() {
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

}
