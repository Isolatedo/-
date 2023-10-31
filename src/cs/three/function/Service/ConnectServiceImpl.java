package cs.three.function.Service;

import cs.three.function.Utils.C3P0Utils;
import cs.three.function.Utils.Convert;
import cs.three.show.entity.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ConnectServiceImpl implements ConnectService {
    @Override
    public List<Contact> queryAll() {
        String sql = "select * from contact";
        try (Connection conn = C3P0Utils.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return Convert.toConvert(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Contact> queryByName(String name) {
        String sql = "select * from contact where Cname = ?";
        try (Connection conn = C3P0Utils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return Convert.toConvert(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean addConnect(Contact contact) {
        String sql = "insert into contact(Cname,Caddress,Cphone) values (?,?,?)";
        try (Connection conn = C3P0Utils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, contact.getCname());
            statement.setString(2, contact.getCaddress());
            statement.setString(3, contact.getCphone());
            return statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
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

    @Override
    public boolean deleteConnectByName(String name) {
        String sql = "DELETE FROM contact WHERE Cname = ?";
        try (Connection conn = C3P0Utils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            boolean execute = statement.execute();
            return execute;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean updateConnect(String oldName,Contact contact) {
        String sql = "UPDATE contact SET Cname = IFNULL(?,Caddress)," +
                "Caddress = IFNULL(?,Caddress)," +
                "Cphone = IFNULL(?,Cphone)" +
                " WHERE Cname = ?";
        try (Connection conn = C3P0Utils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, contact.getCname());
            statement.setString(2, contact.getCaddress());
            statement.setString(3, contact.getCphone());
            statement.setString(4, oldName);
            boolean execute = statement.execute();
            return execute;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
