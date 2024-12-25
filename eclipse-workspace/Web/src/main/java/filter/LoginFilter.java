package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        ArrayList<String> allowList=new ArrayList<>();
        allowList.add("/login.jsp");
        allowList.add("/LoginFilter");
        allowList.add("/register.jsp");
        allowList.add("/RegisterFilter");

        String currentURL=req.getServletPath();

        if(session.getAttribute("user") == null && !allowList.contains(currentURL)) {
            res.sendRedirect("/Web/login.jsp");
        }else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
