import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DrugLabelAnnotationQuery {
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_2_ica?characterEncoding=UTF8&autoReconnect=true&useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASS = "chen0402";
    public static ArrayList getInfo(String name){
        Connection connection = null;
        Statement statement = null;
        ArrayList<HashMap> annotations = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM druglabels WHERE chemicals='" + name + "'";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                HashMap<String, String> annotation = new HashMap<>();
                annotation.put("id", rs.getString("id"));
                annotation.put("name", rs.getString("name"));
                annotation.put("source", rs.getString("source"));
                annotation.put("testing_level", rs.getString("testing_level"));
                annotation.put("genes", rs.getString("genes"));
                annotations.add(annotation);
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return annotations;
    }
    public static ArrayList<HashMap> getAnnotationsByGene (String gene){
        ArrayList<HashMap> annotations = new ArrayList<>();
        ArrayList<HashMap> allAnnotations = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM druglabels";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                HashMap<String, String> annotation = new HashMap<>();
                annotation.put("id", rs.getString("id"));
                annotation.put("name", rs.getString("name"));
                annotation.put("source", rs.getString("source"));
                annotation.put("testing_level", rs.getString("testing_level"));
                annotation.put("chemicals", rs.getString("chemicals"));
                annotation.put("genes", rs.getString("genes"));
                allAnnotations.add(annotation);
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (HashMap<String, String> annotation:allAnnotations){
            String genes = annotation.get("genes");
            if (genes.indexOf(gene) != -1){
                annotations.add(annotation);
            }
        }
        return annotations;
    }
}
