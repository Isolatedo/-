package cs.two.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Utils {
    private static ComboPooledDataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();
    }
    public static Connection getConnection() throws SQLException {
        dataSource.setLogWriter(null);
        return dataSource.getConnection();
    }
}
