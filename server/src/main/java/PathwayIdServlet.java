import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PathwayIdServlet")
public class PathwayIdServlet extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");

        resp.getWriter().write("Pathway ID: ");
        resp.getWriter().write(id);
        resp.getWriter().write("<br/>");

        PathwayQuery query = new PathwayQuery();
        String name = query.getValue(id, "name");
        String chemicals = query.getValue(id, "chemicals");
        String genes = query.getValue(id, "genes");
        String pharmacodynamic = query.getValue(id, "pharmacodynamic");
        if (pharmacodynamic.equals("0")){
            pharmacodynamic = "No";
        }
        else {
            pharmacodynamic = "Yes";
        }
        String pharmacokinetic = query.getValue(id, "pharmacokinetic");
        if (pharmacokinetic.equals("0")){
            pharmacokinetic = "No";
        }
        else {
            pharmacokinetic = "Yes";
        }


        resp.getWriter().write("Name: ");
        resp.getWriter().write(name);
        resp.getWriter().write("<br/>");
        resp.getWriter().write("Chemicals: ");
        resp.getWriter().write(chemicals);
        resp.getWriter().write("<br/>");
        resp.getWriter().write("Genes: ");
        resp.getWriter().write(genes);
        resp.getWriter().write("<br/>");
        resp.getWriter().write("Pharmacodynamic: ");
        resp.getWriter().write(pharmacodynamic);
        resp.getWriter().write("<br/>");
        resp.getWriter().write("Pharmacokinetic: ");
        resp.getWriter().write(pharmacokinetic);
        resp.getWriter().write("<br/><br/>");
        resp.getWriter().write("<a href=\"home.jsp\">Click here to return</a>");
    }
}
