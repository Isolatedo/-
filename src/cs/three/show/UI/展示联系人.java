package cs.three.show.UI;

import cs.three.show.entity.Contact;

import java.util.List;
import java.util.Scanner;

public class 展示联系人 {
    public void display(List<Contact> contactList) {
        if(contactList.size()==0) {
            System.out.println("没有相关的联系人!");
        }else {
            // 计算每列的最大宽度
            int idWidth = getMaxWidth(contactList, "getCnumber");
            int nameWidth = getMaxWidth(contactList, "getCname");
            int addressWidth = getMaxWidth(contactList, "getCaddress");
            int phoneWidth = getMaxWidth(contactList, "getCphone");

            // 输出表格上边框
            printHorizontalLine(idWidth, nameWidth, addressWidth, phoneWidth);
            // 输出表头
            System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + addressWidth + "s | %-" + phoneWidth + "s |\n", "编号", "姓名", "地址", "电话");

            // 输出表格中间分隔线
            printHorizontalLine(idWidth, nameWidth, addressWidth, phoneWidth);

            // 输出人员信息
            for (Contact contact : contactList) {
                System.out.printf("| %-" + idWidth + "s | %-" + nameWidth + "s | %-" + addressWidth + "s | %-" + phoneWidth + "s |\n", String.valueOf(contact.getCnumber()), contact.getCname(), contact.getCaddress(), contact.getCphone());
            }
            // 输出表格下边框
            printHorizontalLine(idWidth, nameWidth, addressWidth, phoneWidth);
        }
        System.out.print("按下回车键返回...");
        new Scanner(System.in).nextLine();
        System.out.print("\n\n\n");
    }
    private static void printHorizontalLine(int idWidth, int nameWidth, int addressWidth, int phoneWidth) {
        System.out.print("+");
        printDashLine(idWidth);
        System.out.print("+");
        printDashLine(nameWidth);
        System.out.print("+");
        printDashLine(addressWidth);
        System.out.print("+");
        printDashLine(phoneWidth);
        System.out.println("+");
    }
    // 打印连续的短横线
    private static void printDashLine(int width) {
        for (int i = 0; i < width + 2; i++) {
            System.out.print("-");
        }
    }
    // 获取集合中最大字符串的宽度
    private static int getMaxWidth(List<Contact> contactList, String methodName) {
        int maxWidth = 0;
        try {
            for (Contact contact : contactList) {
                String value = String.valueOf(contact.getClass().getMethod(methodName).invoke(contact));
                int width = value.length();
                if (width > maxWidth) {
                    maxWidth = width;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxWidth;
    }


}
