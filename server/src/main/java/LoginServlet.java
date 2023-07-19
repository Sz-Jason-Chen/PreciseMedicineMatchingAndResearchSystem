import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserQuery query = new UserQuery(username, password);
        int loginMessage = query.login();

        resp.getWriter().write(username);
        resp.getWriter().write("<br/>");
        if (loginMessage == 1){
            // resp.getWriter().write("Login success");
            // resp.getWriter().write("<br>Calling home page...");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
        else if (loginMessage == -1){
            resp.getWriter().write("Login failed: User not found");
        }
        else if (loginMessage == -2){
            resp.getWriter().write("Login failed: Password incorrect");
        }
    }
}

