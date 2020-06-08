import DAO.IDAO;
import DAO.MySQLDAO;
import DAO.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String log = "";

        for (Cookie cookie: request.getCookies()) {
            if(cookie.getName().equals("Login")){
                log = cookie.getValue();
            }
        }

        IDAO database = null;
        ArrayList<User> users = null;
        try {
            database = new MySQLDAO();
            String country = request.getParameter("name");
            String price = request.getParameter("price");
            database.InsertTour(log,country,price);
            response.sendRedirect(request.getContextPath()+"/CurrentTime");
        }
        catch (SQLException e) {
            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }
        catch (ClassNotFoundException e) {
            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }

        try {

        }
        catch(Exception ex) {


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }
}
