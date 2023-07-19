import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/DrugGeneClinicalServlet")
public class DrugGeneClinicalServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String gene = req.getParameter("gene");

        resp.getWriter().write("Gene: ");
        resp.getWriter().write(gene);
        resp.getWriter().write("<br/><br/>");

        ArrayList<HashMap> annotations = DrugClinicalAnnotationQuery.getAnnotationsByGene(gene);
        if (annotations.size() == 0){
            resp.getWriter().write("No clinical annotation.");
        }
        else {
            for (HashMap<String, String> annotation:annotations){
                resp.getWriter().write("ID: " + annotation.get("id") + "<br/>");
                resp.getWriter().write("Variant: " + annotation.get("Variant") + "<br/>");
                resp.getWriter().write("Gene: " + annotation.get("Gene") + "<br/>");
                resp.getWriter().write("Drug: " + annotation.get("Drug") + "<br/>");
                resp.getWriter().write("Phenotype category: " + annotation.get("Phenotype_Category") + "<br/>");
                resp.getWriter().write("Phenotype: " + annotation.get("Phenotype") + "<br/><br/>");
            }
        }
        resp.getWriter().write("<br/>");
        resp.getWriter().write("<a href=\"home.jsp\">Click here to return</a>");
    }
}
