package controller;

import dto.SystemAdminDto;
import service.SystemAdminService;
import service.impl.SystemAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/account")
public class SystemAdminServlet extends HttpServlet {


    private SystemAdminService systemAdminService = new SystemAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String path = req.getContextPath();
        try {
            String method = req.getParameter("method");
            switch (method) {

                case "login":
                    String email = req.getParameter("email1");
                    String password = req.getParameter("pwd");
                    SystemAdminDto systemAdminDto1 = this.systemAdminService.login(email, password);
                    if (systemAdminDto1.getCode() == -1) {
                        req.setAttribute("emailError", "此邮箱不存在！请注册！");
                        req.getRequestDispatcher("login.jsp").forward(req, resp);
                    }
                    if (systemAdminDto1.getCode() == -2) {
                        req.setAttribute("pwdError", "密码错误");
                        req.getRequestDispatcher("login.jsp").forward(req, resp);
                    }
                    if (systemAdminDto1.getCode() == 1) {
                        HttpSession session = req.getSession();
                        session.setAttribute("admin", systemAdminDto1.getSystemAdmin());
                        resp.sendRedirect("/BSMM_war_exploded/house_list.jsp");
                    }
                case "register":
                    String email_new = req.getParameter("email2");
                    String password_new_1 = req.getParameter("pwd1");
                    SystemAdminDto systemAdminDto2 = this.systemAdminService.register(email_new, password_new_1);

                    if (systemAdminDto2.getCode() == -3) {
                        req.setAttribute("emailNError", "邮箱已存在！请登录！");
                        req.getRequestDispatcher("login.jsp").forward(req, resp);
                    }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
