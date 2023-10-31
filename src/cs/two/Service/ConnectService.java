package cs.two.Service;

import cs.two.Utils.C3P0Utils;
import cs.two.Utils.Convert;
import cs.two.entity.Contact;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 使用过程存储
 */
public class ConnectService {
    public List<Contact> queryAll() {
        try (Connection conn = C3P0Utils.getConnection()) {
            CallableStatement callableStatement = conn.prepareCall("{call queryAllContacts_proce}");
            ResultSet resultSet = callableStatement.executeQuery();
            return Convert.toConvert(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> queryByName(String name) {
        try (Connection conn = C3P0Utils.getConnection()) {
            CallableStatement callableStatement = conn.prepareCall("{call queryByName(?)}");
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            return Convert.toConvert(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean addConnect(Contact contact) {
        try (Connection conn = C3P0Utils.getConnection()) {
            CallableStatement callableStatement = conn.prepareCall("{call addContact(?,?,?)}");
            callableStatement.setString(1, contact.getCname());
            callableStatement.setString(2, contact.getCaddress());
            callableStatement.setString(3, contact.getCphone());
            return callableStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean deleteConnectById(Integer id) {
        String sql = "DELETE FROM contact WHERE Cnumber = ?";
        try (Connection conn = C3P0Utils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            boolean execute = statement.execute();
            return execute;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean deleteConnectByName(String name) {
        try (Connection conn = C3P0Utils.getConnection()) {
            CallableStatement callableStatement = conn.prepareCall("{call deleteContact_proce(?)}");
            callableStatement.setString(1, name);
            boolean execute = callableStatement.execute();
            return execute;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean updateConnect(Contact contact) {
        String sql = "UPDATE contact SET Cname = IFNULL(Cname, ?)," +
                "Caddress = IFNULL(Caddress,?)," +
                "Cphone = IFNULL(Cphone,?)" +
                " WHERE Cnumber = ?";
        try (Connection conn = C3P0Utils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, contact.getCname());
            statement.setString(2, contact.getCaddress());
            statement.setString(3, contact.getCphone());
            statement.setInt(4, contact.getCnumber());
            boolean execute = statement.execute();
            return execute;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
