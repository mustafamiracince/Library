import java.sql.SQLException;
public class Yazar {
    public void addYazar(String ad) throws  SQLException{
        DbHelper helper = new DbHelper();
        String sql="INSERT INTO yazarlar (`yazar_ID`, `yazar`) VALUES ("+null+ ",'" + ad + "')";
        helper.add(sql);
    }
    public void deleteYazar(String id) throws  SQLException{
        DbHelper helper = new DbHelper();
        String sql="DELETE FROM yazarlar WHERE yazar_ID="+id;
        helper.delete(sql);
    }
    }

