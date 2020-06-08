import DAO.IDAO;
import DAO.MySQLDAO;
import DAO.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IDAO database = null;
        ArrayList<User> users = null;
        try {
            database = new MySQLDAO();
            users = database.SelectUsers();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String log = request.getParameter("login");
        String pass = String.valueOf(request.getParameter("password").hashCode());

        boolean isSucces = false;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).Login.equals(log) && users.get(i).Password.equals(pass)){
                isSucces = true;
                break;
            }
        }

        Cookie userNameCookieRemove = new Cookie("Login", "");
        userNameCookieRemove.setMaxAge(0);
        response.addCookie(userNameCookieRemove);

        if(isSucces){
            response.addCookie(new Cookie("Login", log));

        }

        //ServletContext servletContext = getServletContext();
        //RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/CurrentTime");
        //requestDispatcher.forward(request, response);

        response.sendRedirect("CurrentTime");

    }
}
