import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        resp.getWriter().write("doGet\n");
    }
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        resp.getWriter().write("doPost\n");
    }

}
