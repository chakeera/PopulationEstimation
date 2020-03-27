package Database;

import GUI.*;

import java.sql.*;

public class DBConnect
{
    public String connectDB()
    {
        queries queries = new queries();
        try{
            String url = ""; // {dbUrl}/{schema}
            String username = "";
            String password = "";

            Class.forName("org.postgresql.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(queries.getOffspring()); //Enter query
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public static void main(String[] args) {
        DBConnect dbConnect = new DBConnect();
        dbConnect.connectDB();
    }
}
