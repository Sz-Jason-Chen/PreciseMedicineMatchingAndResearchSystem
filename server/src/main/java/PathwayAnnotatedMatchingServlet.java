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

@WebServlet("/PathwayAnnotatedMatchingServlet")
@MultipartConfig
public class PathwayAnnotatedMatchingServlet extends HttpServlet{
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

        resp.getWriter().write("<h4>test message: gene to pathway<br/></h4>");

        Iterator<String> itr5 = matchedGenes.listIterator();
        while (itr5.hasNext()) {
            String gene = itr5.next();
            ArrayList<HashMap> pathways = PathwayQuery.getPathwayByGene(gene);

            if (pathways.size() != 0) {
                for (HashMap<String, String> pathway:pathways){
                    //resp.getWriter().write("Gene symbol:");
                    resp.getWriter().write(gene);
                    // resp.getWriter().write("ID:    ");
                    //resp.getWriter().write(pathway.get("id"));
                    resp.getWriter().write("<br/>");
                    // resp.getWriter().write("Name:  ");
                    resp.getWriter().write(pathway.get("name"));
                    resp.getWriter().write("<br/><br/>");
                }
            }

        }

        resp.getWriter().write("<p>test message: finish<br/></p>");


    }
}
