package servlet;

import dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 注册
@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if("".equals(username)) {
            req.getSession().setAttribute("errorMessage", "用户名不能为空");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if(userDAO.findUser(username)) {
            req.getSession().setAttribute("errorMessage", "用户名已存在");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if(!password.equals(confirmPassword)) {
            req.getSession().setAttribute("errorMessage", "密码不一致");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if("".equals(password)) {
            req.getSession().setAttribute("errorMessage", "密码不能为空");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        userDAO.addUser(username, password);
        req.getSession().removeAttribute("errorMessage");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
