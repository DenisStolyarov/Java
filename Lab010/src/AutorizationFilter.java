import DAO.IDAO;
import DAO.MySQLDAO;
import DAO.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class AutorizationFilter implements Filter {

    private FilterConfig filterConfig;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        boolean isSuccess = false;
        HttpServletRequest request = (HttpServletRequest)req;

        Cookie[] cookies = request.getCookies();
        String cookieName = "Login";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    isSuccess = true;
                    break;
                }
            }
        }

        if(isSuccess){
            chain.doFilter(req, resp);
        }
        else{
            ServletContext ctx = filterConfig.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/Register.jsp");
            dispatcher.forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

}
