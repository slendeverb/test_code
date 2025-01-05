package servlet;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 登录
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(!userDAO.findUser(username)) {
            req.getSession().setAttribute("errorMessage", "用户名错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        String passwordInDatabase = userDAO.getPassword(username);
        if (!password.equals(passwordInDatabase)) {
            req.getSession().setAttribute("errorMessage", "密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("user", username);
        req.getSession().removeAttribute("errorMessage");
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
