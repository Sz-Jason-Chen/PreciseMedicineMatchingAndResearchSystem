import java.util.ArrayList;
import java.util.HashMap;

public class test {
    public static void main(String[] args) {

        PathwayQuery query = new PathwayQuery();
        ArrayList<HashMap> pathways = PathwayQuery.getPathwayByGene("TGFB1");
        System.out.println(pathways);
        for (HashMap<String, String> pathway:pathways){
            System.out.println(pathway.get("id"));
            System.out.println(pathway.get("name"));
        }
    }



}
