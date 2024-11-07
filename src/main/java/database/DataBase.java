package database;
import entities.*;
import java.sql.*;
public class DataBase {
    public  static final List userList = new List();
    public static User loggedInUser;
    public static final List categoryList = new List();
    public static final List tagList = new List();
    public static final List publishedArticles = new List();
    public static final List articlesToCheckForPublish = new List();


        static final String DB_URL ="jdbc:postgresql://localhost:5432/postgres";
        static final String USER = "postgres";
        static final String PASS = "6250074678";
        static final String QUERY = "SELECT id,birthday,firstname,lastname FROM Authors";

        public static void main(String[] args) {
            // Open a connection
            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY)) {
                // Extract data from result set
                while (rs.next()) {
                    // Retrieve by column name
                    System.out.print("ID: " + rs.getInt("id"));
                    System.out.print(", Age: " + rs.getDate("birthday"));
                    System.out.print(", First: " + rs.getString("firstname"));
                    System.out.println(", Last: " + rs.getString("lastname"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

