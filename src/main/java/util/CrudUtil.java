package util;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql,Object... args) throws SQLException {
        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            psTm.setObject((i+1),args[i]);
        }
        if(sql.startsWith("SELECT")||sql.startsWith("select")){
            return (T) psTm.executeQuery();
        }
        return (T) (Boolean) (psTm.executeUpdate()>0);
    }

}
