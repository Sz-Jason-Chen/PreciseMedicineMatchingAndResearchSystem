import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserQuery query = new UserQuery(username, password);
        String message = query.register();

        resp.getWriter().write(message);
        resp.getWriter().write("<br/>");
        resp.getWriter().write("<a href=\"index.jsp\">Click here to return</a>");
    }
}
