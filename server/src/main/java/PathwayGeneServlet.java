import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PathwayGeneServlet")
public class PathwayGeneServlet extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String gene = req.getParameter("gene");

        resp.getWriter().write("Gene: ");
        resp.getWriter().write(gene);
        resp.getWriter().write("<br/><br/>");

        ArrayList<HashMap> pathways = PathwayQuery.getPathwayByGene(gene);
        if (pathways.size() == 0){
            resp.getWriter().write("No pathway");
        }
        else {
            for (HashMap<String, String> pathway:pathways){
                // resp.getWriter().write("ID:    ");
                resp.getWriter().write(pathway.get("id"));
                resp.getWriter().write("<br/>");
                // resp.getWriter().write("Name:  ");
                resp.getWriter().write(pathway.get("name"));
                resp.getWriter().write("<br/><br/>");
            }
        }
        resp.getWriter().write("<a href=\"home.jsp\">Click here to return</a>");
    }
}
