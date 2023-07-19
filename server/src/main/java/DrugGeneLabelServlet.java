import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DrugGeneLabelServlet")
public class DrugGeneLabelServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String gene = req.getParameter("gene");

        resp.getWriter().write("Gene: ");
        resp.getWriter().write(gene);
        resp.getWriter().write("<br/><br/>");

        ArrayList<HashMap> annotations = DrugLabelAnnotationQuery.getAnnotationsByGene(gene);
        if (annotations.size() == 0){
            resp.getWriter().write("No pathway");
        }
        else {
            for (HashMap<String, String> annotation:annotations){
                resp.getWriter().write("Drug: " + annotation.get("chemicals") + "<br/>");
                resp.getWriter().write("Annotation ID:" + annotation.get("id") + "<br/>");
                resp.getWriter().write("Annotation name: " + annotation.get("name") + "<br/>");
                resp.getWriter().write("Source: " + annotation.get("source") + "<br/>");
                resp.getWriter().write("Testing level: " + annotation.get("testing_level") + "<br/>");;
                resp.getWriter().write("Genes: " + annotation.get("genes") + "<br/><br/>");
            }
        }
        resp.getWriter().write("<br/>");
        resp.getWriter().write("<a href=\"home.jsp\">Click here to return</a>");
    }
}
