import javax.swing.*;
import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        DbHelper helper=new DbHelper();
        Connection connection=null;
        try {
            helper.getConnect();
            System.out.println("Bağlantı oluştu!");
        }catch (SQLException e){
            e.getMessage();
        }
        SwingUtilities.invokeLater(() -> {
            Login login=new Login();
            login.setVisible(true);
        });



    }
}
