import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GenomeQuery {
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_2_ica?characterEncoding=UTF8&autoReconnect=true&useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASS = "chen0402";

    public ArrayList getGenes(){
        ArrayList<HashMap> genes = new ArrayList<>();
        String value = null;

        Connection connection = null;
        Statement statement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT symbol, chromosome, start, stop FROM gene ORDER BY chromosome, start";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                HashMap<String, String> gene = new HashMap<>();
                value = rs.getString("symbol");
                gene.put("symbol", value);
                value = rs.getString("chromosome");
                gene.put("chromosome", value);
                value = rs.getString("start");
                gene.put("start", value);
                value = rs.getString("stop");
                gene.put("stop", value);
                genes.add(gene);
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return genes;
    }
    public static ArrayList<HashMap> getPathwayByGene (String gene){
        ArrayList<HashMap> pathways = new ArrayList<>();
        ArrayList<HashMap> allPathways = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
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
