import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@WebServlet("/DrugAnnotatedMatchingServlet")
@MultipartConfig
public class DrugAnnotatedMatchingServlet extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{

        Part filePart = req.getPart("file");
        InputStream is = filePart.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        resp.getWriter().write("<p>test message: start<br/><br/></p>");
        String line;
        ArrayList<String> matchedGenes = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] subline = line.split("\t");
            matchedGenes.add(subline[6]);
        }

        resp.getWriter().write("<h4>test message: file read<br/></h4>");
        Iterator<String> itr0 = matchedGenes.listIterator();
        int limit = 0;
        while (itr0.hasNext()) {
            resp.getWriter().write(itr0.next());
            resp.getWriter().write("<br/>");
            limit++;
            if (limit > 10) {
                break;
            }
        }

        resp.getWriter().write("<h4>test message: gene to drug<br/></h4>");

        Iterator<String> itr5 = matchedGenes.listIterator();
        while (itr5.hasNext()) {
            String gene = itr5.next();
            ArrayList<HashMap> annotations = DrugLabelAnnotationQuery.getAnnotationsByGene(gene);
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
