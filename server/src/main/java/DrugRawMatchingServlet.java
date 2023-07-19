import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/DrugRawMatchingServlet")
@MultipartConfig
public class DrugRawMatchingServlet extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{

        Part filePart = req.getPart("file");
        InputStream is = filePart.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        resp.getWriter().write("<p>test message: start<br/><br/></p>");
        resp.getWriter().write("<h4>Matching Start.<br/></h4>");
        String line;
        ArrayList<ArrayList> locations = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (!(line.startsWith("#"))) {
                String[] subline = line.split("\t");
                ArrayList<String> location = new ArrayList<>();
                location.add(subline[0]);
                location.add(subline[1]);
                locations.add(location);
            }
        }
        resp.getWriter().write("<h4>Read file successfully.<br/></h4>");

        Iterator<ArrayList> itr0 = locations.listIterator();
        int limit = 0;
        while (itr0.hasNext()) {
            ArrayList<String> testVariant = itr0.next();
            resp.getWriter().write((String) testVariant.get(0));
            resp.getWriter().write("\t");
            resp.getWriter().write((String) testVariant.get(1));
            resp.getWriter().write("<br/>");
            limit++;
            if (limit > 10) {
                break;
            }
        }



        GenomeQuery genomeQuery = new GenomeQuery();
        ArrayList<HashMap> genes = genomeQuery.getGenes();

        resp.getWriter().write("<h4>Database connected successfully.<br/></h4>");

        resp.getWriter().write("<h4>test message: database connection<br/></h4>");
        Iterator<HashMap> itr2 = genes.listIterator();
        int limit2 = 0;
        while (itr2.hasNext()) {
            HashMap<String, String> testGene = itr2.next();
            resp.getWriter().write((String) testGene.get("symbol"));
            resp.getWriter().write("\t");
            resp.getWriter().write((String) testGene.get("chromosome"));
            resp.getWriter().write("\t");
            resp.getWriter().write((String) testGene.get("start"));
            resp.getWriter().write("\t");
            resp.getWriter().write((String) testGene.get("stop"));
            resp.getWriter().write("<br/>");
            limit2++;
            if (limit2 > 20) {
                break;
            }
        }


        //resp.getWriter().write("<h4>test message: position to gene<br/></h4>");

        Iterator<ArrayList> itr = locations.listIterator();
        int limit4 = 0;
        ArrayList<HashMap> matchedGenes = new ArrayList<>();
        while (itr.hasNext()) {
            String vChrom = (String) itr.next().get(0);
            int vPos = Integer.parseInt((String) itr.next().get(1));
            Iterator<HashMap> itr3 = genes.listIterator();
            while (itr3.hasNext()) {
                HashMap<String, String> gene = itr3.next();
                String gChrom =(String) gene.get("chromosome");
                if (!(gChrom.equals(vChrom))) {
                    continue;
                }
                int gPosStart = Integer.parseInt(gene.get("start"));
                int gPosStop = Integer.parseInt(gene.get("stop"));
                if ((vPos<gPosStart) | (vPos>gPosStop)) {
                    continue;
                }
                if (matchedGenes.size()!=0) {
                    String gSymbol = (String) gene.get("symbol");
                    String lastVSymbol = (String) matchedGenes.get(matchedGenes.size()-1).get("symbol");
                    if (gSymbol.equals(lastVSymbol)) {
                        break;
                    }
                }
                matchedGenes.add(gene);
            }
            limit4++;
            if (limit4==10000){
                break;
            }
        }

        resp.getWriter().write("<h4>Position annotated successfully.<br/></h4>");

        resp.getWriter().write("<h4>test message: gene output<br/></h4>");
        Iterator<HashMap> itr4 = matchedGenes.listIterator();
        //int limit3 = 0;
        while (itr4.hasNext()) {
            HashMap<String, String> outputGene = itr4.next();
            resp.getWriter().write((String) outputGene.get("symbol"));
            resp.getWriter().write("\t");
            resp.getWriter().write((String) outputGene.get("chromosome"));
            resp.getWriter().write("\t");
            resp.getWriter().write((String) outputGene.get("start"));
            resp.getWriter().write("\t");
            resp.getWriter().write((String) outputGene.get("stop"));
            resp.getWriter().write("<br/>");
            //limit3++;
            //if (limit3 > 100) {
            //    break;
            //}
        }



        resp.getWriter().write("<h4>Clinical Matching result<br/></h4>");
        Iterator<HashMap> itr5 = matchedGenes.listIterator();
        while (itr5.hasNext()) {
            HashMap<String, String> pairingGene = itr5.next();
            String gSymbol = (String) pairingGene.get("symbol");

            ArrayList<HashMap> annotations = DrugLabelAnnotationQuery.getAnnotationsByGene(gSymbol);

            if (annotations.size() != 0) {
                for (HashMap<String, String> annotation:annotations){
                    resp.getWriter().write("Drug: " + annotation.get("chemicals") + "<br/>");
                    resp.getWriter().write("Annotation ID:" + annotation.get("id") + "<br/>");
                    resp.getWriter().write("Annotation name: " + annotation.get("name") + "<br/>");
                    resp.getWriter().write("Source: " + annotation.get("source") + "<br/>");
                    resp.getWriter().write("Testing level: " + annotation.get("testing_level") + "<br/>");;
                    resp.getWriter().write("Genes: " + annotation.get("genes") + "<br/><br/>");
                }
            }
        }
        resp.getWriter().write("<p>test message: finish<br/></p>");
    }
}
