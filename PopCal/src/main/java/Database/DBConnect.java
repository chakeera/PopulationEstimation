package Database;

import GUI.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect
{
    public List<String> connectDB(String query)
    {
        queries queries = new queries();
        List<String> results = new ArrayList<String>();
        try{
            String url = ""; // {dbUrl}/{schema}
            String username = "";
            String password = "";

            Class.forName("org.postgresql.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query); //Enter query
            while (resultSet.next())
            {
                results.add(resultSet.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    public static void main(String[] args) {
        DBConnect dbConnect = new DBConnect();
//        dbConnect.connectDB();
    }
}
