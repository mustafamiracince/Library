import javax.sound.midi.Soundbank;
import java.awt.*;
import java.sql.*;

public class DbHelper {
    private String userName = "root";
    private String password = "";
    private String dbUrl = "jdbc:mysql://localhost:3306/kütüphane";
    Connection connection = null;
    PreparedStatement statement=null;
    Statement st=null;
    public Connection getConnect() throws SQLException {
        return DriverManager.getConnection(dbUrl, userName, password);
    }

    public void showErrorMessage(SQLException exception) {
        System.out.println("Error: " + exception.getMessage());
        System.out.println("Error Code: " + exception.getErrorCode());
    }
    public void delete(String sql) {
       try {
           connection=getConnect();
           statement=connection.prepareStatement(sql);
           statement.executeUpdate();
           System.out.println("Kayıt Silindi!");
       }catch (SQLException exception){
           exception.getMessage();
       }
    }
    public void add(String sql){
        try {
            connection=getConnect();
            statement=connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Kayıt Eklendi!");
        }catch (SQLException exception){
            exception.getMessage();
        }
    }
}
