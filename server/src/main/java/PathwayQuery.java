import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PathwayQuery {
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_2_ica?characterEncoding=UTF8&autoReconnect=true&useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASS = "chen0402";
    private String id = null;
    public static String getValue(String id, String parameter){
        Connection connection = null;
        Statement statement = null;
        String value = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM pathway WHERE id='" +  id+ "'";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                value = rs.getString(parameter);
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
    public static ArrayList<HashMap> getPathwayByGene (String gene){
        ArrayList<HashMap> pathways = new ArrayList<>();
        ArrayList<HashMap> allPathways = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT id, name, genes FROM pathway";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                HashMap<String, String> pathway = new HashMap<>();
                pathway.put("id", rs.getString("id"));
                pathway.put("name", rs.getString("name"));
                pathway.put("genes", rs.getString("genes"));
                allPathways.add(pathway);
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (HashMap<String, String> pathway:allPathways){
            String genes = pathway.get("genes");
            if (genes.indexOf(gene) != -1){
                pathway.remove("genes");
                pathways.add(pathway);
            }
        }
        return pathways;
    }
}
