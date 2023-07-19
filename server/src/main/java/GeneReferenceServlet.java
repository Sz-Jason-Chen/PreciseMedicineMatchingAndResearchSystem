import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GeneReferenceServlet")
public class GeneReferenceServlet extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String gene = req.getParameter("gene");
        resp.getWriter().write("Gene: " + gene + "<br/><br/>");
        ReferenceQuery query = new ReferenceQuery();
        ArrayList<HashMap> references= query.getReference(gene);
        for (HashMap<String, String> reference : references) {
            resp.getWriter().write("Source ID: " + reference.get("sourceID") + "<br/>");
            resp.getWriter().write("Source name: " + reference.get("source_name") + "<br/><br/>");
        }
        resp.getWriter().write("<br/>");
        resp.getWriter().write("<a href=\"home.jsp\">Click here to return</a>");
    }
}
