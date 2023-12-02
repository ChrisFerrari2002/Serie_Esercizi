package ferrari_chris.serie10.esercizio01;


import java.sql.*;
//drop table if exist book;
/*
create table book (
    id IDENTITY NOT NULL,
    title varchar2(50),
    pub_year integer,
    publisher varchar2(20),
    author_first_name varchar2(20),
    author_last_name varchar2(20)
);
*/

public class Books {
    
    public static void main(String[] args) {
        insertBook("Carrie", 1974, "Doubleday", "Stephen", "King");
        insertBook("Shining", 1977, "Doubleday", "Stephen", "King");
        insertBook("IT", 1986, "Viking", "Stephen", "King");
        insertBook("Moby dick", 1851, "Richard Bentley", "Herman", "Melville");
        insertBook("Siddhartha", 1922, "Suhrkamp Verlag", "Hermann", "Hesse");

        System.out.println();
        System.out.println("printBooks()");
        printBooks();
    }

    /**
     * Add a new book to the database
     * 
     * @param title
     * @param pub_year
     * @param publisher
     * @param firstName
     * @param lastName
     */
    private static void insertBook(String title, int pub_year, String publisher, String firstName, String lastName) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM book");
                int maxId = 0;
                if (resultSet.next()){
                    maxId = resultSet.getInt(1);
                }
                int newId = maxId + 1;
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?)");
                pstmt.setInt(1, newId);
                pstmt.setString(2, title);
                pstmt.setInt(3, pub_year);
                pstmt.setString(4, publisher);
                pstmt.setString(5, firstName);
                pstmt.setString(6, lastName);
                pstmt.executeUpdate();
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    /**
     * Print all books
     */
    private static void printBooks() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")){
            try (Statement statement = connection.createStatement()){
                ResultSet rs = statement.executeQuery("SELECT * FROM book");
                while (rs.next()) {
                    System.out.printf("Book %d:\nTitle: %s\nPub Year: %s\nPublisher: %s\nAuthor: %s %s\n\n", rs.getInt("ID"),
                            rs.getString("TITLE"),
                            rs.getInt("PUB_YEAR"), rs.getString("PUBLISHER"),
                            rs.getString("AUTHOR_FIRST_NAME"), rs.getString("AUTHOR_LAST_NAME"));
                }
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }

    }
}
