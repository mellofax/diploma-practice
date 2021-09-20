package ForUI.DateBase;

import ForUI.Account.Account;
import ForUI.Static.Log;

import java.sql.*;

public class DBConnection {
    private String user = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/accounts";

    private Connection con;
    private Statement statement;

    public DBConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
            if(con != null)
            {
                Log.getLogger().info("Connection to the database is successful!");
                statement = con.createStatement();
            }
        } catch (SQLException message) {
            Log.getLogger().error(message);
        }
    }

    public Account GetEmailAccount() {
        try {
            Account account = null;
            ResultSet result = statement.executeQuery("SELECT Login,Password FROM list WHERE Type='Mail'");
            String login = null;
            String pass = null;
            while(result.next())
            {
                login = result.getString(1);
                pass = result.getString(2);
            }
            account = new Account(login, pass);
            Log.getLogger().info("Mail account information received");
            CloseConnection();
            return account;
        } catch (SQLException message) {
            Log.getLogger().error(message);
            return null;
        }
    }
    public Account GetAliexpressAccount() {
        Account account = null;
        try {
            ResultSet result = statement.executeQuery("SELECT Login,Password FROM list WHERE Type='Aliexpress'");
            String login = null;
            String pass = null;
            while(result.next())
            {
                login = result.getString(1);
                pass = result.getString(2);
            }
            account = new Account(login, pass);
            Log.getLogger().info("Aliexpress account information received");
            CloseConnection();
        } catch (SQLException message) {
            Log.getLogger().error(message);
        }
        return account;
    }

    private void CloseConnection() {
        try {
            con.close();
            Log.getLogger().info("Connection to the database is closed!");
        } catch (SQLException message) {
            Log.getLogger().error(message);
        }
    }
}
