import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReferenceQuery {
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_2_ica?characterEncoding=UTF8&autoReconnect=true&useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASS = "chen0402";
    public static ArrayList getReference(String gene){
        Connection connection = null;
        Statement statement = null;
        ArrayList<HashMap> references = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT sourceID, source_name FROM reference WHERE genes='" + gene + "'";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                HashMap<String, String> reference = new HashMap<>();
                reference.put("sourceID", rs.getString("sourceID"));
                reference.put("source_name", rs.getString("source_name"));
                references.add(reference);
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return references;
    }
}
