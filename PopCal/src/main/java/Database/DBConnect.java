package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect
{
    public List<String> connectDB(String query)
    {
        List<String> results = new ArrayList<String>();
        try{
            String url = "jdbc:postgresql://localhost:5435/postgres"; // {dbUrl}/{schema}
            String username = "postgres";
            String password = "1234";

            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query); //Enter query
            while (resultSet.next()) {
                results.add(resultSet.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }
}
