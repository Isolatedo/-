package cs.two.Utils;

import cs.two.entity.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Convert {
    public static List<Contact> toConvert(ResultSet resultSet) throws SQLException {
        List<Contact> res = new ArrayList<>();
        while(resultSet.next()) {
            Contact contact = new Contact();
            int number = resultSet.getInt("Cnumber");
            String name = resultSet.getString("Cname");
            String address = resultSet.getString("Caddress");
            String phone = resultSet.getString("Cphone");
            contact.setCname(name);
            contact.setCnumber(number);
            contact.setCphone(phone);
            contact.setCaddress(address);
            res.add(contact);
        }
        return res;
    }
}
