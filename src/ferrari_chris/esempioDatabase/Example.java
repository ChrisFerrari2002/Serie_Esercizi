package ferrari_chris.esempioDatabase;

import java.sql.*;

public class Example {
    public static void main(String[] args) {
        String username = "sa";
        String password = "";
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", username, password)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT NAME, ID FROM TEST");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " -> " + resultSet.getInt(2));
                }
            }
            String insertDataQuery = "INSERT INTO TEST (ID, NAME) VALUES (3, '!')";
            try (Statement statement = connection.createStatement()){
                int rowsInserted = statement.executeUpdate(insertDataQuery);

                if (rowsInserted > 0){
                    System.out.println("Data inserted successfully");
                } else {
                    System.out.println("Failed to insert data");
                }
            }
            // Your database operations here
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
