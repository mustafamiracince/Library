import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Login extends JFrame{
    private JTextField textKullaniciAdi;
    private JButton girişYapButton;
    private JPanel panelLogin;
    private JPasswordField passwordField;
    public Login(){
        panelLogin.setBackground(Color.gray);
        add(panelLogin);
        setSize(400,250);
        setTitle("Giriş Ekranı");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbHelper  helper=new DbHelper();
                String ad,sifre;
                ad=textKullaniciAdi.getText();
                sifre=passwordField.getText();
                try {
                    helper.connection=helper.getConnect();
                    helper.st=helper.connection.createStatement();
                    String sql="SELECT * FROM login WHERE ad='"+ad+"'AND sifre='"+sifre+"'";
                    ResultSet rs=helper.st.executeQuery(sql);
                    if (rs.next()){
                        dispose();
                        Kutuphane kutuphane=new Kutuphane();
                        kutuphane.show();
                    }else {
                        JOptionPane.showMessageDialog(panelLogin,"Kullanıcı Adı veya Şifre yanlış!!");
                        //textKullaniciAdi.setText("");
                        //passwordField.setText("");
                    }
                    helper.connection.close();
                }catch (SQLException exception){
                    helper.showErrorMessage(exception);
                }
            }
        });
    }
}
