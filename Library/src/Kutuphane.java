import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
public class Kutuphane extends JFrame{
    private JPanel panelKutuphane;
    private JTextField textEser;
    private JTextField textSayfasayisi;
    private JTextField textYazar;
    private JButton eserEkleButton;
    private JButton yazarEkleButton;
    private JButton eserSilButton;
    private JButton yazarSilButton;
    private JTextField textEserID;
    private JTextField textYazarID;
    private JTable tableEser;
    private JButton listeleButtonEser;
    private JButton listeleButtonYazar;
    private JTable tableYazar;
    private JButton cikisYapButton;
    DefaultTableModel modelEser=(DefaultTableModel) tableEser.getModel();
    DefaultTableModel modelYazar=(DefaultTableModel) tableYazar.getModel();
    public Kutuphane(){
        add(panelKutuphane);
        setSize(800,400);
        setTitle("Kütüphane");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panelKutuphane.setBackground(Color.GRAY);
        tableEser.setBackground(Color.lightGray);
        tableYazar.setBackground(Color.LIGHT_GRAY);
        eserEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eser eser =new Eser();
                String eserAd,eserID,sayfaSayisi;
                eserAd= textEser.getText();
                sayfaSayisi= textSayfasayisi.getText();
                eserID= textEserID.getText();
                if(eserAd.isEmpty() || sayfaSayisi.isEmpty() && eserID!= ){
                    JOptionPane.showMessageDialog(panelKutuphane,"Lütfen ilgili alanları doldurunuz!!");
                    return;
                }
                try {
                    if(eserID.isEmpty()){
                        eser.addEser(eserAd,sayfaSayisi);
                        JOptionPane.showMessageDialog(panelKutuphane,"Eser Eklendi");
                    }else{
                        eser.addEser(eserID,eserAd,sayfaSayisi);
                        JOptionPane.showMessageDialog(panelKutuphane,"Eser Eklendi");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        eserSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eser eser=new Eser();
                String ad,sayfasayisi,eserID;
                ad=textEser.getText();
                sayfasayisi=textSayfasayisi.getText();
                eserID=textEserID.getText();
                if (eserID.isEmpty() || ad.isEmpty()){
                    JOptionPane.showMessageDialog(panelKutuphane,"Lütfen ilgili alanları doldurunuz!!");
                    return;
                }
                try {
                    eser.deleteEser(eserID);
                    JOptionPane.showMessageDialog(panelKutuphane,"Eser Silindi");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        yazarEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Yazar yazar =new Yazar();
                String yazarAd, yazarID;
                yazarAd= textYazar.getText();
                yazarID= textYazarID.getText();
                if (yazarAd.isEmpty()){
                    JOptionPane.showMessageDialog(panelKutuphane,"Yazar adı boş bırakılamaz!!");
                    return;
                }
                    try {
                        yazar.addYazar(yazarAd);
                        JOptionPane.showMessageDialog(panelKutuphane,"Yazar Eklendi");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        });
        yazarSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Yazar yazar =new Yazar();
                String yazarAd, yazarID;
                yazarAd= textYazar.getText();
                yazarID= textYazarID.getText();
                if (yazarID.isEmpty() || yazarAd.isEmpty()){
                    JOptionPane.showMessageDialog(panelKutuphane,"Lütfen ilgili alanları doldurunuz!!");
                    return;
                }
                try {
                    yazar.deleteYazar(yazarID);
                    JOptionPane.showMessageDialog(panelKutuphane,"Yazar Silindi");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        listeleButtonEser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbHelper helper =new DbHelper();
                modelEser.setRowCount(0);
                try {
                    helper.connection=helper.getConnect();
                    helper.st=helper.connection.createStatement();
                    String sql="SELECT * FROM eserler";
                    ResultSet rs=helper.st.executeQuery(sql);
                    ResultSetMetaData rsmd=rs.getMetaData();
                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for (int i=0;i<cols;i++){
                        colName[i] =rsmd.getColumnName(i+1);
                        modelEser.setColumnIdentifiers(colName);
                    }
                    String eser_ID, eser, sayfasayisi;
                    while (rs.next()){
                        eser_ID=rs.getString(1);
                        eser=rs.getString(2);
                        sayfasayisi=rs.getString(3);
                        String[] row ={eser_ID, eser,sayfasayisi};
                        modelEser.addRow(row);
                    }
                    helper.st.close();
                    helper.connection.close();
                }catch (SQLException exception){
                    helper.showErrorMessage(exception);
                }
            }
        });
        listeleButtonYazar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbHelper helper =new DbHelper();
                modelYazar.setRowCount(0);
                try {
                    helper.connection=helper.getConnect();
                    helper.st=helper.connection.createStatement();
                    String sql="SELECT * FROM yazarlar";
                    ResultSet rs=helper.st.executeQuery(sql);
                    ResultSetMetaData rsmd=rs.getMetaData();
                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];
                    for (int i=0;i<cols;i++){
                        colName[i] =rsmd.getColumnName(i+1);
                        modelYazar.setColumnIdentifiers(colName);
                    }
                    String yazar_ID, yazar;
                    while (rs.next()){
                        yazar_ID=rs.getString(1);
                        yazar=rs.getString(2);
                        String[] row ={yazar_ID, yazar};
                        modelYazar.addRow(row);
                    }
                    helper.st.close();
                    helper.connection.close();
                }catch (SQLException exception){
                    helper.showErrorMessage(exception);
                }
            }
        });
        tableYazar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textYazar.setText((String)modelYazar.getValueAt(tableYazar.getSelectedRow(),1));
                textYazarID.setText((String)modelYazar.getValueAt(tableYazar.getSelectedRow(),0));
                super.mouseClicked(e);
            }
        });
        tableEser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textEser.setText((String)modelEser.getValueAt(tableEser.getSelectedRow(),1));
                textEserID.setText((String)modelEser.getValueAt(tableEser.getSelectedRow(),0));
                textSayfasayisi.setText((String)modelEser.getValueAt(tableEser.getSelectedRow(),2));
                super.mouseClicked(e);
            }
        });
        cikisYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login=new Login();
                login.show();
            }
        });
    }
}
