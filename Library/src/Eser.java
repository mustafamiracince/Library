import java.sql.SQLException;
public class Eser {
    public void addEser(String ad, String sayfa) throws SQLException {
        DbHelper helper = new DbHelper();
        String sql="INSERT INTO `eserler` (`eser_ID`, `ad`, `sayfasayisi`) VALUES ("+null+ ",'" + ad + "',"+"'"+sayfa+"')";
        helper.add(sql);
    }
    public void addEser(String id,String ad, String sayfa) throws SQLException {
        DbHelper helper = new DbHelper();
        String sql="INSERT INTO `eserler` (`eser_ID`, `ad`, `sayfasayisi`) VALUES ("+id+ ",'" + ad + "',"+"'"+sayfa+"')";
        System.out.println(sql);
        helper.add(sql);
    }
    public void deleteEser(String id) throws  SQLException{
        DbHelper helper = new DbHelper();
        String sql="DELETE FROM eserler WHERE eser_ID="+id;
        helper.delete(sql);
    }
}
