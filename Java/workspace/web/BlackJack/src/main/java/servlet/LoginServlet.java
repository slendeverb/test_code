package servlet;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        username = username.trim();
        password = password.trim();
        if(!userDAO.findUser(username)) {
            return;
        }else {
            String passwordInDatabase = userDAO.getPassword(username);
            if (password.equals(passwordInDatabase)) {
                req.getSession().setAttribute("user", username);
                resp.sendRedirect(req.getContextPath()+"/index.jsp");
            }else {
                return;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
